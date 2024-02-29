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

    /**
     * 启用禁用员工账号
     * @param status 员工的启用禁用状态 使用url参数传递
     * @param id 员工的id 路径参数传递
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据id查询员工信息
     * @param id 要查询员工的id
     * @return 查询到的员工封装成Employee对象返回
     */
    Employee getById(Long id);

    /**
     * 编辑员工信息
     * @param employeeDTO 封装好要修改的员工信息
     */
    void update(EmployeeDTO employeeDTO);
}
