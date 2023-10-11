package com.xing.common.config.exception;

import com.xing.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Description: 自定义异常
 * @Author: Wang Xing
 * @Date: 16:55 2023/7/25
 */
@Data
@ToString
@AllArgsConstructor
public class XingException extends RuntimeException {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("描述信息")
    private String msg;

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public XingException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "XingException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

    /* try{
        int i = 10/0;
    } catch() {
        throw new XingException(2001, "执行自定义异常..");
    } */
}
