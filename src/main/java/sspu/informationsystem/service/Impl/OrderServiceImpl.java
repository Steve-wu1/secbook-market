package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import sspu.informationsystem.entity.*;
import sspu.informationsystem.mapper.*;
import sspu.informationsystem.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    OrderService orderService;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private StoreMapper storeMapper;
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

           orderService.dealOrderInfo(item);
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

    @Override
    public void orderComplete(Integer orderId) {
        orderMapper.orderComplete(orderId);
    }

    @Override
    public void orderCancel(Integer orderId) {
        orderMapper.orderCancel(orderId);
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        List<Order> orderList = orderMapper.getOrderByUserId(userId);
        orderList.forEach(order -> {
            //下单商家解析
            Store store = storeMapper.getStoreInfoById(orderMapper.getStoreId(order.getOrderId()));
            order.setOStore(store.getSName());
            order.setOStoreId(store.getStoreId());
            orderService.dealOrderInfo(order);
        });
        return orderList;
    }

    @Override
    public void dealOrderInfo(Order order) {
        //下单状态解析
        switch (order.getOState()){
            case 0: order.setOStateName("已提交");
                break;
            case 1: order.setOStateName("已完成");
                break;
            case 2: order.setOStateName("已交付");
                break;
            case 3: order.setOStateName("已取消");
                break;
        }
        //订单总价及内含菜品解析
        final Double[] sumPrice = {0.0};
        final String[] content = {""};
        List<OrderDishesBind> dishesList = orderDishesBindMapper.getDishesList(order.getOrderId());
        dishesList.forEach(dish->{
            Dishes dishInfo = dishesMapper.getInfo(dish.getDId());
            //价格解析求和
            Double price = dishInfo.getDPrice();
            price = price * dish.getDNumber();
            sumPrice[0] += price;
            //点餐内容解析
            content[0] += dishInfo.getDName()+"x"+dish.getDNumber();
        });
        order.setOPrice(sumPrice[0]);
        order.setOContent(content[0]);
    }

    @Override
    public void orderAccept(Integer orderId) {
        orderMapper.orderAccept(orderId);
    }

    @Override
    public void orderAddComment(Integer oRank, String oComment, Integer orderId) {
        orderMapper.orderAddComment(oRank,oComment,orderId);
    }

}
