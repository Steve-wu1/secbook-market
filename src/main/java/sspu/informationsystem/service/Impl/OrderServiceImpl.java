package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import sspu.informationsystem.entity.Dishes;
import sspu.informationsystem.entity.OrderDishesBind;
import sspu.informationsystem.entity.User;
import sspu.informationsystem.mapper.DishesMapper;
import sspu.informationsystem.mapper.OrderDishesBindMapper;
import sspu.informationsystem.mapper.OrderMapper;
import sspu.informationsystem.entity.Order;
import sspu.informationsystem.mapper.UserMapper;
import sspu.informationsystem.service.OrderService;
import sun.rmi.runtime.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private DishesMapper dishesMapper;
    @Resource
    private OrderDishesBindMapper orderDishesBindMapper;

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    public List<Order> getOrderByStoreId(Integer storeId) {
        List<Order> orderList = orderMapper.getOrderByStoreId(storeId);
        orderList.forEach(item->{
            //下单用户信息解析
           User user = userMapper.getUserInfoById(item.getOUserId());
           item.setOName(user.getUName());
           item.setOPhone(user.getUPhone());
           //下单状态解析
           switch (item.getOState()){
               case 0: item.setOStateName("已提交");
                   break;
               case 1: item.setOStateName("已完成");
                   break;
               case 2: item.setOStateName("已交付");
                   break;
               case 3: item.setOStateName("已取消");
                   break;
           }
           //订单总价及内含菜品解析
            final Double[] sumPrice = {0.0};
            final String[] content = {""};
            List<OrderDishesBind> dishesList = orderDishesBindMapper.getDishesList(item.getOrderId());
           dishesList.forEach(dish->{
               Dishes dishInfo = dishesMapper.getInfo(dish.getDId());
               //价格解析求和
               Double price = dishInfo.getDPrice();
               price = price * dish.getDNumber();
               sumPrice[0] += price;
               //点餐内容解析
               content[0] += dishInfo.getDName()+"x"+dish.getDNumber();
           });
            item.setOPrice(sumPrice[0]);
            item.setOContent(content[0]);
        });

        return orderList;
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
