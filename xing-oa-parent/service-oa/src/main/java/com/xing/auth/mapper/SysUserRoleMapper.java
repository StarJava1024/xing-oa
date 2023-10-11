package com.xing.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xing.system.model.SysRole;
import com.xing.system.model.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author xing
 * @since 2023-08-17
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysRole> getAssignRoleList(@Param("userId") String userId);
}
