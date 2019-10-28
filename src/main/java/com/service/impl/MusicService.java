package com.service.impl;

import com.dao.IMusicDao;
import com.entity.Music;
import com.service.IMusicService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Transactional
@MapperScan(basePackages = "com.dao")
@Service("musicService")
public class MusicService implements IMusicService
{
    @Autowired
    private IMusicDao md;

    @Override
    public List<Music> selectMaxByMusicLikecount()
    {
        return md.selectMaxByMusicLikecount();
    }

    @Override
    public List<Music> selectRandMusic()
    {
        return md.selectRandMusic();
    }

    @Override
    public List<Music> selectByName(String name)
    {
        return md.selectByName(name);
    }

    @Override
    public Music selectByPrimaryKey(Integer id)
    {
        return md.selectByPrimaryKey(id);
    }

    @Override
    public List<Music> selectMusicByNameAndSinger(Map<String, Object> mp)
    {
        return md.selectMusicByNameAndSinger(mp);
    }

    @Override
    public Music favoriteMusic(String name)
    {
        return md.favoriteMusic(name);
    }

    @Override
    public Music searchPlay(Map<String, Object> mp)
    {
        return md.searchPlay(mp);
    }

    @Override
    public List<Music> selectByMusicTypeId(Integer type)
    {
        return md.selectByMusicTypeId(type);
    }

    @Override
    public Integer updateCount(String name, Integer id)
    {
        return md.updateCount(name,id);
    }

    @Override
    public Integer reduceCount(String name, Integer id)
    {
        return md.reduceCount(name,id);
    }

//    管理员
    @Override
    public List<Music> selectMusicByNameAndSingerOfpage(Map<String, Object> mp) {
        return md.selectMusicByNameAndSingerOfpage(mp);
    }

    @Override
    public Integer selectCount(Map<String, Object> mp) {
        return md.selectCount(mp);
    }

    @Override
    public Integer musicdelete(Integer id) {
        return md.musicdelete(id);
    }

    @Override
    public Integer updateByPrimaryKey(Map<String, Object> mp) {
        return md.updateByPrimaryKey(mp);
    }

    @Override
    public Integer updateByHeadPhoto(Map<String, Object> mp) {
        return md.updateByHeadPhoto(mp);
    }


}
