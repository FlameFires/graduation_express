package com.express.mapper.entity;

import lombok.*;

// 送单员
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Courier extends BaseEntity {
    private String userName;
    private String headPic;
    private String phoneNum;
    private String password;
    private String pwdSalt;
    private String gender;
}
