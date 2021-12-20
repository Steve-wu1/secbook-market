package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Dishes;
import sspu.informationsystem.entity.OrderDishesBind;
import sspu.informationsystem.entity.Store;

import java.util.List;

@Mapper
public interface StoreMapper {

    int insert(Store record);

    Store getStoreInfoByAccount(String account);

    List<Store> getAllStorePassed();

    Store getStoreInfoById(Integer storeId);

    List<Dishes> getDishesById(int storeId);

    List<String> getALlPhone();

    List<String> getAllAccount();

    int checkApplyState(Integer storeId);

    void deleteStore(Integer storeId);

    double getRankById(Integer storeId);

    Dishes getStarDishByIdForMonth(Integer storeId);

    List<OrderDishesBind> getSaleToday(Integer storeId);

    List<OrderDishesBind> getSaleSameAddressToday(String sAddress);

    List<OrderDishesBind> getSaleMonth(Integer storeId);

    List<OrderDishesBind> getSaleWeek(Integer storeId);

    List<Object> getStoreMCount(Integer storeId);

    List<Object> getRecentFiveMonth();

    List<Object> getAllAverageMCount();

    Double getMonthStarDishSale(Integer dishesId);

    Integer getOrderCountToday(Integer storeId);

    Integer getOrderCountWeek(Integer storeId);

    Integer getOrderCountMonth(Integer storeId);
}