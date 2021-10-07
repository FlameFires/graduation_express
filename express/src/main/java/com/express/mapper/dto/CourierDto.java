package com.express.mapper.dto;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierDto {
    private String name;
    private String gender;
}

