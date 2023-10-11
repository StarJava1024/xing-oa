package com.xing.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xing.system.model.SysMenu;
import com.xing.system.vo.AssginMenuVO;
import com.xing.system.vo.RouterVO;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author xing
 * @since 2023-08-18
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    Integer removeMenuById(String id);

    List<SysMenu> getMenuByRoleId(String roleId);

    boolean doAssign(AssginMenuVO assginMenuVo);

    List<RouterVO> getUserMenusByUserId(String userId);

    List<String> getUserPermsByUserId(String userId);
}
