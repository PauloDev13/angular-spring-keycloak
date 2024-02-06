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
    @GetMapping("get/admin")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public String listAdmin() {
        return "ADMIN - Listando produtos";
    }

    @PostMapping("post/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String createAdmin() {
        return "ADMIN - Criando produto";
    }

    @GetMapping("get/user")
//    @PreAuthorize("hasAnyRole('USER')")
    public String listUser() {
        return "USER - Listando produtos";
    }

    @PostMapping("post/user")
    @PreAuthorize("hasAnyRole('USER')")
    public String createUser() {
        return "USER - Criando produto";
    }

    @GetMapping("get/guest")
//    @PreAuthorize("hasAnyRole('GUEST')")
    public String listGuest() {
        return "GUEST - Listando produtos";
    }

    @PostMapping("post/guest")
    @PreAuthorize("hasAnyRole('GUEST')")
    public String createGuest() {
        return "GUEST - Criando produto";
    }
}
