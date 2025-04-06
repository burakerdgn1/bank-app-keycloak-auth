package com.burak.accountservice.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakService {

  //private final RestTemplate restTemplate;

  private String keycloakServerUrl;

  private String realm;

  private String adminToken;

  public String registerUser(String username, String email, String password) {
    String url = keycloakServerUrl + "/admin/realms/" + realm + "/users";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(adminToken);
    headers.set("Content-Type", "application/json");

    Map<String, Object> user = Map.of(
      "username", username,
      "email", email,
      "enabled", true,
      "credentials", new Object[] {
        Map.of("type", "password", "value", password, "temporary", false)
      }
    );

    HttpEntity<Map<String, Object>> request = new HttpEntity<>(user, headers);
    //ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

    /*if (response.getStatusCode().is2xxSuccessful()) {
      return "User registered successfully";
    } else {
      throw new RuntimeException("Failed to register user in Keycloak");
    }*/
    return "User registered successfully";
  }
}

