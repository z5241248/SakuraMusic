package com.dao;

import com.entity.Musictype;

import java.util.List;

public interface IMusicTypeDao
{
    List<Musictype> selectAllMusicType();

    List<Musictype> selectAllMusicType2();

    Musictype selectImageByMusicType(String musictype);

    Musictype selectById(Integer id);
}
