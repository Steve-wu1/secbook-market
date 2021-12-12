package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Order;
import sspu.informationsystem.entity.OrderDishesBind;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insert(Order record);

    List<Order> getOrderByStoreId(Integer storeId);

    void addOrder(Integer userId);

    int getLatestId();


}