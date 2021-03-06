package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Order;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insert(Order record);

    List<Order> getOrderByStoreId(Integer storeId);

    void addOrder(Integer userId);

    int getLatestId();

    void orderComplete(Integer orderId, String oDelieverName, String oDelieverNum);

    void orderCancel(Integer orderId);

    List<Order> getOrderByUserId(Integer userId);

    Integer getStoreId(Integer orderId);

    void orderAccept(Integer orderId);

    void orderAddComment(Integer oRank, String oComment, Integer orderId);

    void orderReceive(Integer orderId);

    List<String> getStateList();
}