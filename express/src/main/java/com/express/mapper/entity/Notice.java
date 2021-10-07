package com.express.mapper.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notice{
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createDate;
    @Length(min = 2,message = "字数太少了")
    private  String  title;
    @Length(min = 5,message = "字数太少了")
    private String content;
    @Pattern(regexp = "^(活动|公告)$",message = "类型输入有误")
    private String type;
    private String link;
}
