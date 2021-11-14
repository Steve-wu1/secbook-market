package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import sspu.informationsystem.mapper.OrderMapper;
import sspu.informationsystem.entity.Order;
import sspu.informationsystem.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

}
