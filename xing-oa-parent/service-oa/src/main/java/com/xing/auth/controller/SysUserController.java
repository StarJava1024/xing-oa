package com.xing.auth.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xing.auth.service.SysUserService;
import com.xing.common.result.Result;
import com.xing.common.utils.MD5;
import com.xing.system.model.SysUser;
import com.xing.system.qo.SysUserQO;
import com.xing.system.bo.SysUserBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xing
 * @since 2023-08-17
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
    }

    @ApiOperation("用户条件分页查询")
    @GetMapping
    public Result pageQueryUser(@RequestParam(name = "current", defaultValue = "1") Integer current,
                                @RequestParam(name = "limit", defaultValue = "10") Integer limit,
                                SysUserQO sysUserQO) {
        Page<SysUser> page = new Page<>(current, limit);
        IPage<SysUserBO> sysUserBO = sysUserService.getSysUserPage(sysUserQO, page);
        return Result.ok(sysUserBO);
    }

    /* @ApiOperation("用户条件分页查询")
    @GetMapping("{current}/{limit}")
    public Result pageQueryUser(@PathVariable Integer current, @PathVariable Integer limit, SysUserQO sysUserQueryVo) {
        Page<SysUser> page = new Page<>(current, limit);
        IPage<SysUser> sysUser = sysUserService.getSysUserPage(sysUserQueryVo, page);
        return Result.ok(sysUser);
    } */

    @ApiOperation(value = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id, @PathVariable Integer status) {
        boolean isSuccess = sysUserService.updateStatus(id, status);
        if(!isSuccess) {
            return Result.fail().message("状态更新失败");
        }
        return Result.ok().message("状态更新成功");
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("get/{id}")
    public Result getById(@PathVariable String id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @ApiOperation(value = "保存用户")
    @PostMapping("save")
    public Result save(@RequestBody SysUser user) {
        //密码进行加密，使用MD5
        String passwordMD5 = MD5.encrypt(user.getPassword());
        user.setPassword(passwordMD5);
        boolean isSuccess = sysUserService.save(user);
        if(!isSuccess) {
            return Result.fail().message("保存用户失败");
        }
        return Result.ok().message("保存用户成功");
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("update")
    public Result updateById(@RequestBody SysUser user) {
        boolean isSuccess = sysUserService.updateById(user);
        if(!isSuccess) {
            return Result.fail().message("更新用户失败");
        }
        return Result.ok().message("更新用户成功");
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        boolean isSuccess = sysUserService.removeById(id);
        if(!isSuccess) {
            return Result.fail().message("删除用户");
        }
        return Result.ok().message("删除用户");
    }

}

