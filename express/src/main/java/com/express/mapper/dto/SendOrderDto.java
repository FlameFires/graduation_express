package com.express.mapper.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SendOrderDto {
    @NotBlank(message = "寄件人姓名不能为空")
    private String sname;
    @NotBlank(message = "收件人姓名不能为空")
    private String sphone;
    @NotBlank(message = "寄件手机号不能为空")
    private String rname;
    @NotBlank(message = "收件手机号不能为空")
    private String rphone;
    @NotBlank(message = "寄件地址不能为空")
    private String taddress;
    @NotBlank(message = "收件地址不能为空")
    private String saddress;
    @NotBlank(message = "取件时间不能为空")
    private String tdate;
    @Min(value = 1, message = "站点不能为空")
    private Integer dpointid;
    @Min(value = 1, message = "快递类型不能为空")
    private Integer dtypeid;
//    @Min(value = 0, message = "物品类型设置错误")
//    private Integer gid; //物品类型
}
