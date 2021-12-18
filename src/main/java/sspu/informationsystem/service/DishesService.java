package sspu.informationsystem.service;

import org.springframework.web.multipart.MultipartFile;
import sspu.informationsystem.entity.Dishes;
public interface DishesService{


    int insert(Dishes record);

    void updateDish(Dishes dishes);

    void deleteDishById(Integer dishesId);

    void updatePhoto(MultipartFile file);
}
