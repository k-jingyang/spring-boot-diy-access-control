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

    public Map<String, Collection<String>> getTeamToRolesHashMap() {
        return getTeamToRolesMap().asMap();
    }

    public Multimap<String, String> getTeamToRolesMap() {
        ArrayListMultimap<String, String> roleToTeamsMap = ArrayListMultimap.create();

        for (RoleToTeams roleToTeams : roleToTeamsList) {
            for (String team : roleToTeams.getTeams()) {
                roleToTeamsMap.put(team, roleToTeams.getRole());
            }
        }
        return roleToTeamsMap;
    }

}
