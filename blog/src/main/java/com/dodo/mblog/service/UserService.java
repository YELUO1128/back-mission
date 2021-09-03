package com.dodo.mblog.service;

import com.dodo.mblog.entity.User;
import com.dodo.mblog.entity.UserDetails;
import com.dodo.mblog.mapper.UserDetailsMapper;
import com.dodo.mblog.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;



@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserDetailsMapper userDetailsMapper;


    public User findUserById(Integer userid){
        return userMapper.findById(userid);
    }

    public User findUserByEmail(String email){
        return userMapper.findByEmail(email);
    }

    public List<User> findUserList(){
        return userMapper.findUser();
    }

    @Transactional(noRollbackFor = Exception.class)
    public void addUser(User user) {
         userMapper.insertUser(user);
    }


    public UserDetails findByEmail(String email){
        return userDetailsMapper.findByEmail(email);
    }

    @Transactional(noRollbackFor = Exception.class)
    public void addUserDetails(UserDetails user){
        userDetailsMapper.insertUserDetails(user);
    }

    @Transactional(noRollbackFor = Exception.class)
    public void updateUser(User user) {
        userMapper.updateUser(user);

    }

    @Transactional(noRollbackFor = Exception.class)
    public void deleteUser(String email) {
        userMapper.deleteUser(email);
    }
}
