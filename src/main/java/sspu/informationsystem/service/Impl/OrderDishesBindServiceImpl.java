package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import sspu.informationsystem.entity.OrderDishesBind;
import sspu.informationsystem.mapper.OrderDishesBindMapper;
import sspu.informationsystem.service.OrderDishesBindService;
@Service
public class OrderDishesBindServiceImpl implements OrderDishesBindService{

    @Resource
    private OrderDishesBindMapper orderDishesBindMapper;

    @Override
    public int insert(OrderDishesBind record) {
        return orderDishesBindMapper.insert(record);
    }

}
