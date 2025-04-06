package com.burak.userservice.controller;

import com.burak.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
    return ResponseEntity.ok(authService.register(username, password));
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
    return ResponseEntity.ok(authService.login(username, password));
  }
}

