package com.express.mapper.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Description;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Description("帖子类")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {
    private Integer customerId; // 客户id
    private String title;
    private String content;
}
