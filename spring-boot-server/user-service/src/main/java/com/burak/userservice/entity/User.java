package com.burak.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // "user" is a reserved keyword in PostgreSQL, so we use "users"
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;
}

