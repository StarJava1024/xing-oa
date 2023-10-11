package com.xing.auth.controller;


import com.xing.auth.service.SysMenuService;
import com.xing.common.result.Result;
import com.xing.system.model.SysMenu;
import com.xing.system.vo.AssginMenuVO;
import io.swagger.annotations.ApiOperation;
import org.apache.velocity.shaded.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author xing
 * @since 2023-08-18
 */
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("查询所有菜单和角色分配的菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<SysMenu> list = sysMenuService.getMenuByRoleId(roleId);
        return Result.ok(list);
    }

    @ApiOperation("角色分配菜单")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVO assginMenuVo) {
        boolean isSuccess = sysMenuService.doAssign(assginMenuVo);
        if (!isSuccess) {
            return Result.fail().message("角色分配菜单失败");
        }
        return Result.ok().message("角色分配菜单成功");
    }

    @ApiOperation(value = "菜单列表")
    @GetMapping("findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu permission) {
        /* boolean pathString = StringUtils.substringMatch(permission.getPath(), 0, "/");
        if (!pathString) {
            return Result.fail().message("菜单新增失败，路由地址前需要加 / ");
        } */
        boolean isSuccess = sysMenuService.save(permission);
        if (!isSuccess) {
            return Result.fail().message("菜单新增失败，添加到数据库错误");
        }
        return Result.ok().message("菜单新增成功");
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody SysMenu permission) {
        /* boolean pathString = StringUtils.substringMatch(permission.getPath(), 0, "/");
        if (!pathString) {
            return Result.fail().message("菜单修改失败");
        } */
        boolean isSuccess = sysMenuService.updateById(permission);
        if (!isSuccess) {
            return Result.fail().message("菜单修改失败");
        }
        return Result.ok().message("菜单修改成功");
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        Integer count = sysMenuService.removeMenuById(id);
        if (count < 1) {
            return Result.fail().message("菜单删除失败");
        }
        return Result.ok().message("菜单删除成功");
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd");

    @PostMapping("/upload")
    public Result fileUpload(MultipartFile file, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String filename = file.getOriginalFilename();       // 文件名
        String suffix = FilenameUtils.getExtension(filename);   // 文件后缀
        if (!filename.endsWith(".pdf")) {
            return Result.fail().message("文件类型不正确");
        }
        String format = simpleDateFormat.format(new Date());
        String realPath = request.getServletContext().getRealPath("/") + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String newName = UUID.randomUUID() + ".pdf";
        try {
            file.transferTo(new File(folder, newName));
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + format + "/" + newName;
            result.put("url", url);
            return Result.ok(result).message("上传成功");
        } catch (IOException e) {
            return Result.fail().message(e.getMessage());
        }
    }

}