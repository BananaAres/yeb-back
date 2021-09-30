package com.sundaohan.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.sundaohan.server.pojo.Employee;
import com.sundaohan.server.pojo.RespBean;
import com.sundaohan.server.pojo.RespPageBean;
import com.sundaohan.server.pojo.Salary;
import com.sundaohan.server.service.IEmployeeService;
import com.sundaohan.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.controller
 * @Title SalarySobCfgController
 * @Description TODO
 * @Date 2021/8/4 下午11:05
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "获取所有员工账套")
    @GetMapping("listAll")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer size){
        System.out.println("进入请求");
        return employeeService.getEmployeeWithSalary(currentPage,size);
    }

    @ApiOperation(value = "更新员工账套")
    @PostMapping("update/{eid}/{sid}")
    public RespBean updateEmployeeSalary(@PathVariable  Integer eid,Integer sid){
        if(employeeService.update(new UpdateWrapper<Employee>().set("salaryId",sid).eq("id",eid))){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败！");
    }
}
