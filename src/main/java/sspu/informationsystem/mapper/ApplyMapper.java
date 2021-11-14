package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Apply;

@Mapper
public interface ApplyMapper {
    int insert(Apply record);
}