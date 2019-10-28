package com.controller;

import com.entity.Music;
import com.entity.Musictype;
import com.service.impl.MusicTypeService;
import com.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
public class MusicTypeController
{
    @Autowired
    private MusicTypeService ms;

    @Autowired
    private RedisClient client;


    //查询所有音乐类型
    @RequestMapping(value = "/selectAllMusicType",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Musictype> selectAllMusicType() throws Exception
    {
        List<Musictype> musicType = (List<Musictype>)client.get("MusicType");
        if(client.get("MusicType")==null || client.get("MusicType")=="")
        {
            musicType=ms.selectAllMusicType();
            client.set("MusicType",musicType);
        }
        return musicType;
    }


    @RequestMapping(value = "/selectImageByMusicType/{musictype}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Musictype selectImageByMusicType(@PathVariable(value = "musictype") String musictype) throws Exception
    {
        return ms.selectImageByMusicType(musictype);
    }
}
