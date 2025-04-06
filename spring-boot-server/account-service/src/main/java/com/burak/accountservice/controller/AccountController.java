package com.burak.accountservice.controller;

import com.burak.accountservice.dto.AccountDto;
import com.burak.accountservice.dto.CreateAccountRequest;
import com.burak.accountservice.dto.UpdateBalanceRequest;
import com.burak.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;

  @PostMapping
  public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest request) {
    return ResponseEntity.ok(accountService.createAccount(request));
  }

  @PutMapping("/{id}/update-balance")
  public ResponseEntity<AccountDto> updateBalance(@PathVariable Long id, @RequestBody UpdateBalanceRequest request) {
    return ResponseEntity.ok(accountService.updateBalance(id, request.getNewBalance()));
  }


  @GetMapping("/{id}")
  public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
    return ResponseEntity.ok(accountService.getAccount(id));
  }
}
