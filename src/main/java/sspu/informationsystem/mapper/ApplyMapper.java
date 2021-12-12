package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Apply;
import sspu.informationsystem.entity.Store;

import java.util.List;

@Mapper
public interface ApplyMapper {
    int insert(Apply record);

    void addStoreRegisterApply(Integer storeId);

    List<Apply> getAllApplyUndealt();

    String getStateNameForStore(Integer aState);

    String getApplyNameForStore(Integer aId);

    void setApplyPass(Integer applyId,Integer userId);

    void setApplyDeny(Integer applyId,Integer userId);
}