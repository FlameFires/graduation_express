package com.express.mapper.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TakeOrderDto {
    @NotBlank(message = "姓名不能为空")
    private String name;
    private String alias;
    @NotBlank(message = "手机号不能为空")
    private String phone;
    @NotBlank(message = "地址不能为空")
    private String address;
    @NotBlank(message = "取件码不能为空")
    private String tcode;
    @Min(value = 1, message = "站点不能为空")
    private Integer dpointid;
    @Min(value = 1, message = "快递类型不能为空")
    private Integer dtypeid;
}
