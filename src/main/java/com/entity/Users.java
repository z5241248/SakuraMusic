package com.entity;

import java.io.Serializable;

public class Users implements Serializable
{
    private Integer id;

    private String name;

    private String password;

    private String username;

    private Integer type;

    private String likealbum;

    private String likemusic;

    private String image;

    private int seal;

    private String phone;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLikealbum() {
        return likealbum;
    }

    public void setLikealbum(String likealbum) {
        this.likealbum = likealbum == null ? null : likealbum.trim();
    }

    public String getLikemusic() {
        return likemusic;
    }

    public void setLikemusic(String likemusic) {
        this.likemusic = likemusic == null ? null : likemusic.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public int getSeal()
    {
        return seal;
    }

    public void setSeal(int seal)
    {
        this.seal = seal;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public String toString()
    {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", type=" + type +
                ", likealbum='" + likealbum + '\'' +
                ", likemusic='" + likemusic + '\'' +
                ", image='" + image + '\'' +
                ", seal=" + seal +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Users(String name, String password)
    {
        this.name = name;
        this.password = password;
    }


    public Users(String name, String password, String username)
    {
        this.name = name;
        this.password = password;
        this.username = username;
    }


    public Users(String name, String password, String username, String phone)
    {
        this.name = name;
        this.password = password;
        this.username = username;
        this.phone = phone;
    }

    public Users(){}
}