package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User record);


    User getUserInfoByAccount(String account);

    List<String> getAllPhone();

    List<String> getAllAccount();

    void updateInfoById(User user);

    User getUserInfoById(Integer userId);

    Integer checkAdminState(Integer userId);

    List<User> getAllUser();

    void deleteUser(Integer userId);
}