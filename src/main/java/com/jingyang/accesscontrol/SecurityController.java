package com.jingyang.accesscontrol;

import com.jingyang.accesscontrol.service.AccessControlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    Logger log = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    AccessControlService accessControlService;

    @GetMapping("")
    public String getHelloWorld() {
        log.info("HERE");
        return "hello";
    }

    @GetMapping("/api/v1/p/{id}")
    public ResponseEntity<String> getP(@PathVariable String id) {
        log.info("Accessed pid : {}", id);
        return ResponseEntity.ok( "Accessed id: " + id);
    }

}
