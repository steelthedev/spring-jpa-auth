package dev.akinwumi.jpasecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("")
    public   String  home(){
        return "Homme";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/admin")
    public  String admin(){
        return "Admin";
    }
}
