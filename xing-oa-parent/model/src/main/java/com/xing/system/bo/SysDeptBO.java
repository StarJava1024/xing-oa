package com.xing.system.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xing.base.BaseEntity;
import com.xing.common.annotation.PrivacyEncrypt;
import com.xing.common.enums.PrivacyTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "部门")
public class SysDeptBO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "部门名称")
	private String name;

	@ApiModelProperty(value = "上级部门id")
	private Long parentId;

	@ApiModelProperty(value = "树结构")
	private String treePath;

	@ApiModelProperty(value = "排序")
	private Integer sortValue;

	@ApiModelProperty(value = "负责人")
	private String leader;

	@ApiModelProperty(value = "电话")
	@PrivacyEncrypt(type = PrivacyTypeEnum.PHONE)
	private String phone;

	@ApiModelProperty(value = "状态（1正常 0停用）")
	private Integer status;

	@ApiModelProperty(value = "下级部门")
	private List<SysDeptBO> children;

}