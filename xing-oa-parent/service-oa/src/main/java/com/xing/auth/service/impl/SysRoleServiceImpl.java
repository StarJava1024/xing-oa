package com.xing.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xing.auth.mapper.SysRoleMapper;
import com.xing.auth.service.SysRoleService;
import com.xing.auth.service.SysUserRoleService;
import com.xing.system.model.SysRole;
import com.xing.system.model.SysUserRole;
import com.xing.system.vo.AssginRoleVO;
import com.xing.system.qo.SysRoleQO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 角色 Service
 * @Author: Wang Xing
 * @Date: 14:38 2023/7/21
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public IPage<SysRole> getSysRolePage(SysRoleQO sysRoleQO, IPage<SysRole> page) {
        return baseMapper.getSysRolePage(page, sysRoleQO);
    }

    // 查询所有角色和当前用户所属角色
    @Override
    public Map<String, Object> getRoleDataByUserId(String userId) {
        // 查询所有角色
        List<SysRole> roles = baseMapper.selectList(null);
        // 查询当前用户所属角色
        List<SysRole> assignRoles = sysUserRoleService.getAssignRoleList(userId);

        // 把得到两部分数据封装map集合返回
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("allRolesList", roles);
        roleMap.put("assignRoleList", assignRoles);
        return roleMap;
    }

    // 为用户分配角色
    @Override
    @Transactional
    public void doAssign(AssginRoleVO assginRoleVo) {
        //把用户之前分配角色数据删除，用户角色关系表里面，根据userid删除
        sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, assginRoleVo.getUserId()));
        //重新进行分配
        List<String> roleIds = assginRoleVo.getRoleIdList();
        List<SysUserRole> userRoles = roleIds.stream().map(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            return sysUserRole;
        }).collect(Collectors.toList());
        sysUserRoleService.saveBatch(userRoles);
    }
}
