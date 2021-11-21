package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
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


}
