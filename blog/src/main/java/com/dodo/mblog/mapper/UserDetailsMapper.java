package com.dodo.mblog.mapper;

import com.dodo.mblog.entity.User;
import com.dodo.mblog.entity.UserDetails;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/5/28 10:30
 * @Description:
 */
public interface UserDetailsMapper {

    public UserDetails findByEmail(String email);
    public void insertUserDetails(UserDetails userDetails);

    public UserDetails findByUserName(String username);

    public void updateUser(UserDetails userDetails);

    List<UserDetails> getUsers();

    void deleteUser(String email);
}
