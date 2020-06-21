package com.jingyang.accesscontrol;

import com.jingyang.accesscontrol.domain.VillainUser;
import com.jingyang.accesscontrol.service.AccessControlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    final
    AccessControlService accessControlService;

    public SecurityController(AccessControlService accessControlService) {
        this.accessControlService = accessControlService;
    }

    @GetMapping("")
    public String getHelloWorld(@AuthenticationPrincipal VillainUser user) {
        if(user != null ) {
            return "Hello, " + user.getUsername();
        }
        else {
            return "Hello, anonymous user";
        }
    }

    @GetMapping("/api/v1/pokemon/{id}")
    public ResponseEntity<String> getPokemon(@PathVariable Long id) {
        return ResponseEntity.ok( "Accessed general information about pokemon with id: " + id);
    }

    @GetMapping("/api/v1/pokemon/{id}/location")
    public ResponseEntity<String> getPokemonLocation(@PathVariable Long id) {
        return ResponseEntity.ok( "Accessed location information about pokemon with id: " + id);
    }

}
