package com.express.mapper.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@ToString
@AllArgsConstructor
public class DeliveryType {

    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createDate;
    private String name;
}
