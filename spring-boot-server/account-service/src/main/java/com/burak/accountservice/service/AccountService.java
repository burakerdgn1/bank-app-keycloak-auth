package com.burak.accountservice.service;

import com.burak.accountservice.dto.AccountDto;
import com.burak.accountservice.dto.CreateAccountRequest;
import com.burak.accountservice.entity.Account;
import com.burak.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {
  private final AccountRepository accountRepository;

  public AccountDto createAccount(CreateAccountRequest request) {
    Account account = new Account(null, request.getOwnerName(), request.getBalance());
    Account savedAccount = accountRepository.save(account);
    return mapToDto(savedAccount);
  }

  /*
  public AccountDto createAccount(CreateAccountRequest request) {
    // 1. Register user in Keycloak and get user ID
    String keycloakId = keycloakService.registerUser(request.getOwnerName(), request.getOwnerName() + "@mail.com", "password123");

    // 2. Create Account and link it with Keycloak ID
    Account account = new Account(null, request.getOwnerName(), request.getBalance(), keycloakId);
    Account savedAccount = accountRepository.save(account);

    return mapToDto(savedAccount);
  }
  */

  public AccountDto getAccount(Long accountId) {
    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new RuntimeException("Account not found"));
    return mapToDto(account);
  }

  public AccountDto updateBalance(Long accountId, BigDecimal newBalance) {
    Account account = accountRepository.findById(accountId)
      .orElseThrow(() -> new RuntimeException("Account not found"));
    account.setBalance(newBalance);
    Account updatedAccount = accountRepository.save(account);
    return mapToDto(updatedAccount);
  }


  private AccountDto mapToDto(Account account) {
    AccountDto dto = new AccountDto();
    dto.setId(account.getId());
    dto.setOwnerName(account.getOwnerName());
    dto.setBalance(account.getBalance());
    return dto;
  }
}
