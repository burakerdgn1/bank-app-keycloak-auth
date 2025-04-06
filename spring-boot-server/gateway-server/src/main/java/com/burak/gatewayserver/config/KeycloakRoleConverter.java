package com.burak.gatewayserver.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
  @Override
  public Collection<GrantedAuthority> convert(Jwt source) {
    Map<String, Object> resourceAccess = (Map<String, Object>) source.getClaims().get("resource_access");
    if (resourceAccess == null || resourceAccess.isEmpty()) {
      return new ArrayList<>();
    }

    // Extract roles for the client application (e.g., "burak-callcenter-cc")
    Map<String, Object> clientAccess = (Map<String, Object>) resourceAccess.get("burak-callcenter-cc");
    if (clientAccess == null || clientAccess.isEmpty()) {
      return new ArrayList<>();
    }

    Collection<GrantedAuthority> returnValue = ((List<String>) clientAccess.get("roles"))
      .stream()
      .map(roleName -> "ROLE_" + roleName) // Prefix roles with "ROLE_"
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());

    return returnValue;
  }
}

