package com.xing.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xing.system.model.SysUser;
import com.xing.system.qo.SysUserQO;
import com.xing.system.bo.SysUserBO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xing
 * @since 2023-08-17
 */
public interface SysUserService extends IService<SysUser> {

    IPage<SysUserBO> getSysUserPage(SysUserQO sysUserQO, Page<SysUser> page);

    boolean updateStatus(String id, Integer status);
}
