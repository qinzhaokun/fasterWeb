package com.fasterweb.service;

import com.fasterweb.dao.UserMapper;
import com.fasterweb.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userSeivice")
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.addUser(user);
    }

    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    public void deleteById(Integer id){
        userMapper.deleteById(id);
    }

    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }
}
