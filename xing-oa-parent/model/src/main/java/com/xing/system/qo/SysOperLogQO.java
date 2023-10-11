package com.xing.system.qo;

import lombok.Data;

@Data
public class SysOperLogQO {

	private String title;
	private String operName;

	private String createTimeBegin;
	private String createTimeEnd;

}

