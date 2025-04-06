package com.burak.transactionservice.service.client;

import com.burak.transactionservice.dto.AccountDto;
import com.burak.transactionservice.dto.UpdateBalanceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(name = "account-service", url = "http://localhost:8088/accounts", fallback = AccountClientFallback.class)
@Primary
public interface AccountClient {

  @GetMapping("/{id}")
  ResponseEntity<AccountDto> getAccount(@PathVariable Long id);

  @PutMapping(value = "/{id}/update-balance", consumes = "application/json")
  ResponseEntity<Void> updateBalance(@PathVariable Long id, @RequestBody UpdateBalanceRequest request);


}
