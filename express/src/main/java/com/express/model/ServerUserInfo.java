package com.express.model;

import lombok.*;

import java.util.Date;

/**
 * 用户登录信息
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerUserInfo {
    private Integer id;
    private Object userObject;
    private Date loginDate = new Date();
    private UserType userType;
}

