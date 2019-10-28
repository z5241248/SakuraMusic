package com.dao;

import com.entity.Album;

import java.util.List;

public interface IAlbumDao
{
    List<Album> selectMaxByAlbumLikecount();

    Album selectByPrimaryKey(Integer id);

    List<Album> selectAlbumByName(String name);

    String selectAlbumInfo(String name);

    String selectMusicByAlbum(String name);
}
