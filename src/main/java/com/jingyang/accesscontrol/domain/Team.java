package com.jingyang.accesscontrol.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class Team {

    private Long teamId;
    private String teamName;

    public static Team mockTeamRocket() {
        return new Team(1L, "Rocket");
    }

    public static Team mockTeamMagma() {
        return new Team(2L, "Magma");
    }

    public static Team mockTeamAqua() {
        return new Team(3L, "Aqua");
    }

    public static Team mockTeamGalactic() {
        return new Team(4L, "Galactic");
    }

}
