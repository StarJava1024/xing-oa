package com.xing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xing.auth.mapper.SysRoleMapper;
import com.xing.system.model.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestMpDemo1 {

    @Autowired
    private SysRoleMapper mapper;

    // 查询所有记录
    @Test
    public void getAll(){
        List<SysRole> list = mapper.selectList(null);
        list.forEach(System.out::println);
    }

    // 添加用户信息
    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");

        int rows = mapper.insert(sysRole); // 影响的行数
        System.out.println("影响的行数：" + rows);
        System.out.println(sysRole.getId());
    }

    // 根据ID更新数据
    @Test
    public void update(){
        // 根据id查询
        SysRole role = mapper.selectById(12);
        // 设置修改值
        role.setRoleCode("role");
        role.setDescription("管理员2");
        // 调用方法实现最终的修改
        int rows = mapper.updateById(role);
        System.out.println("影响的行数：" + rows);
    }

    // 根据ID删除数据
    @Test
    public void delete(){
        int rows = mapper.deleteById(11);
    }

    // 批量删除
    @Test
    public void delete1(){
        mapper.deleteBatchIds(Arrays.asList(9, 13));
    }

    // 条件查询
    @Test
    public void testQuery(){
        // 创建QueryWrapper对象，调用方法封装条件
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", "总经理");
        // 调用mp方法实现查询操作
        List<SysRole> list = mapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    // 查询信息
    @Test
    public void testQuery2(){
        // 创建QueryWrapper对象，调用方法封装条件
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("SysRole::getRoleName", "总经理");
        // 调用mp方法实现查询操作
        List<SysRole> list = mapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

}
