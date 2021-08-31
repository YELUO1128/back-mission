package com.dodo.mblog.mapper;

import com.dodo.mblog.entity.User;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/5/27 09:55
 * @Description:
 */
public interface UserMapper {

    public User findById(Integer userid);   // 通过id查找
    public User findByEmail(String email);    // 通过username查找
    public List<User> findUser();     // 获取用户列表


    public void insertUser(User user);

    public void updateUser(User user);

    void deleteUser(String email);
}
