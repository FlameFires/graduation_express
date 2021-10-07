package com.express.mapper.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MakeMoney  {
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createDate;
    private Integer customerId;
    private BigDecimal money;
}