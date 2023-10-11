package com.xing.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xing.auth.mapper.SysMenuMapper;
import com.xing.auth.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xing.auth.service.SysRoleMenuService;
import com.xing.auth.utils.MenuHelper;
import com.xing.system.model.SysMenu;
import com.xing.system.model.SysRoleMenu;
import com.xing.system.vo.AssginMenuVO;
import com.xing.system.vo.MetaVO;
import com.xing.system.vo.RouterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author xing
 * @since 2023-08-18
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> findNodes() {
        // 查询所有菜单数据, 并以树形结构返回
        return MenuHelper.buildTree(baseMapper.selectList(
                new LambdaQueryWrapper<SysMenu>()
                        .eq(SysMenu::getStatus, 1)
                        .orderByAsc(SysMenu::getSortValue)
                        .orderByAsc(SysMenu::getCreateTime)
        ));
    }

    @Override
    public Integer removeMenuById(String id) {
        // 判断当前菜单是否有下一层菜单
        return baseMapper.deleteMenuById(id);
    }

    @Override
    public List<SysMenu> getMenuByRoleId(String roleId) {
        // 查询所有菜单
        List<SysMenu> sysMenus = baseMapper.selectList(
                new LambdaQueryWrapper<SysMenu>()
                        .eq(SysMenu::getStatus, 1)
                        .orderByAsc(SysMenu::getSortValue)
                        .orderByAsc(SysMenu::getCreateTime)
        );
        // 根据角色在角色菜单关系表中查询所选中的菜单
        List<String> menuIds =
                sysRoleMenuService.list(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId))
                        .stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        sysMenus.forEach(item -> {
            if (menuIds.contains(item.getId())) {
                item.setChosen(true);
            } else {
                item.setChosen(false);
            }
        });
        // 返回规定树形显示格式菜单列表
        return MenuHelper.buildTree(sysMenus);
    }

    @Override
    @Transactional
    public boolean doAssign(AssginMenuVO assginMenuVo) {
        // 根据角色id 删除菜单角色表  分配数据
        sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, assginMenuVo.getRoleId()));
        // 从参数里面获取角色新分配菜单id列表, 进行遍历，把每个id数据添加菜单角色表
        List<SysRoleMenu> roleMenus = assginMenuVo.getMenuIds().stream().map(menuId -> {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(assginMenuVo.getRoleId());
            return roleMenu;
        }).collect(Collectors.toList());
        return sysRoleMenuService.saveBatch(roleMenus);
    }

    @Override
    public List<RouterVO> getUserMenusByUserId(String userId) {
        // 1 判断当前用户是否是管理员 userId=1是管理员
        // 1.1 如果是管理员，查询所有菜单列表
        List<SysMenu> sysMenus = new ArrayList<>();
        if (userId.equals("1")) {
            // 查询所有菜单列表
            sysMenus = baseMapper.selectList(
                    new LambdaQueryWrapper<SysMenu>()
                            .eq(SysMenu::getStatus, 1)
                            .orderByAsc(SysMenu::getSortValue)
                            .orderByAsc(SysMenu::getCreateTime)
            );
            // sysMenus = this.findNodes();
        } else {
            // 1.2 如果不是管理员，根据userId查询可以操作菜单列表
            sysMenus = baseMapper.getMenusByUserId(userId);
        }
        // 2 把查询出来数据列表-构建成框架要求的路由结构
        // 使用菜单操作工具类构建树形结构
        List<SysMenu> sysMenuList = MenuHelper.buildTree(sysMenus);
        // 构建成框架要求的路由结构
        List<RouterVO> routerVo = this.buildRouter(sysMenuList);
        return routerVo;
    }

    // 构建成框架要求的路由结构
    private List<RouterVO> buildRouter(List<SysMenu> menus) {
        // 创建list集合，存储最终数据
        List<RouterVO> routers = new ArrayList<>();
        // menus遍历
        for(SysMenu menu : menus) {
            RouterVO router = new RouterVO();
            router.setHidden(false);
            router.setAlwaysShow(false);
            router.setPath(getRouterPath(menu));
            router.setComponent(menu.getComponent());
            router.setMeta(new MetaVO(menu.getName(), menu.getIcon()));
            //下一层数据部分
            List<SysMenu> children = menu.getChildren();
            if(menu.getType() == 1) {
                //加载出来下面隐藏路由
                List<SysMenu> hiddenMenuList = children.stream()
                        .filter(item -> !StringUtils.isEmpty(item.getComponent()))
                        .collect(Collectors.toList());
                for(SysMenu hiddenMenu : hiddenMenuList) {
                    RouterVO hiddenRouter = new RouterVO();
                    //true 隐藏路由
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(getRouterPath(hiddenMenu));
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    hiddenRouter.setMeta(new MetaVO(hiddenMenu.getName(), hiddenMenu.getIcon()));

                    routers.add(hiddenRouter);
                }
            } else {
                if(!CollectionUtils.isEmpty(children)) {
                    if(children.size() > 0) {
                        router.setAlwaysShow(true);
                    }
                    // 递归
                    router.setChildren(buildRouter(children));
                }
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu) {
        String routerPath = "/" + menu.getPath();
        if(!menu.getParentId().equals("0")) {
            routerPath = menu.getPath();
        }
        return routerPath;
    }

    // 5 根据用户id获取用户可以操作按钮列表
    @Override
    public List<String> getUserPermsByUserId(String userId) {
        List<SysMenu> sysMenus = new ArrayList<>();
        if(userId.equals("1")) {
            // 查询所有菜单列表
            sysMenus = baseMapper.selectList(
                    new LambdaQueryWrapper<SysMenu>()
                            .eq(SysMenu::getStatus, 1)
                            .orderByAsc(SysMenu::getSortValue)
                            .orderByAsc(SysMenu::getCreateTime)
            );
        } else {
            // 如果不是管理员，根据userId查询可以操作按钮列表
            sysMenus = baseMapper.getMenusByUserId(userId);
        }
        //3 从查询出来的数据里面，获取可以操作按钮值的list集合，返回
        return sysMenus.stream()
                .filter(item -> item.getType() == 2)
                .map(SysMenu::getPerms)
                .collect(Collectors.toList());
    }

}
