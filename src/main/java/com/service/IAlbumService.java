package com.service;

import com.entity.Album;
import com.entity.Singer;

import java.util.List;

public interface IAlbumService
{
    List<Album> selectMaxByAlbumLikecount();

    Album selectByPrimaryKey(Integer id);

    List<Album> selectAlbumByName(String name);

    String selectAlbumInfo(String name);

    String selectMusicByAlbum(String name);
}
