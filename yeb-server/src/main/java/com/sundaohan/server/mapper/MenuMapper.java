package com.sundaohan.server.mapper;

import com.sundaohan.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * @Title getMenusByAdminId
     * @Description 根据用户id查询菜单列表
     * @Author sundaohan
     * @Params [id]
     * @return java.util.List<com.sundaohan.server.pojo.Menu>
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * @Title getMenusWithRole
     * @Description 根据角色获取菜单列表
     * @Author sundaohan
     * @Params []
     * @return java.util.List<com.sundaohan.server.pojo.Menu>
     */
    List<Menu> getMenusWithRole();
    /**
     * @Title getAllMenus
     * @Description 查询所有菜单
     * @Author sundaohan
     * @Params []
     * @return java.util.List<com.sundaohan.server.pojo.Menu>
     */
    List<Menu> getAllMenus();
}
