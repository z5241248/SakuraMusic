package com.service.impl;

import com.dao.IUserDao;
import com.entity.Users;
import com.service.IUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@MapperScan(basePackages = "com.dao")
@Service("userService")
public class UserService implements IUserService
{
    @Autowired
    private IUserDao ud;

    @Override
    public Users selectById(Integer id)
    {
        return ud.selectById(id);
    }

    @Override
    public Users login(Users u)
    {
        return ud.login(u);
    }

    @Override
    public Integer insertUser(Users u)
    {
        return ud.insertUser(u);
    }

    @Override
    public Integer selectName(String name)
    {
        return ud.selectName(name);
    }

    @Override
    public Integer updateLikeCount(Map<String, Object> mp)
    {
        return ud.updateLikeCount(mp);
    }

    @Override
    public Integer reduceLikeCount(Map<String, Object> mp)
    {
        return ud.reduceLikeCount(mp);
    }

    @Override
    public Users selectUserByName(String name)
    {
        return ud.selectUserByName(name);
    }

    @Override
    public Integer updateUser(Map<String, Object> mp)
    {
        return ud.updateUser(mp);
    }

    @Override
    public Users selectByPhone(String phone)
    {
        return ud.selectByPhone(phone);
    }

//    管理员
    @Override
    public Users selectByIdAndSealtime(String username) {
        return ud.selectByIdAndSealtime(username);
    }

    //查询所有封号用户
    @Override
    public List<Users> selectBySeal()
    {
        return ud.selectBySeal();
    }
}
