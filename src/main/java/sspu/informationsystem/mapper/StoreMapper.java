package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Store;

@Mapper
public interface StoreMapper {
    int insert(Store record);
}