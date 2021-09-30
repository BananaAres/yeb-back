package com.sundaohan.server.mapper;

import com.sundaohan.server.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
    /**
     * @Title insertRecord
     * @Description 更新角色菜单
     * @Author sundaohan
     * @Params [rid, mids]
     * @return java.lang.Integer
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
