package com.express.mapper.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class TagList {
    private List dpoints;
    private List dtypes;
    private List times;
    private List gtypes;
}
