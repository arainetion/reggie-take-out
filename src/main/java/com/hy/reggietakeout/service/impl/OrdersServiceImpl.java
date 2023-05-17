package com.hy.reggietakeout.service.impl;

import com.hy.reggietakeout.entity.Orders;
import com.hy.reggietakeout.mapper.OrdersMapper;
import com.hy.reggietakeout.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author hy
 * @since 2023-05-16
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
