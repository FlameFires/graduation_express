package com.express.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 验证码类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCode {
    private String code;
    private byte[] imgBytes;
    private long expireTime;
}