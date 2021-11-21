package sspu.informationsystem.service;

import sspu.informationsystem.entity.User;

import java.util.List;

public interface UserService{


    int insert(User record);

    User getUserInfoByAccount(String account);

    List<String> getAllPhone();

    List<String> getAllAccount();



}
