package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Order;

@Mapper
public interface OrderMapper {
    int insert(Order record);
}