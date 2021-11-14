package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import sspu.informationsystem.mapper.ApplyMapper;
import sspu.informationsystem.entity.Apply;
import sspu.informationsystem.service.ApplyService;
@Service
public class ApplyServiceImpl implements ApplyService{

    @Resource
    private ApplyMapper applyMapper;

    @Override
    public int insert(Apply record) {
        return applyMapper.insert(record);
    }

}
