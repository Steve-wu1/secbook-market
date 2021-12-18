package sspu.informationsystem.service.Impl;

import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import sspu.informationsystem.entity.Dishes;
import sspu.informationsystem.entity.OrderDishesBind;
import sspu.informationsystem.mapper.DishesMapper;
import sspu.informationsystem.mapper.StoreMapper;
import sspu.informationsystem.entity.Store;
import sspu.informationsystem.service.OrderService;
import sspu.informationsystem.service.StoreService;

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
    public Dishes getStarDishByIdForWeek(Integer storeId) {
        return storeMapper.getStarDishByIdForWeek(storeId);
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
    public Double getSaleYear(Integer storeId) {
        List<OrderDishesBind> dishesList = storeMapper.getSaleYear(storeId);
        return orderService.calSum(dishesList);
    }




}
