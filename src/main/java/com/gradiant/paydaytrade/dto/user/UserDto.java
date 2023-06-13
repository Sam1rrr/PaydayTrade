package com.gradiant.paydaytrade.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class UserDto {
    private String name;
    private String surname;
    private String mail;
    private String number;
    private String password;
    private BigDecimal balance;
    private String picture;
}
