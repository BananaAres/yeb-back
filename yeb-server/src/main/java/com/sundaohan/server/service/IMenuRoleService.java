package com.sundaohan.server.service;

import com.sundaohan.server.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sundaohan.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface IMenuRoleService extends IService<MenuRole> {
    /**
     * @Title updateMenuRole
     * @Description 更新角色菜单
     * @Author sundaohan
     * @Params [rid, mids]
     * @return com.sundaohan.server.pojo.RespBean
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
