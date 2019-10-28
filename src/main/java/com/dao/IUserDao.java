package com.dao;

import com.entity.Users;

import java.util.List;
import java.util.Map;

public interface IUserDao
{
    Users selectById(Integer id);

    Users login(Users u);

    Integer insertUser(Users u);

    Integer selectName(String name);

    Integer updateLikeCount(Map<String,Object> mp);

    Integer reduceLikeCount(Map<String,Object> mp);

    Users selectUserByName(String name);

    Integer updateUser(Map<String,Object> mp);

    Users selectByPhone(String phone);

//    管理员
    Users selectByIdAndSealtime(String username);

    //查询所有封号用户
    List<Users> selectBySeal();
}
