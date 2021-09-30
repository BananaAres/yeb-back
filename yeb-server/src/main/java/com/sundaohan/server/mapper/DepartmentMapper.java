package com.sundaohan.server.mapper;

import com.sundaohan.server.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sundaohan.server.pojo.RespBean;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * @Title getAllDepartments
     * @Description 获取所有部门
     * @Author sundaohan
     * @Params []
     * @return java.util.List<com.sundaohan.server.pojo.Department>
     */
    List<Department> getAllDepartments(Integer parentId);
    /**
     * @Title addDep
     * @Description 添加部门
     * @Author sundaohan
     * @Params [dep]
     * @return void
     */
    void addDep(Department dep);
    /**
     * @Title deleteDep
     * @Description 删除部门
     * @Author sundaohan
     * @Params [dep]
     * @return void
     */
    void deleteDep(Department dep);
}
