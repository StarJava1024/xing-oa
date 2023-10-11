package com.xing.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xing.system.model.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author xing
 * @since 2023-08-18
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    Integer deleteMenuById(@Param("id") String id);

    List<SysMenu> getMenusByUserId(@Param("userId") String userId);
}
