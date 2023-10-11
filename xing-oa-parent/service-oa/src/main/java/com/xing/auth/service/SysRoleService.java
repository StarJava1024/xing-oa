package com.xing.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xing.system.model.SysRole;
import com.xing.system.qo.SysRoleQO;
import com.xing.system.vo.AssginRoleVO;

import java.util.Map;

/**
 * @Description:
 * @Author: Wang Xing
 * @Date: 14:37 2023/7/21
 */
public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> getSysRolePage(SysRoleQO sysRoleQO, IPage<SysRole> page);

    Map<String, Object> getRoleDataByUserId(String userId);

    void doAssign(AssginRoleVO assginRoleVo);
}
