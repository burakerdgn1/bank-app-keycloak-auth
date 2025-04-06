package com.burak.accountservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
public class UpdateBalanceRequest {
  private BigDecimal newBalance;

  public UpdateBalanceRequest() {}

  public UpdateBalanceRequest(BigDecimal newBalance) {
    this.newBalance = newBalance;
  }

}

