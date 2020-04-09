package com.jingyang.accesscontrol.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InfoSectionPermissions {
    private InfoSection infoSection;
    private String role;
    private Integer permissions;
}
