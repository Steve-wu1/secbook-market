package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.OrderBookBind;

import java.util.List;

@Mapper
public interface OrderBookBindMapper {
    int insert(OrderBookBind record);

    void bookBind(Integer orderId, OrderBookBind item);

    List<OrderBookBind> getBookList(Integer orderId);
}