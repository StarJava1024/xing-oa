package com.xing.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xing.common.result.Result;
import com.xing.auth.service.SysRoleService;
import com.xing.system.model.SysRole;
import com.xing.system.qo.SysRoleQO;
import com.xing.system.vo.AssginRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description: 系统角色
 * @Author: Wang Xing
 * @Date: 14:42 2023/7/21
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("获取角色")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable String userId) {
        Map<String,Object> map = sysRoleService.getRoleDataByUserId(userId);
        return Result.ok(map);
    }

    @ApiOperation("为用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVO assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok().message("用户分配角色成功");
    }

    @ApiOperation("分页查询角色")
    @GetMapping
    public Result pageQueryRole(@RequestParam(name = "current", defaultValue = "1") Integer current,
                                @RequestParam(name = "limit", defaultValue = "10") Integer limit,
                                SysRoleQO sysRoleQO) {
        Page<SysRole> page = new Page<>(current, limit);
        IPage<SysRole> sysRole = sysRoleService.getSysRolePage(sysRoleQO, page);
        return Result.ok(sysRole);
    }

    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody SysRole sysRole) {
        boolean isSuccess = sysRoleService.save(sysRole);
        if(!isSuccess) {
            return Result.fail().message("添加角色失败");
        }
        return Result.ok().message("添加角色成功");
    }

    @ApiOperation("根据id查询")
    @GetMapping("{id}")
    public Result getById(@PathVariable String id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    @ApiOperation("根据id修改")
    @PutMapping("update")
    public Result updateById(@RequestBody SysRole sysRole) {
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if(!isSuccess) {
            return Result.fail().message("角色修改失败");
        }
        return Result.ok().message("角色修改成功");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("{id}")
    public Result remove(@PathVariable String id) {
        boolean isSuccess = sysRoleService.removeById(id);
        if(!isSuccess) {
            return Result.fail().message("角色删除失败");
        }
        return Result.ok().message("角色删除成功");
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> ids) {
        boolean isSuccess = sysRoleService.removeByIds(ids);
        if(!isSuccess) {
            return Result.fail().message("批量删除角色失败");
        }
        return Result.ok().message("批量删除角色成功");
    }

}
