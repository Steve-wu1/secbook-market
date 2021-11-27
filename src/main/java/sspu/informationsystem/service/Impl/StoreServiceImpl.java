package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import sspu.informationsystem.entity.Dishes;
import sspu.informationsystem.mapper.StoreMapper;
import sspu.informationsystem.entity.Store;
import sspu.informationsystem.service.StoreService;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService{

    @Resource
    private StoreMapper storeMapper;

    @Override
    public int insert(Store record) {
        return storeMapper.insert(record);
    }

    @Override
    public Store getStoreInfoByAccount(String account) {
        return storeMapper.getStoreInfoByAccount(account);
    }

    @Override
    public List<Store> getAllStore() {
        return storeMapper.getAllStore();
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


}
