package com.sundaohan.server.mapper;

import com.sundaohan.server.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * @Title getRoles
     * @Description 根据用户id查询角色列表
     * @Author sundaohan
     * @Params [adminId]
     * @return java.util.List<com.sundaohan.server.pojo.Role>
     */
    List<Role> getRoles(Integer adminId);
}
