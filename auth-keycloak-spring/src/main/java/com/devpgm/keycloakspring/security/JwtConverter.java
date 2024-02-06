package com.devpgm.keycloakspring.security;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;


@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Map<String, Collection<String>> realmsAccess = source.getClaim("realm_access");
        Collection<String> roles = realmsAccess.get("roles");
        var grants = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList();

        return new JwtAuthenticationToken(source, grants);
    }
}

