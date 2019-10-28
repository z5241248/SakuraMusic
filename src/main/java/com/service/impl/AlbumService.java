package com.service.impl;

import com.dao.IAlbumDao;
import com.entity.Album;
import com.service.IAlbumService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@MapperScan(basePackages = "com.dao")
@Service("albumService")
public class AlbumService implements IAlbumService
{
    @Autowired
    private IAlbumDao ad;

    @Override
    public List<Album> selectMaxByAlbumLikecount()
    {
        return ad.selectMaxByAlbumLikecount();
    }

    @Override
    public Album selectByPrimaryKey(Integer id)
    {
        return ad.selectByPrimaryKey(id);
    }

    @Override
    public List<Album> selectAlbumByName(String name)
    {
        return ad.selectAlbumByName(name);
    }

    @Override
    public String selectAlbumInfo(String name)
    {
        return ad.selectAlbumInfo(name);
    }

    @Override
    public String selectMusicByAlbum(String name)
    {
        return ad.selectMusicByAlbum(name);
    }


}
