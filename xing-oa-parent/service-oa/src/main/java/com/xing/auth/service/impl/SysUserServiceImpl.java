package com.xing.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xing.auth.mapper.SysUserMapper;
import com.xing.auth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xing.system.model.SysUser;
import com.xing.system.qo.SysUserQO;
import com.xing.system.bo.SysUserBO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xing
 * @since 2023-08-17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public IPage<SysUserBO> getSysUserPage(SysUserQO sysUserQO, Page<SysUser> page) {
        return baseMapper.getSysUserPage(page, sysUserQO);
    }

    @Override
    public boolean updateStatus(String id, Integer status) {
        //根据userid查询用户对象
        SysUser sysUser = baseMapper.selectById(id);
        //设置修改状态值
        sysUser.setStatus(status);
        //调用方法进行修改
        int total = baseMapper.updateById(sysUser);
        if (total > 0) {
            return true;
        } else {
            return false;
        }
    }

}
