package com.hy.reggietakeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.reggietakeout.common.R;
import com.hy.reggietakeout.entity.Employee;
import com.hy.reggietakeout.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


/**
 * @author arainetion
 * @version 1.0
 * @date 2023/5/16 15:52
 * @description
 */
@SuppressWarnings({"all"})
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private IEmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param request
     * @param employee
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

        //1.将页面提交的密码进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper); //数据库中对username做了唯一索引（getOne）

        //3.如果没有查询到则返回登陆失败结果
        if (emp == null) {
            return R.error("登录失败");
        }


        //4.密码比对，如果不一致返回登录失败结果
        if (!emp.getPassword().equals(password)) {
            return R.error("请检查账号或密码");
        }

        //5.查看员工状态，如果为已禁用状态（0），则返回员工已禁用结果
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }

        //6.登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {

        log.info("新增员工，员工信息：{}", employee.toString());
        //设置初始密码123456，进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //获得当前登录用户Id
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    @GetMapping("/page")
    public R<Page<Employee>> page(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, String name) {

        //构造分页构造器
        Page<Employee> employeePage = new Page<>(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);

        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(employeePage, queryWrapper);


        return R.success(employeePage);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        //1.清理Session中保存的当前登录员工id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){

        Long empId = (Long) request.getSession().getAttribute("employee");

        employee.setUpdateUser(empId);
        employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

}
