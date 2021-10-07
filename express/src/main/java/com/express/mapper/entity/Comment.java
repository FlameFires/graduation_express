package com.express.mapper.entity;

import lombok.*;
import org.springframework.context.annotation.Description;

@Description("评论")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
    private String name;
    private Integer customerId;
    private String content;
}
