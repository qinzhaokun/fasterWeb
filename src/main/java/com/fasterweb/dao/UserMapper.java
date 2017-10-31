package com.fasterweb.dao;

import com.fasterweb.model.User;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface UserMapper {
    User getUserById(Integer id);

    void addUser(User user);

    void updateUser(User user);

    void deleteById(Integer id);
}
