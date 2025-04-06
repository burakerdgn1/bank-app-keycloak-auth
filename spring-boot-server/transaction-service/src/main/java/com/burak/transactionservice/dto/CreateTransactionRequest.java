package com.burak.transactionservice.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateTransactionRequest {
  private Long accountId;
  private BigDecimal amount;
  private String type;
}

