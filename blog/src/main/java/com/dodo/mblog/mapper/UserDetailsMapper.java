package com.dodo.mblog.mapper;

import com.dodo.mblog.entity.User;
import com.dodo.mblog.entity.UserDetails;

import java.util.List;


public interface UserDetailsMapper {

    public UserDetails findByEmail(String email);
    public void insertUserDetails(UserDetails userDetails);

    public UserDetails findByUserName(String username);

    public void updateUser(UserDetails userDetails);

    List<UserDetails> getUsers();

    void deleteUser(String email);
}
