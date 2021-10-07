package com.express.mapper.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    @NotBlank(message = "标题不能为空")
    @Length(min = 2,message = "字数太少了")
    private String title;
    @NotBlank(message = "内容不能为空")
    @Length(min = 10,message = "字数太少了")
    private String content;
}
