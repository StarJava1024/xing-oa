package com.xing.auth.service.impl;

import com.xing.auth.mapper.SysUserRoleMapper;
import com.xing.auth.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xing.system.model.SysRole;
import com.xing.system.model.SysUserRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色 服务实现类
 *
 * @author xing
 * @since 2023-08-17
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Override
    public List<SysRole> getAssignRoleList(String userId) {
        return baseMapper.getAssignRoleList(userId);
    }
}
