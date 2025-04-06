package com.burak.transactionservice.service;

import com.burak.transactionservice.dto.AccountDto;
import com.burak.transactionservice.dto.CreateTransactionRequest;
import com.burak.transactionservice.dto.TransactionDto;
import com.burak.transactionservice.dto.UpdateBalanceRequest;
import com.burak.transactionservice.entity.Transaction;
import com.burak.transactionservice.repository.TransactionRepository;
import com.burak.transactionservice.service.client.AccountClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final AccountClient accountClient;

  @Transactional
  public TransactionDto createTransaction(CreateTransactionRequest request) {
    // Fetch account details
    ResponseEntity<AccountDto> response = accountClient.getAccount(request.getAccountId());

    if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
      throw new RuntimeException("Account not found");
    }

    AccountDto account = response.getBody();

    BigDecimal newBalance = request.getType().equals("DEPOSIT")
      ? account.getBalance().add(request.getAmount())
      : account.getBalance().subtract(request.getAmount());

    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
      throw new RuntimeException("Insufficient funds");
    }

    UpdateBalanceRequest updateRequest = new UpdateBalanceRequest(newBalance);

    // Update balance
    ResponseEntity<Void> updateResponse = accountClient.updateBalance(request.getAccountId(), updateRequest);
    if (!updateResponse.getStatusCode().is2xxSuccessful()) {
      throw new RuntimeException("Failed to update account balance");
    }

    // Save transaction
    Transaction transaction = new Transaction(null, request.getAccountId(), request.getAmount(), request.getType());
    Transaction savedTransaction = transactionRepository.save(transaction);
    return mapToDto(savedTransaction);
  }


  private TransactionDto mapToDto(Transaction transaction) {
    TransactionDto dto = new TransactionDto();
    dto.setId(transaction.getId());
    dto.setAccountId(transaction.getAccountId());
    dto.setAmount(transaction.getAmount());
    dto.setType(transaction.getType());
    return dto;
  }
}
