package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import sspu.informationsystem.entity.OrderBookBind;
import sspu.informationsystem.mapper.OrderBookBindMapper;
import sspu.informationsystem.service.OrderBookBindService;

import javax.annotation.Resource;

@Service
public class OrderBookBindServiceImpl implements OrderBookBindService {

    @Resource
    private OrderBookBindMapper orderBookBindMapper;

    @Override
    public int insert(OrderBookBind record) {
        return orderBookBindMapper.insert(record);
    }

}
