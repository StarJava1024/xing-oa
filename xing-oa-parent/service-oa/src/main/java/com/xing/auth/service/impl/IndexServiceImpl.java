package com.xing.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xing.auth.service.IndexService;
import com.xing.auth.service.SysMenuService;
import com.xing.auth.service.SysUserService;
import com.xing.common.config.exception.XingException;
import com.xing.common.jwt.JwtHelper;
import com.xing.common.utils.MD5;
import com.xing.system.model.SysUser;
import com.xing.system.vo.LoginVO;
import com.xing.system.vo.RouterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xing
 * @since 2023-08-17
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public Map<String, Object> getLogin(LoginVO loginVo) {
        // 获取输入用户名和密码
        // 根据用户名查询数据库
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, loginVo.getUsername()));
        // 用户信息是否存在
        if (sysUser == null) {
            throw new XingException(201, "用户不存在");
        }
        // 判断密码
        // 数据库存密码（MD5）
        String passwordDB = sysUser.getPassword();
        // 获取输入的密码
        String passwordInput = MD5.encrypt(loginVo.getPassword());
        if (!passwordDB.equals(passwordInput)) {
            throw new XingException(201, "密码错误");
        }
        // 判断用户是否被禁用 1 可用 0 禁用
        if (sysUser.getStatus() == 0) {
            throw new XingException(201, "用户已经被禁用");
        }
        // 使用jwt根据用户id和用户名称生成token字符串
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        // 返回
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    @Override
    public Map<String, Object> info(HttpServletRequest request) {
        //1、从请求头获取用户信息（获取请求头token字符串）
        String token = request.getHeader("token");
        //2、从token字符串获取用户id 或者 用户名称
        String userId = JwtHelper.getUserId(token);
        //3、根据用户id查询数据库，把用户信息获取出来
        SysUser sysUser = sysUserService.getById(userId);
        //4、根据用户id获取用户可以操作菜单列表
        // 查询数据库动态构建路由结构，进行显示
        List<RouterVO> routerList = sysMenuService.getUserMenusByUserId(userId);
        //5、根据用户id获取用户可以操作按钮列表
        List<String> permsList = sysMenuService.getUserPermsByUserId(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", sysUser.getName());
        map.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        //返回用户可以操作菜单
        map.put("routers", routerList);
        //返回用户可以操作按钮
        map.put("buttons", permsList);
        return map;
    }
}
