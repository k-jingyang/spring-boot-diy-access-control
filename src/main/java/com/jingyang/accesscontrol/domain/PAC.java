package com.jingyang.accesscontrol.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PAC {
    private Long pId;
    private List<ACRoleToTeams> roleToTeamsList;
}
