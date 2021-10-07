package com.express.mapper.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("站点类")
public class Station {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("站点名")
    private String station_name;
    @ApiModelProperty("站点人数")
    private Integer person_num;
    @ApiModelProperty("联系号码")
    private String contact;
    @ApiModelProperty("快件数量")
    private Integer express_num;
}
