package com.xing.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xing.system.model.SysRole;
import com.xing.system.qo.SysRoleQO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    IPage<SysRole> getSysRolePage(IPage<SysRole> page, @Param("sysRoleQO") SysRoleQO sysRoleQO);
}
