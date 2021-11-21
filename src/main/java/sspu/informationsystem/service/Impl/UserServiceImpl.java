package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import sspu.informationsystem.entity.User;
import sspu.informationsystem.mapper.UserMapper;
import sspu.informationsystem.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User getUserInfoByAccount(String account) {
        return userMapper.getUserInfoByAccount(account);
    }

    @Override
    public List<String> getAllPhone() {
        return userMapper.getAllPhone();
    }

    @Override
    public List<String> getAllAccount() {
        return userMapper.getAllAccount();
    }

    @Override
    public void updateInfoById(User user) {
        userMapper.updateInfoById(user);
    }

    @Override
    public User getUserInfoById(Integer userId) {
        return userMapper.getUserInfoById(userId);
    }

}
