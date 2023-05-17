package com.hy.reggietakeout.service.impl;

import com.hy.reggietakeout.entity.User;
import com.hy.reggietakeout.mapper.UserMapper;
import com.hy.reggietakeout.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author hy
 * @since 2023-05-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
