package com.jingyang.accesscontrol.service;

import com.jingyang.accesscontrol.mapper.AccessControlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessControlService {

    AccessControlMapper accessControlMapper;

    public AccessControlService(@Autowired AccessControlMapper accessControlMapper) {
        this.accessControlMapper = accessControlMapper;
    }

}
