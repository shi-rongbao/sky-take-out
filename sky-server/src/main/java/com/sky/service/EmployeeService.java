package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工方法
     * @param employeeDTO 前端传过来的封装好的员工对象
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     * @param employeePageQueryDTO 分页查询所需要的参数封装成的对象
     * @return 返回一个装有总记录数与当前页数据集合的对象
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
