package com.sundaohan.server.mapper;

import com.sundaohan.server.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sundaohan.server.pojo.RespBean;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    /**
     * @Title updateAdminRole
     * @Description 更新操作员角色
     * @Author sundaohan
     * @Params [adminId, rids]
     * @return com.sundaohan.server.pojo.RespBean
     */
    Integer updateAdminRole(Integer adminId, Integer[] rids);
}
