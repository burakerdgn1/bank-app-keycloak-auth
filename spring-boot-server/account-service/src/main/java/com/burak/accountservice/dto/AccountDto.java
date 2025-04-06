package com.burak.accountservice.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountDto {
  private Long id;
  private String ownerName;
  private BigDecimal balance;
}
