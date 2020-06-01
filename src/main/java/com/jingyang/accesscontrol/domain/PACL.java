package com.jingyang.accesscontrol.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PACL {
    private Long pId;

    @JsonIgnore
    private List<RoleToTeams> roleToTeamsList;

    public Multimap<String, Team> getRoleToTeamsMap() {
        ArrayListMultimap<String, Team> roleToTeamsMap = ArrayListMultimap.create();

        for (RoleToTeams roleToTeams : roleToTeamsList) {
            roleToTeamsMap.putAll(roleToTeams.getRole(), roleToTeams.getTeamList());
        }

        return roleToTeamsMap;
    }

    public Map<String, Collection<String>> getTeamToRolesHashMap() {
        return getTeamToRolesMap().asMap();
    }

    public Multimap<String, String> getTeamToRolesMap() {
        ArrayListMultimap<String, String> roleToTeamsMap = ArrayListMultimap.create();

        for (RoleToTeams roleToTeams : roleToTeamsList) {
            for (Team team : roleToTeams.getTeamList()) {
                roleToTeamsMap.put(team.getName(), roleToTeams.getRole());
            }
        }
        return roleToTeamsMap;
    }

}
