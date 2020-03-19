package com.artiow.cbr.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CentralBankRate {

    private String id;
    private Integer numCode;
    private String charCode;
    private String name;
    private BigDecimal nominal;
    private BigDecimal value;
}
