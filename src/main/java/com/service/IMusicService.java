package com.service;

import com.entity.Music;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IMusicService
{
    List<Music> selectMaxByMusicLikecount();

    List<Music> selectRandMusic();

    List<Music> selectByName(String name);

    Music selectByPrimaryKey(Integer id);

    List<Music> selectMusicByNameAndSinger(Map<String,Object> mp);

    Music favoriteMusic(String name);

    Music searchPlay(Map<String,Object> mp);

    List<Music> selectByMusicTypeId(Integer type);

    Integer updateCount(String name,Integer id);

    Integer reduceCount(String name,Integer id);

//    管理员
    List<Music> selectMusicByNameAndSingerOfpage(Map<String,Object> mp);

    Integer selectCount(Map<String,Object> mp);

    Integer musicdelete(Integer id);

    Integer updateByPrimaryKey(Map<String,Object> mp);

    Integer updateByHeadPhoto(Map<String,Object> mp);

}
