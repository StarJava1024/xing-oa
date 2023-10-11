package com.xing.auth.controller;

import com.xing.auth.service.IndexService;
import com.xing.common.result.Result;
import com.xing.system.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 后台登录管理
 * @Author: Wang Xing
 * @Date: 18:15 2023/7/25
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 登录
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVO loginVo) {
        /* Map<String, Object> map = new HashMap<>();
        map.put("token","admin-token"); */

        Map<String,Object> map = indexService.getLogin(loginVo);
        return Result.ok(map);

    }
    @ApiOperation("登录")
    // @PostMapping("login")
    public Result login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);

    }

    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public Result info(HttpServletRequest request) {

        Map<String,Object> map = indexService.info(request);
        return Result.ok(map);
    }
    @ApiOperation("获取用户信息")
    // @GetMapping("info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", "admin");
        map.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return Result.ok(map);
    }

    /**
     * 退出
     * @return
     */
    @ApiOperation("退出")
    @PostMapping("logout")
    public Result logout(){
        return Result.ok().message("退出成功");
    }

}
