package com.express.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
@ApiModel(description = "返回响应数据")
public class ResultMsg {
    @ApiModelProperty(value = "是否成功")
    private boolean status;
    @ApiModelProperty(value = "提示消息")
    private String msg;
    @ApiModelProperty(value = "附加数据")
    private Object data;

    public ResultMsg() {
    }

    public ResultMsg(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }
    public ResultMsg(Object data) {
        this.data = data;
    }
    public ResultMsg(boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ResultMsg ok() {
        return ResultMsg.builder().status(true).build();
    }

    public static ResultMsg ok(String msg, Object data) {
        return new ResultMsg(true, msg, data);
    }

    public static ResultMsg judge(Integer val, String errorMsg) {
        if (val < 1) {
            return error(errorMsg);
        } else {
            return ok();
        }
    }

    public static ResultMsg judge(List data, String errorMsg) {
        if (data == null || data.size() < 1) {
            return error(errorMsg);
        } else {
            return ok();
        }
    }

    public static ResultMsg judge(Object data, String errorMsg) {
        if (data == null) {
            return error(errorMsg);
        } else {
            return ok();
        }
    }

    public static ResultMsg judgeGo(List data, String errorMsg) {
        if (data == null || data.size() < 1) {
            return error(errorMsg);
        } else {
            return ok(data);
        }
    }

    public static ResultMsg judgeGo(List data, boolean isOk, String errorMsg) {
        if (data == null || data.size() < 1) {
            if(isOk){
                return ok(errorMsg);
            }else{
                return error(errorMsg);
            }
        }
        else {
            return ok(data);
        }
    }

    public static ResultMsg judgeGo(Object data, String errorMsg) {
        if (data == null) {
            return error(errorMsg);
        } else {
            return ok(data);
        }
    }

    public static ResultMsg ok(Object data) {
        return ok(null, data);
    }

    public static ResultMsg ok(String msg) {
        return ok(msg, null);
    }

    public static ResultMsg error(String msg, Object data) {
        return new ResultMsg(msg, data);
    }

    public static ResultMsg error(String msg) {
        return error(msg, null);
    }
}
