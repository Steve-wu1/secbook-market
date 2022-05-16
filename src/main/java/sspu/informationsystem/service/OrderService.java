package sspu.informationsystem.service;

import sspu.informationsystem.entity.Order;
import sspu.informationsystem.entity.OrderBookBind;

import java.util.List;

public interface OrderService{


    int insert(Order record);

    List<Order> getOrderByStoreId(Integer storeId);

    void addOrder(Integer userId, List<OrderBookBind> dishesList);

    List<OrderBookBind> checkBookInOrder(List<OrderBookBind> orderBookBindList);

    void orderComplete(Integer orderId, String oDelieverName, String oDelieverNum);

    void orderCancel(Integer orderId);

    List<Order> getOrderByUserId(Integer userId);

    void dealOrderInfo(Order order);

    Double calSum(List<OrderBookBind> dishesList);

    void orderAccept(Integer orderId);

    void orderAddComment(Integer oRank, String oComment, Integer orderId);

    void orderReceive(Integer orderId);


}
