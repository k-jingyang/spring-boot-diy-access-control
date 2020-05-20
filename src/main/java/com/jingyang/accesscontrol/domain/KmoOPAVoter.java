package com.jingyang.accesscontrol.domain;

import java.io.Console;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import com.jingyang.accesscontrol.mapper.AccessControlMapper;
import org.openpolicyagent.voter.OPADataRequest;
import org.openpolicyagent.voter.OPADataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KmoOPAVoter implements AccessDecisionVoter<Object> {

    Logger log = LoggerFactory.getLogger(KmoOPAVoter.class);

    private final String opaUrl = "http://localhost:8181/v1/data/http/authz/allow";

    @Autowired
    AccessControlMapper accessControlMapper;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object obj, Collection attributes) {
        if (!(obj instanceof FilterInvocation)) {
            return ACCESS_ABSTAIN;
        }

        FilterInvocation filter = (FilterInvocation) obj;
        Map<String, String> headers = new HashMap<String, String>();


        for (Enumeration<String> headerNames = filter.getRequest().getHeaderNames(); headerNames
                        .hasMoreElements();) {
            String header = headerNames.nextElement();
            headers.put(header, filter.getRequest().getHeader(header));
        }

        PACL pacl = accessControlMapper.getPACL(1L);

        log.info("requestURL: {}", filter.getRequestUrl());
        log.info("requestURI: {}", filter.getRequest().getRequestURI());
        // String[] path = filter.getRequest().getRequestURI().replaceAll("^/|/$", "").split("/");
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("auth", authentication);
        input.put("method", filter.getRequest().getMethod());
        input.put("path", filter.getRequestUrl());
        input.put("headers", headers);
        input.put("pacl", pacl.getTeamToRolesHashMap());

        RestTemplate client = new RestTemplate();
        HttpEntity<?> request = new HttpEntity<>(new OPADataRequest(input));
        OPADataResponse response =
                        client.postForObject(this.opaUrl, request, OPADataResponse.class);

        if (!response.getResult()) {
            return ACCESS_DENIED;
        }

        return ACCESS_GRANTED;

    }

}
