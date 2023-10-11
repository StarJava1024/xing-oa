package com.xing.system.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xing.base.BaseEntity;
import com.xing.common.annotation.PrivacyEncrypt;
import com.xing.common.enums.PrivacyTypeEnum;
import com.xing.system.model.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用户")
// @TableName("sys_user")
public class SysUserBO extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "手机")
	@PrivacyEncrypt(type = PrivacyTypeEnum.PHONE)
	private String phone;

	@ApiModelProperty(value = "头像地址")
	private String headUrl;

	@ApiModelProperty(value = "部门id")
	private Long deptId;

	@ApiModelProperty(value = "岗位id")
	private Long postId;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "openId")
	private String openId;

	@ApiModelProperty(value = "状态（1：正常 0：停用）")
	private Integer status;


	@ApiModelProperty(value = "角色列表")
	private List<SysRole> roleList;
	@ApiModelProperty(value = "岗位名称")
	private String postName;
	@ApiModelProperty(value = "部门名称")
	private String deptName;
}

