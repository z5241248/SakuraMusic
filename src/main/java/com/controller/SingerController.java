package com.controller;

import com.entity.Singer;
import com.service.ISingerService;
import com.util.RedisClient;
import com.util.SerializeUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
public class SingerController
{
    @Autowired
    private ISingerService is;

    @Autowired
    private RedisClient client;


    //查询随机4名歌手
    @RequestMapping(value = "/selectRandSinger",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Singer> selectRandSinger() throws Exception
    {
        List<Singer> list=(List<Singer>)client.get("RandSinger");
        if(client.get("RandSinger")==null || client.get("RandSinger")=="")
        {
            list=is.selectRandSinger();
            client.set("RandSinger",list);
            client.expire("RandSinger",1800);
        }
        return list;
    }


    //模糊查询
    @RequestMapping(value = "/selectSingerByName/{name}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Singer> selectByName(@PathVariable(value = "name")String name)throws Exception
    {
        return is.selectByName(name);
    }


    //id查询
    @RequestMapping(value = "/selectSingerById/{id}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Singer selectByPrimaryKey(@PathVariable(value = "id")Integer id)throws Exception
    {
        return is.selectByPrimaryKey(id);
    }


    //根据id查询
    @RequestMapping(value = "/selectByName2/{name}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Singer selectByName2(@PathVariable(value = "name")String name) throws Exception
    {
        String getName="selectByName2-"+name;
        Singer singer=(Singer)client.get(getName);
        if(client.get(getName)==null || client.get(getName)=="")
        {
            singer=is.selectByName2(name);
            client.set(getName , singer);
        }
        return singer;
    }
}
