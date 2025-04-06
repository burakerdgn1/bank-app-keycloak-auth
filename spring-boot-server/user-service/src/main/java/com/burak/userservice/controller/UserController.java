package com.burak.userservice.controller;

import com.burak.userservice.entity.User;
import com.burak.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
    userService.registerUser(username, password);
    return ResponseEntity.ok("User registered successfully");
  }
}

