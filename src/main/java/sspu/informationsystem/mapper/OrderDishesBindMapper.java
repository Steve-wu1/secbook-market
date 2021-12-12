package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.OrderDishesBind;

import java.util.List;

@Mapper
public interface OrderDishesBindMapper {
    int insert(OrderDishesBind record);

    void dishesBind(Integer orderId, OrderDishesBind item);

    List<OrderDishesBind> getDishesList(Integer orderId);
}