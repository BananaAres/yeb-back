package com.sundaohan.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sundaohan.server.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * @Title getEmployeeByPage
     * @Description 获取所有员工（分页）
     * @Author sundaohan
     * @Params [page, employee, beginDateScope]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.sundaohan.server.pojo.Employee>
     */
    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee,
                                      @Param("beginDateScope") LocalDate[] beginDateScope);

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
     * @Params [page]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.sundaohan.server.pojo.Employee>
     */
    IPage<Employee> getEmployeeWithSalary(Page<Employee> page);
}
