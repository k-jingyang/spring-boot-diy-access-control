package com.jingyang.accesscontrol.service;

import com.google.common.collect.Multimap;
import com.jingyang.accesscontrol.domain.*;
import com.jingyang.accesscontrol.mapper.AccessControlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccessControlService {

    AccessControlMapper accessControlMapper;

    public AccessControlService(@Autowired AccessControlMapper accessControlMapper) {
        this.accessControlMapper = accessControlMapper;
    }

    public boolean userHasAccess(Long pId, PSection section, Permission permission, UserInfo user) {
        PACL pACL = accessControlMapper.getPACL(pId);

        if (pACL == null) {
            throw new IllegalArgumentException("Unable to retrieve access control of p");
        }

        return hasAccessToInformation(pACL, section, permission, user);
    }

    public boolean hasAccessToInformation(PACL pACL, PSection sectionToAccess,
                    Permission permission, UserInfo user) {

        Multimap<String, String> teamToRolesMap = pACL.getTeamToRolesMap();
        Map<String, Integer> roleToPermissionsMap = getRoleToPermissionsMap(sectionToAccess);

        for (Team team : user.getTeams()) {
            Collection<String> rolesOfTeam = teamToRolesMap.get(team.getName());
            for (String role : rolesOfTeam) {
                Integer aclPermission = roleToPermissionsMap.get(role);
                if (permission.isWithin(aclPermission)) {
                    return true;
                }
            }
        }

        return false;
    }

    private Map<String, Integer> getRoleToPermissionsMap(PSection pSection) {
        List<PSectionACL> pSectionACLs = accessControlMapper.getPSectionACL(pSection);
        HashMap<String, Integer> roleToPermissionsMap = new HashMap();
        for (PSectionACL rolePermissions : pSectionACLs) {
            roleToPermissionsMap.put(rolePermissions.getRole(), rolePermissions.getPermissions());
        }
        return roleToPermissionsMap;
    }

}
