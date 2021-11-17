package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.User;

@Mapper
public interface UserMapper {
    int insert(User record);


    User getUserInfoByAccount(String account);
}