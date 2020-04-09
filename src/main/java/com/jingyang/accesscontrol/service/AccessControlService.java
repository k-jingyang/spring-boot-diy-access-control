package com.jingyang.accesscontrol.service;

import com.jingyang.accesscontrol.domain.ACRoleToTeams;
import com.jingyang.accesscontrol.domain.Team;
import com.jingyang.accesscontrol.mapper.AccessControlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class AccessControlService {

    @Autowired
    AccessControlMapper accessControlMapper;

    public HashMap<String, List<Team>> getPAccessControl(Long pId){
        List<ACRoleToTeams> roleToTeamsList = accessControlMapper.getRecords(1L).getRoleToTeamsList();

        HashMap<String, List<Team>> roleToTeamsMap = new HashMap();

        for(ACRoleToTeams roleToTeams : roleToTeamsList) {
            roleToTeamsMap.put(roleToTeams.getRoleName(), roleToTeams.getTeamList());
        }

        return roleToTeamsMap;
    }
}
