package com.sundaohan.server.service;

import com.sundaohan.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface IMenuService extends IService<Menu> {
    /**
     * @Title getMenusByAdminId
     * @Description 根据用户id查询用户菜单列表
     * @Author sundaohan
     * @Params []
     * @return java.util.List<com.sundaohan.server.pojo.Menu>
     */

    List<Menu> getMenusByAdminId();

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
