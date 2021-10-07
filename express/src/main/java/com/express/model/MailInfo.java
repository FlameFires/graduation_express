package com.express.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NonNull
public class MailInfo {

    @NotNull(message = "cid 不能为空")
    private String cid;

    @NotNull(message = "aaddress 不能为空")
    private String aaddress;

    @NotNull(message = "aname 不能为空")
    private String aname;

    @NotNull(message = "saddress 不能为空")
    private String saddress;

    @NotNull(message = "sname 不能为空")
    private String sname;

    @NotNull(message = "weight 不能为空")
    private Integer weight;

    @NotNull(message = "cdate 不能为空")
    private Time cdate;
}
