package com.jingyang.accesscontrol.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class ACRoleToTeams {
    public String roleName;
    public List<Team> teamList;
}
