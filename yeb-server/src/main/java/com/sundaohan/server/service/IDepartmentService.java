package com.sundaohan.server.service;

import com.sundaohan.server.mapper.DepartmentMapper;
import com.sundaohan.server.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sundaohan.server.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface IDepartmentService extends IService<Department> {

    
    /**
     * @Title getALLdepartments
     * @Description 获取所有部门
     * @Author sundaohan
     * @Params []
     * @return java.util.List<com.sundaohan.server.pojo.Department>
     */
    List<Department> getAlldepartments();
    /**
     * @Title addDep
     * @Description 添加部门
     * @Author sundaohan
     * @Params [dep]
     * @return com.sundaohan.server.pojo.RespBean
     */
    RespBean addDep(Department dep);

    /**
     * @Title deleteDep
     * @Description 删除部门
     * @Author sundaohan
     * @Params [id]
     * @return com.sundaohan.server.pojo.RespBean
     */
    RespBean deleteDep(Integer id);
}
