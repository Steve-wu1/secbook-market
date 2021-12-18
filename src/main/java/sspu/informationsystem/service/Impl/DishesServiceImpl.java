package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartFile;
import sspu.informationsystem.entity.Dishes;
import sspu.informationsystem.mapper.DishesMapper;
import sspu.informationsystem.service.DishesService;
import sspu.informationsystem.utils.FtpUtil;



@Service
public class DishesServiceImpl implements DishesService{

    @Resource
    private FtpUtil ftpUtil;

    @Resource
    private DishesMapper dishesMapper;

    @Override
    public int insert(Dishes record) {
        return dishesMapper.insert(record);
    }

    @Override
    public void updateDish(Dishes dishes) {
        dishesMapper.updateDish(dishes);
    }

    @Override
    public void deleteDishById(Integer dishesId) {
        dishesMapper.deleteDish(dishesId);
    }

    @Override
    public void updatePhoto(MultipartFile file) {

    }

}
