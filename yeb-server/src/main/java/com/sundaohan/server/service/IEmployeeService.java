package com.sundaohan.server.service;

import com.sundaohan.server.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sundaohan.server.pojo.RespBean;
import com.sundaohan.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface IEmployeeService extends IService<Employee> {
    /**
     * @Title getEmployeeByPage
     * @Description 获取所有员工(分页)
     * @Author sundaohan
     * @Params [currentPage, size, employee, beginDateScope]
     * @return com.sundaohan.server.pojo.RespPageBean
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * @Title maxWorkID
     * @Description 获取工号
     * @Author sundaohan
     * @Params []
     * @return com.sundaohan.server.pojo.RespBean
     */
    RespBean maxWorkID();

    /**
     * @Title addEmp
     * @Description 添加员工
     * @Author sundaohan
     * @Params [employee]
     * @return com.sundaohan.server.pojo.RespBean
     */
    RespBean addEmp(Employee employee);

    /**
     * @Title getEmployee
     * @Description 查询员工
     * @Author sundaohan
     * @Params [id]
     * @return java.util.List<com.sundaohan.server.pojo.Employee>
     */
    List<Employee> getEmployee(Integer id);

    /**
     * @Title getEmployeeWithSalary
     * @Description 获取所有员工账套
     * @Author sundaohan
     * @Params [currentPage, size]
     * @return com.sundaohan.server.pojo.RespPageBean
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
