package com.express.mapper.dto;

import com.express.mapper.entity.BaseEntity;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseEntity {
    private String account;
    private String userName;
    private String headPic;
    private String phoneNum;
    private String password;
    private String pwdSalt;
    private String gender;
}
