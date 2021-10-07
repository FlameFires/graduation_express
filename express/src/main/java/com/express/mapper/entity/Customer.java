package com.express.mapper.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer extends BaseEntity {
    private String userName;
    private String phoneNum;
    private String address;
    private String headPic;
    private String password;
    private String pwdSalt;
    private String gender;
}
