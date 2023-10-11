package com.xing.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xing.system.model.SysUser;
import com.xing.system.qo.SysUserQO;
import com.xing.system.bo.SysUserBO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xing
 * @since 2023-08-17
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<SysUserBO> getSysUserPage(@Param("page") Page<SysUser> page, @Param("sysUserQO") SysUserQO sysUserQO);
}
