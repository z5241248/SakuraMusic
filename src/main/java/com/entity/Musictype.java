package com.entity;

import java.io.Serializable;

public class Musictype implements Serializable
{
    private Integer id;

    private String musictype;

    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusictype() {
        return musictype;
    }

    public void setMusictype(String musictype) {
        this.musictype = musictype == null ? null : musictype.trim();
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return "Musictype{" +
                "id=" + id +
                ", musictype='" + musictype + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}