package com.hy.reggietakeout.service.impl;

import com.hy.reggietakeout.entity.Employee;
import com.hy.reggietakeout.mapper.EmployeeMapper;
import com.hy.reggietakeout.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author hy
 * @since 2023-05-17
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
