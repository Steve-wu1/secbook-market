package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Dishes;
import sspu.informationsystem.entity.Store;

import java.util.List;

@Mapper
public interface StoreMapper {

    int insert(Store record);

    Store getStoreInfoByAccount(String account);

    List<Store> getAllStore();

    Store getStoreInfoById(Integer storeId);

    List<Dishes> getDishesById(int storeId);

    List<String> getALlPhone();

    List<String> getAllAccount();
}