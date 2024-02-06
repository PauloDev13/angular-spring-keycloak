package com.devpgm.keycloakspring.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@EnableMethodSecurity
public class ProductController {
    @GetMapping("get")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String list() {
        return "Listando produtos";
    }

    @PostMapping("post")
    @PreAuthorize("hasAnyRole('USER', 'GUEST', 'ADMIN')")
    public String create() {
        return "Criando produto";
    }
}
