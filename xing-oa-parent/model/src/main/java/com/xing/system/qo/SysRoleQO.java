//
//
package com.xing.system.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色查询实体
 * </p>
 *
 * @author wx
 * @since 2023-07-24
 */
@Data
public class SysRoleQO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("角色名称")
	private String roleName;

}

