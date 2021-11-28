package sspu.informationsystem.service;

import sspu.informationsystem.entity.Dishes;
import sspu.informationsystem.entity.Store;

import java.util.List;

public interface StoreService{


    int insert(Store record);

    Store getStoreInfoByAccount(String account);

    List<Store> getAllStore();

    Store getStoreInfoById(Integer storeId);

    List<Dishes> getDishesById(int storeId);

    List<String> getALlPhone();

    List<String> getAllAccount();

    List<Store> getAddress(List<Store> storeList);

    Integer checkApplyState(Integer storeId);

    void deleteStore(Integer storeId);
}
