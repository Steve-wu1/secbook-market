package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import sspu.informationsystem.entity.Book;
import sspu.informationsystem.entity.OrderBookBind;
import sspu.informationsystem.entity.Store;
import sspu.informationsystem.mapper.BookMapper;
import sspu.informationsystem.mapper.StoreMapper;
import sspu.informationsystem.service.OrderService;
import sspu.informationsystem.service.StoreService;

import javax.annotation.Resource;
import java.util.List;


@Service
public class StoreServiceImpl implements StoreService{

    @Resource
    private StoreMapper storeMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private OrderService orderService;

    @Override
    public int insert(Store record) {
        return storeMapper.insert(record);
    }

    @Override
    public Store getStoreInfoByAccount(String account) {
        return storeMapper.getStoreInfoByAccount(account);
    }

    @Override
    public List<Store> getAllStorePassed() {
        return storeMapper.getAllStorePassed();
    }

    @Override
    public Store getStoreInfoById(Integer storeId) {
        return storeMapper.getStoreInfoById(storeId);
    }

    @Override
    public List<Book> getBookById(int storeId) {
        return storeMapper.getBookById(storeId);
    }

    @Override
    public List<String> getALlPhone() { return storeMapper.getALlPhone(); }

    @Override
    public List<String> getAllAccount() { return storeMapper.getAllAccount(); }


    @Override
    public Integer checkApplyState(Integer storeId) {
        return storeMapper.checkApplyState(storeId);
    }

    @Override
    public void deleteStore(Integer storeId) {
        storeMapper.deleteStore(storeId);
    }

    @Override
    public double getRankById(Integer storeId) {
        return storeMapper.getRankById(storeId);
    }

    @Override
    public List<Book> getBookOrderByCountForMonth(Integer storeId) {
        return storeMapper.getBookOrderByCountForMonth(storeId);
    }

    @Override
    public Double getSaleToday(Integer storeId) {
        List<OrderBookBind> dishesList = storeMapper.getSaleToday(storeId);
        return orderService.calSum(dishesList);
    }

    @Override
    public Double getSaleSameAddressToday(String sAddress) {
        List<OrderBookBind> dishesList = storeMapper.getSaleSameAddressToday(sAddress);
        return orderService.calSum(dishesList);
    }

    @Override
    public Double getSaleMonth(Integer storeId) {
        List<OrderBookBind> dishesList = storeMapper.getSaleMonth(storeId);
        return orderService.calSum(dishesList);
    }

    @Override
    public Double getSaleWeek(Integer storeId) {
        List<OrderBookBind> dishesList = storeMapper.getSaleWeek(storeId);
        return orderService.calSum(dishesList);
    }


    @Override
    public List<Object> getStoreMCount(Integer storeId) {
        return storeMapper.getStoreMCount(storeId);
    }


    @Override
    public List<Object> getRecentFiveMonth() {
        return storeMapper.getRecentFiveMonth();
    }

    @Override
    public List<Object> getAllAverageMCount() {
        return storeMapper.getAllAverageMCount();
    }

    @Override
    public Double getMonthStarBookSale(Integer dishesId) {
        return storeMapper.getMonthStarDishSale(dishesId);
    }

    @Override
    public Integer getOrderCountToday(Integer storeId) {
        return storeMapper.getOrderCountToday(storeId);
    }

    @Override
    public Integer getOrderCountWeek(Integer storeId) {
        return storeMapper.getOrderCountWeek(storeId);
    }

    @Override
    public Integer getOrderCountMonth(Integer storeId) {
        return storeMapper.getOrderCountMonth(storeId);
    }

    @Override
    public List<Integer> getRankData(Integer storeId,Integer rank) {
        return storeMapper.getRankData(storeId,rank);
    }
}
