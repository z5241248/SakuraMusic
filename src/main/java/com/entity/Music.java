package com.entity;

import java.io.Serializable;

public class Music implements Serializable
{
    private Integer id;

    private String name;

    private Integer singer;

    private Integer album;

    private Integer type;

    private Integer likecount;

    private Integer sharecount;

    private String musicaddress;

    private String lrcaddress;

    private String image;

    private Singer singerss;

    private Album albumss;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSinger() {
        return singer;
    }

    public void setSinger(Integer singer) {
        this.singer = singer;
    }

    public Integer getAlbum() {
        return album;
    }

    public void setAlbum(Integer album) {
        this.album = album;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public Integer getSharecount() {
        return sharecount;
    }

    public void setSharecount(Integer sharecount) {
        this.sharecount = sharecount;
    }

    public String getMusicaddress() {
        return musicaddress;
    }

    public void setMusicaddress(String musicaddress) {
        this.musicaddress = musicaddress;
    }

    public String getLrcaddress() {
        return lrcaddress;
    }

    public void setLrcaddress(String lrcaddress) {
        this.lrcaddress = lrcaddress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Singer getSingerss() {
        return singerss;
    }

    public Album getAlbumss() {
        return albumss;
    }

    public void setAlbumss(Album albumss) {
        this.albumss = albumss;
    }

    public void setSingerss(Singer singerss) {
        this.singerss = singerss;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singer=" + singer +
                ", album=" + album +
                ", type=" + type +
                ", likecount=" + likecount +
                ", sharecount=" + sharecount +
                ", musicaddress='" + musicaddress + '\'' +
                ", lrcaddress='" + lrcaddress + '\'' +
                ", image='" + image + '\'' +
                ", singerss=" + singerss +
                ", albumss=" + albumss +
                '}';
    }
}