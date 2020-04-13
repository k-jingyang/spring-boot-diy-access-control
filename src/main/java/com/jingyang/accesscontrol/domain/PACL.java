package com.jingyang.accesscontrol.domain;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PACL {
    private Long pId;
    private List<RoleToTeams> roleToTeamsList;

    public Multimap<String, Team> getRoleToTeamsMap(){
        ArrayListMultimap<String, Team> roleToTeamsMap = ArrayListMultimap.create();

        for(RoleToTeams roleToTeams : roleToTeamsList) {
            roleToTeamsMap.putAll(roleToTeams.getRole(), roleToTeams.getTeamList());
        }

        return roleToTeamsMap;
    }

    public Multimap<Team, String> getTeamToRolesMap(){
        ArrayListMultimap<Team, String> roleToTeamsMap = ArrayListMultimap.create();

        for(RoleToTeams roleToTeams : roleToTeamsList) {
            for(Team team : roleToTeams.getTeamList()) {
                roleToTeamsMap.put(team, roleToTeams.getRole());
            }
        }

        return roleToTeamsMap;
    }

}
