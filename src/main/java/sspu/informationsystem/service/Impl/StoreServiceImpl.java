package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import sspu.informationsystem.entity.Dishes;
import sspu.informationsystem.entity.OrderDishesBind;
import sspu.informationsystem.entity.Store;
import sspu.informationsystem.mapper.DishesMapper;
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
    private DishesMapper dishesMapper;
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
    public List<Dishes> getDishesById(int storeId) { return storeMapper.getDishesById(storeId); }

    @Override
    public List<String> getALlPhone() { return storeMapper.getALlPhone(); }

    @Override
    public List<String> getAllAccount() { return storeMapper.getAllAccount(); }

    @Override
    public List<Store> getAddress(List<Store> storeList) {
        for (Store store:storeList){
            switch (store.getSAddress()){
                case "W1": store.setSAddress("西食堂一楼");
                    break;
                case "W2": store.setSAddress("西食堂二楼");
                    break;
                case "E1": store.setSAddress("东食堂一楼");
                    break;
                case "E2": store.setSAddress("东食堂二楼");
                    break;
                case "E3": store.setSAddress("东食堂三楼");
            }

        }
        return storeList;
    }

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
    public Dishes getStarDishByIdForMonth(Integer storeId) {
        return storeMapper.getStarDishByIdForMonth(storeId);
    }

    @Override
    public Double getSaleToday(Integer storeId) {
        List<OrderDishesBind> dishesList = storeMapper.getSaleToday(storeId);
        return orderService.calSum(dishesList);
    }

    @Override
    public Double getSaleSameAddressToday(String sAddress) {
        List<OrderDishesBind> dishesList = storeMapper.getSaleSameAddressToday(sAddress);
        return orderService.calSum(dishesList);
    }

    @Override
    public Double getSaleMonth(Integer storeId) {
        List<OrderDishesBind> dishesList = storeMapper.getSaleMonth(storeId);
        return orderService.calSum(dishesList);
    }

    @Override
    public Double getSaleWeek(Integer storeId) {
        List<OrderDishesBind> dishesList = storeMapper.getSaleWeek(storeId);
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
    public Double getMonthStarDishSale(Integer dishesId) {
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
}
