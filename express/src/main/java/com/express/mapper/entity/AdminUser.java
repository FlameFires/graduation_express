package com.express.mapper.entity;

import lombok.*;


//管理员
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser extends BaseEntity {
    private String account;
    private String password;
    private String pwdSalt;
//    private Integer loginErrorNum;
//    private Date lastLoginDate;
}
