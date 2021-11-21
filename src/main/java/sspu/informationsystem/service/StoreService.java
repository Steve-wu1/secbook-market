package sspu.informationsystem.service;

import sspu.informationsystem.entity.Store;

import java.util.List;

public interface StoreService{


    int insert(Store record);

    Store getStoreInfoByAccount(String account);

    List<Store> getAllStore();
}
