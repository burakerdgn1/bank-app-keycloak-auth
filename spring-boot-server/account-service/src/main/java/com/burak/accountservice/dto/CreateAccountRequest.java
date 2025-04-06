package com.burak.accountservice.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateAccountRequest {
  private String ownerName;
  private BigDecimal balance;
}
