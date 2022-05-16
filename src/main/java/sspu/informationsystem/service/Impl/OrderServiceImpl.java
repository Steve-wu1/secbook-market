package sspu.informationsystem.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sspu.informationsystem.entity.*;
import sspu.informationsystem.mapper.*;
import sspu.informationsystem.service.OrderService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
    private BookMapper bookMapper;
    @Resource
    private OrderBookBindMapper orderBookBindMapper;

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
    public void addOrder(Integer userId, List<OrderBookBind> dishesList) {
        orderMapper.addOrder(userId);
        Integer orderId = orderMapper.getLatestId();
        log.debug("获取最新注入的Id" + orderId);
        log.debug("订单绑定菜品列表" + dishesList);
        dishesList.forEach(item -> orderBookBindMapper.bookBind(orderId, item));
    }

    @Override
    public List<OrderBookBind> checkBookInOrder(List<OrderBookBind> orderBookBindList) {
        List<OrderBookBind> dealtList = new ArrayList<>();
        orderBookBindList.forEach(item -> {
            if (item.getBNumber() != 0) {
                dealtList.add(item);
            }
        });
        return dealtList;
    }

    @Override
    public void orderComplete(Integer orderId, String oDelieverName, String oDelieverNum) {
        orderMapper.orderComplete(orderId, oDelieverName, oDelieverNum);
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
        List<String> stateList = orderMapper.getStateList();
        order.setOStateName(stateList.get(order.getOState()));
        //订单总价及内含菜品解析
        final Double[] sumPrice = {0.0};
        final String[] content = {""};
        List<OrderBookBind> dishesList = orderBookBindMapper.getBookList(order.getOrderId());
        dishesList.forEach(dish->{
            Book dishInfo = bookMapper.getInfo(dish.getBId());
            //价格解析求和
            Double price = dishInfo.getBPrice();
            price = price * dish.getBNumber();
            sumPrice[0] += price;
            //点餐内容解析
            content[0] += dishInfo.getBName() + "x" + dish.getBNumber();
        });
        order.setOPrice(sumPrice[0]);
        order.setOContent(content[0]);
    }

    @Override
    public Double calSum(List<OrderBookBind> dishesList) {
        final Double[] sumPrice = {0.0};
        dishesList.forEach(dish->{
            Book dishInfo = bookMapper.getInfo(dish.getBId());
            //价格解析求和
            Double price = dishInfo.getBPrice();
            price = price * dish.getBNumber();
            sumPrice[0] += price;
        });
        return sumPrice[0];
    }

    @Override
    public void orderAccept(Integer orderId) {
        orderMapper.orderAccept(orderId);
    }

    @Override
    public void orderAddComment(Integer oRank, String oComment, Integer orderId) {
        orderMapper.orderAddComment(oRank,oComment,orderId);
    }

    @Override
    public void orderReceive(Integer orderId) {
        orderMapper.orderReceive(orderId);
    }

}
