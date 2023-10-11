package com.xing.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xing.system.model.SysRole;
import com.xing.system.model.SysUserRole;

import java.util.List;

/**
 * 用户角色 服务类
 *
 * @author xing
 * @since 2023-08-17
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    List<SysRole> getAssignRoleList(String userId);
}
