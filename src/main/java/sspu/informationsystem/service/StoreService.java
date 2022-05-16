package sspu.informationsystem.service;

import sspu.informationsystem.entity.Book;
import sspu.informationsystem.entity.Store;

import java.util.List;

public interface StoreService{


    int insert(Store record);

    Store getStoreInfoByAccount(String account);

    List<Store> getAllStorePassed();

    Store getStoreInfoById(Integer storeId);

    List<Book> getBookById(int storeId);

    List<String> getALlPhone();

    List<String> getAllAccount();



    Integer checkApplyState(Integer storeId);

    void deleteStore(Integer storeId);

    double getRankById(Integer storeId);

    List<Book> getBookOrderByCountForMonth(Integer storeId);

    Double getSaleToday(Integer storeId);

    Double getSaleSameAddressToday(String sAddress);

    Double getSaleMonth(Integer storeId);

    Double getSaleWeek(Integer storeId);

    List<Object> getStoreMCount(Integer storeId);

    List<Object> getRecentFiveMonth();

    List<Object> getAllAverageMCount();

    Double getMonthStarBookSale(Integer dishesId);

    Integer getOrderCountToday(Integer storeId);

    Integer getOrderCountWeek(Integer storeId);

    Integer getOrderCountMonth(Integer storeId);

    List<Integer> getRankData(Integer storeId,Integer rank);
}
