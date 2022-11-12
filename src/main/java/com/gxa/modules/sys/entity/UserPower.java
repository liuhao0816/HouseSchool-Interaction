package com.gxa.modules.sys.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class UserPower {
    private int userId;
    private String userName;
    private Set<String> powers;
}
