package com.sundaohan.server.controller;


import com.sundaohan.server.pojo.Department;
import com.sundaohan.server.pojo.RespBean;
import com.sundaohan.server.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("listAll")
    public List<Department> getAllDepartment(){
        return departmentService.getAlldepartments();
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("add")
    public RespBean addDep(@RequestBody Department dep){
        return departmentService.addDep(dep);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("delete/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id){
        return departmentService.deleteDep(id);
    }
}
