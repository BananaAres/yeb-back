package com.sundaohan.server.service.impl;

import com.sundaohan.server.pojo.Department;
import com.sundaohan.server.mapper.DepartmentMapper;
import com.sundaohan.server.pojo.RespBean;
import com.sundaohan.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {


    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * @Title getAlldepartments
     * @Description 获取所有部门
     * @Author sundaohan
     * @Params []
     * @return java.util.List<com.sundaohan.server.pojo.Department>
     */
    @Override
    public List<Department> getAlldepartments() {

        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * @Title addDep
     * @Description 添加部门
     * @Author sundaohan
     * @Params [dep]
     * @return com.sundaohan.server.pojo.RespBean
     */
    @Override
    public RespBean addDep(Department dep) {
        dep.setEnabled(true);
        System.out.println(dep);
        departmentMapper.addDep(dep);
        if(dep.getResult() == 1){
            return RespBean.success("添加成功!",dep);
        }
        return RespBean.error("添加失败!");
    }

    /**
     * @Title deleteDep
     * @Description 删除部门
     * @Author sundaohan
     * @Params [id]
     * @return com.sundaohan.server.pojo.RespBean
     */
    @Override
    public RespBean deleteDep(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.deleteDep(dep);
        if(dep.getResult() == -2){
            return RespBean.error("该部门下还有子部门，删除失败!");
        }else if(dep.getResult() == -1){
            return RespBean.error("该部门下还有员工，删除失败!");
        }else if(dep.getResult() == 1){
            return RespBean.success("删除成功！");
        }

        return RespBean.error("删除失败！");
    }
}
