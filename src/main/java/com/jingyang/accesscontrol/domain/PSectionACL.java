package com.jingyang.accesscontrol.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
public class PSectionACL {
    private PSection pSection;
    private String role;
    private Integer permissions;

}
