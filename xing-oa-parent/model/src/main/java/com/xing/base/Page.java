package com.xing.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 页
 * @Author: Wang Xing
 * @Date: 17:11 2023/7/24
 */
@Data
public class Page {
    @ApiModelProperty("当前页号")
    private Long pageNum;

    @ApiModelProperty("每页显示记录数")
    private Long pageSize;
}
