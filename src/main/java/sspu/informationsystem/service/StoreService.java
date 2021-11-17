package sspu.informationsystem.service;

import sspu.informationsystem.entity.Store;
public interface StoreService{


    int insert(Store record);

    Store getStoreInfoByAccount(String account);
}
