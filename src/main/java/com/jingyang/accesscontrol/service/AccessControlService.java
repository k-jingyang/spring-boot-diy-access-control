package com.jingyang.accesscontrol.service;

import com.jingyang.accesscontrol.domain.ACRoleToTeams;
import com.jingyang.accesscontrol.domain.InfoSection;
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


        // Would be nice to have a MultiBiMap
        HashMap<String, List<Team>> roleToTeamsMap = new HashMap();

        for(ACRoleToTeams roleToTeams : roleToTeamsList) {
            roleToTeamsMap.put(roleToTeams.getRoleName(), roleToTeams.getTeamList());
        }

        return roleToTeamsMap;
    }

    public boolean hasAccessToInformation(List<Team> userTeams, InfoSection sectionToAccess, String permission) {
        // retrieves access control information of this section:
        // in a map: role -> permission

        // for each team:
        // take the roles that this team is part of for this p:
        // check against the above map: if have permission return true, if there's no permission after all teams, return false

        return false;
    }
}
