package com.burak.transactionservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
  private Long id;
  private String ownerName;
  private BigDecimal balance;
}
