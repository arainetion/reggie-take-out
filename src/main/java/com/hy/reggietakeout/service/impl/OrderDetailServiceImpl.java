package com.hy.reggietakeout.service.impl;

import com.hy.reggietakeout.entity.OrderDetail;
import com.hy.reggietakeout.mapper.OrderDetailMapper;
import com.hy.reggietakeout.service.IOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author hy
 * @since 2023-05-16
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
