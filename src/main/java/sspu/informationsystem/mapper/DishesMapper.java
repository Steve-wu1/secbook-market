package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Dishes;

@Mapper
public interface DishesMapper {
    int insert(Dishes record);

    void updateDish(Dishes dishes);

    void deleteDish(Integer dishesId);

    Dishes getInfo(Integer dId);
}