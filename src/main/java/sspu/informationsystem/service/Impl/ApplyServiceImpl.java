package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import sspu.informationsystem.entity.Store;
import sspu.informationsystem.mapper.ApplyMapper;
import sspu.informationsystem.entity.Apply;
import sspu.informationsystem.service.ApplyService;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService{

    @Resource
    private ApplyMapper applyMapper;

    @Override
    public int insert(Apply record) {
        return applyMapper.insert(record);
    }

    @Override
    public void addStoreRegisterApply(Store store) {
        applyMapper.addStoreRegisterApply(store);
    }

    @Override
    public List<Apply> getAllApplyUndealed() {
        List<Apply> applyList = applyMapper.getAllApplyUndealed();
        for (Apply apply:applyList){
            apply.setStateName(applyMapper.getStateNameForStore(apply.getAState()));
            apply.setAName(applyMapper.getApplyNameForStore(apply.getAId()));
        }
        return applyList;
    }

    @Override
    public void setApplyPass(Integer applyId,Integer userId) {
        applyMapper.setApplyPass(applyId,userId);
    }

    @Override
    public void setApplyDeny(Integer applyId,Integer userId) {
        applyMapper.setApplyDeny(applyId,userId);
    }

}
