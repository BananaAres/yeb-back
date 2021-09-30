package com.sundaohan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sundaohan.server.pojo.MenuRole;
import com.sundaohan.server.mapper.MenuRoleMapper;
import com.sundaohan.server.pojo.RespBean;
import com.sundaohan.server.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;
    /**
     * @Title updateMenuRole
     * @Description 更新角色菜单
     * @Author sundaohan
     * @Params [rid, mids]
     * @return com.sundaohan.server.pojo.RespBean
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if(mids == null || mids.length == 0){
            System.out.println();
            return RespBean.success("更新成功！");
        }
        for(int i : mids){
            System.out.println(i);
        }
        menuRoleMapper.insertRecord(rid, mids);
        return RespBean.success("更新成功！");
    }
}
