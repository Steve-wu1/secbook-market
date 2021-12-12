package sspu.informationsystem.service;

import sspu.informationsystem.entity.Order;
import sspu.informationsystem.entity.OrderDishesBind;

import java.util.List;

public interface OrderService{


    int insert(Order record);

    List<Order> getOrderByStoreId(Integer storeId);

    void addOrder(Integer userId,List<OrderDishesBind> dishesList);

    List<OrderDishesBind> checkDishesInOrder(List<OrderDishesBind> orderDishesBindList);
}
