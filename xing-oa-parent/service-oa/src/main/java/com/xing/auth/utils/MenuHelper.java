package com.xing.auth.utils;

import com.xing.system.model.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 菜单工具类
 * @Author: Wang Xing
 * @Date: 14:31 2023/8/18
 */
public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        // 创建list集合，用于最终数据
        List<SysMenu> trees = new ArrayList<>();
        // 把所有菜单数据进行遍历
        sysMenuList.forEach(sysMenu -> {
            if (sysMenu.getParentId().equals("0")) {
                trees.add(getChildren(sysMenu, sysMenuList));
            }
        });
        return trees;
    }

    public static SysMenu getChildren(SysMenu sysMenus, List<SysMenu> sysMenuList) {
        sysMenus.setChildren(new ArrayList<SysMenu>());
        // 遍历所有菜单数据，判断 id 和 parentId对应关系
        sysMenuList.forEach(sysMenu -> {
            if(sysMenus.getId().equals(sysMenu.getParentId())) {
                if (sysMenus.getChildren() == null) {
                    sysMenus.setChildren(new ArrayList<>());
                }
                sysMenus.getChildren().add(getChildren(sysMenu, sysMenuList));
            }
        });
        return sysMenus;
    }
}
