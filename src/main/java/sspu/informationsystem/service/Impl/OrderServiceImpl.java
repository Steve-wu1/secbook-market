package sspu.informationsystem.service.Impl;

import javafx.beans.binding.ListBinding;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import sspu.informationsystem.entity.OrderDishesBind;
import sspu.informationsystem.mapper.DishesMapper;
import sspu.informationsystem.mapper.OrderDishesBindMapper;
import sspu.informationsystem.mapper.OrderMapper;
import sspu.informationsystem.entity.Order;
import sspu.informationsystem.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDishesBindMapper orderDishesBindMapper;

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    public List<Order> getOrderByStoreId(Integer storeId) {
        return orderMapper.getOrderByStoreId(storeId);
    }

    @Override
    public void addOrder(Integer userId,List<OrderDishesBind> dishesList) {
        orderMapper.addOrder(userId);
        Integer orderId = orderMapper.getLatestId();
        dishesList.forEach(item-> orderDishesBindMapper.dishesBind(orderId,item));
    }

    @Override
    public List<OrderDishesBind> checkDishesInOrder(List<OrderDishesBind> orderDishesBindList) {
        List<OrderDishesBind> dealtList = new ArrayList<>();
        orderDishesBindList.forEach(item->{
            if (item.getDNumber()!=0){
                dealtList.add(item);
            }
        });
        return dealtList;
    }

}
