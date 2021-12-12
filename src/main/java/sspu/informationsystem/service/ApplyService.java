package sspu.informationsystem.service;

import sspu.informationsystem.entity.Apply;
import sspu.informationsystem.entity.Store;

import java.util.List;

public interface ApplyService{


    int insert(Apply record);

    void addStoreRegisterApply(Integer storeId);

    List<Apply> getAllApplyUndealt();

    void setApplyPass(Integer applyId,Integer userId);

    void setApplyDeny(Integer applyId,Integer userId);
}
