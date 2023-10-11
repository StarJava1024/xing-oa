package com.xing.system.qo;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class SysLoginLogQO {
	
	@ApiModelProperty(value = "用户账号")
	private String username;

	private String createTimeBegin;
	private String createTimeEnd;

}

