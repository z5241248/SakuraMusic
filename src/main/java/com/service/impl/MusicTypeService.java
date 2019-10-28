package com.service.impl;

import com.dao.IMusicTypeDao;
import com.entity.Musictype;
import com.service.IMusicTypeService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@MapperScan(basePackages = "com.dao")
@Service("musicTypeService")
public class MusicTypeService implements IMusicTypeService
{
    @Autowired
    private IMusicTypeDao ms;

    @Override
    public List<Musictype> selectAllMusicType()
    {
        return ms.selectAllMusicType();
    }

    @Override
    public List<Musictype> selectAllMusicType2() {
        return ms.selectAllMusicType2();
    }

    @Override
    public Musictype selectImageByMusicType(String musictype)
    {
        return ms.selectImageByMusicType(musictype);
    }

}
