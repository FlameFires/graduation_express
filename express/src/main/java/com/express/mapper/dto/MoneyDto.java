package com.express.mapper.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDto {
    BigDecimal total;
    BigDecimal today;
}
