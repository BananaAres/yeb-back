package com.sundaohan.server.mapper;

import com.sundaohan.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sundaohan.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface AdminMapper extends BaseMapper<Admin> {

    
    /**
     * @Title getAllAdmins
     * @Description 获取所有操作员
     * @Author sundaohan
     * @Params [id, keywords]
     * @return java.util.List<com.sundaohan.server.pojo.Admin>
     */
    List<Admin> getAllAdmins(Integer id, String keywords);
}
