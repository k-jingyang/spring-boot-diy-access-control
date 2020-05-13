package com.jingyang.accesscontrol;

import com.jingyang.accesscontrol.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @Autowired
    AccessControlService accessControlService;

    @GetMapping("")
    public String getHelloWorld() {
        return "hello";
    }

}
