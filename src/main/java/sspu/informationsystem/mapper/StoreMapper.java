package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Book;
import sspu.informationsystem.entity.OrderBookBind;
import sspu.informationsystem.entity.Store;

import java.util.List;

@Mapper
public interface StoreMapper {

    int insert(Store record);

    Store getStoreInfoByAccount(String account);

    List<Store> getAllStorePassed();

    Store getStoreInfoById(Integer storeId);

    List<Book> getBookById(int storeId);

    List<String> getALlPhone();

    List<String> getAllAccount();

    int checkApplyState(Integer storeId);

    void deleteStore(Integer storeId);

    double getRankById(Integer storeId);

    List<Book> getBookOrderByCountForMonth(Integer storeId);

    List<OrderBookBind> getSaleToday(Integer storeId);

    List<OrderBookBind> getSaleSameAddressToday(String sAddress);

    List<OrderBookBind> getSaleMonth(Integer storeId);

    List<OrderBookBind> getSaleWeek(Integer storeId);

    List<Object> getStoreMCount(Integer storeId);

    List<Object> getRecentFiveMonth();

    List<Object> getAllAverageMCount();

    Double getMonthStarDishSale(Integer bookId);

    Integer getOrderCountToday(Integer storeId);

    Integer getOrderCountWeek(Integer storeId);

    Integer getOrderCountMonth(Integer storeId);

    List<Integer> getRankData(Integer storeId,Integer rank);
}