package com.burak.transactionservice.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionDto {
  private Long id;
  private Long accountId;
  private BigDecimal amount;
  private String type;
}
