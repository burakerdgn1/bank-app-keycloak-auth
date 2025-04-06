package com.burak.transactionservice.service.client;

import com.burak.transactionservice.dto.AccountDto;
import com.burak.transactionservice.dto.UpdateBalanceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountClientFallback implements AccountClient {

  @Override
  public ResponseEntity<AccountDto> getAccount(Long id) {
    System.out.println("Fallback: Account service is unavailable.");
    return ResponseEntity.ok(new AccountDto(id, "Unknown", BigDecimal.ZERO));
  }


  @Override
  public ResponseEntity<Void> updateBalance(Long id, UpdateBalanceRequest request) {
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
  }
}
