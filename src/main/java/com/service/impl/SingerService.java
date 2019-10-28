package com.service.impl;

import com.dao.ISingerDao;
import com.entity.Singer;
import com.service.ISingerService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@MapperScan(basePackages = "com.dao")
@Service("singerService")
public class SingerService implements ISingerService
{
    @Autowired
    private ISingerDao sd;

    @Override
    public List<Singer> selectRandSinger()
    {
        return sd.selectRandSinger();
    }

    @Override
    public List<Singer> selectByName(String name)
    {
        return sd.selectByName(name);
    }

    @Override
    public Singer selectByPrimaryKey(Integer id)
    {
        return sd.selectByPrimaryKey(id);
    }

    @Override
    public Singer selectByName2(String name)
    {
        return sd.selectByName2(name);
    }

}
