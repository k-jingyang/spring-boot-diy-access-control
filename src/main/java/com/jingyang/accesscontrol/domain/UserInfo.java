package com.jingyang.accesscontrol.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserInfo {

    private List<Team> teamList;

}
