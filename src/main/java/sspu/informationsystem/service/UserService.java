package sspu.informationsystem.service;

import sspu.informationsystem.entity.User;

import java.util.List;

public interface UserService{


    int insert(User record);

    User getUserInfoByAccount(String account);

    List<String> getAllPhone();

    List<String> getAllAccount();

    void updateInfoById(User user);

    User getUserInfoById(Integer userId);

    Boolean checkAdminState(Integer userId);
}
