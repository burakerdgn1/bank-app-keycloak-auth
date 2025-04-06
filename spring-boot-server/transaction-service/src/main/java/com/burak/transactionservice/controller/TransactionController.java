package com.burak.transactionservice.controller;

import com.burak.transactionservice.dto.CreateTransactionRequest;
import com.burak.transactionservice.dto.TransactionDto;
import com.burak.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionService transactionService;

  @PostMapping
  public ResponseEntity<TransactionDto> createTransaction(@RequestBody CreateTransactionRequest request) {
    return ResponseEntity.ok(transactionService.createTransaction(request));
  }
}
