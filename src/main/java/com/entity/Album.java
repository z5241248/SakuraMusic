package com.entity;

import java.io.Serializable;

public class Album implements Serializable
{
    private Integer id;

    private String name;

    private Integer singer;

    private String all;

    private String date;

    private Integer likecount;

    private Integer sharecount;

    private String image;

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getSinger() {
        return singer;
    }

    public void setSinger(Integer singer) {
        this.singer = singer;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all == null ? null : all.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }
}