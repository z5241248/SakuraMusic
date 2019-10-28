package com.controller;

import com.entity.Music;
import com.service.IAlbumService;
import com.service.IMusicService;
import com.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
public class MusicController
{
    @Autowired
    private IMusicService ms;

    @Autowired
    private IAlbumService as;

    @Autowired
    private RedisClient client;

    //查询前4名歌曲
    @RequestMapping(value = "/selectMaxByMusicLikecount",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Music> selectMaxByMusicLikecount() throws Exception
    {
        return ms.selectMaxByMusicLikecount();
    }

    //随机查询歌曲
    @RequestMapping(value = "/selectRandMusic",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Music> selectRandMusic() throws Exception
    {
        List<Music> musicList = (List<Music>)client.get("RandMusic");
        if(client.get("RandMusic")==null || client.get("RandMusic")=="")
        {
            musicList=ms.selectRandMusic();
            client.set("RandMusic",musicList);
            client.expire("RandMusic",1800);
        }
        return musicList;
    }


    //模糊查询
    @RequestMapping(value = "/selectMusicByName/{name}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Music> selectByName(@PathVariable(value = "name")String name)throws Exception
    {
        return ms.selectByName(name);
    }


    //根据专辑名查询音乐并返回列表
    @RequestMapping(value = "/selectMusicByAlbumName/{name}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Music> selectMusicByAlbumName(@PathVariable(value = "name")String name)throws Exception
    {
        String str = as.selectMusicByAlbum(name);
        ArrayList<Music> arrayList=new ArrayList<Music>();
        if(str!="" || str!=null)
        {
            int len=str.length();
            if(len > 1)
            {
                String[] musics=str.split(",");
                for (String i : musics)
                {
                    Music m=ms.selectByPrimaryKey(Integer.parseInt(i));
                    arrayList.add(m);
                }
            }
            else
            {
                Music m=ms.selectByPrimaryKey(Integer.parseInt(str));
                arrayList.add(m);
            }
        }
        return arrayList;
    }


    @RequestMapping(value = "/selectMusicByNameAndSinger" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Music> selectMusicByNameAndSinger(@RequestParam Map<String,Object> mp)throws Exception
    {
        return ms.selectMusicByNameAndSinger(mp);
    }


    //查询最爱的音乐
    @RequestMapping(value = "/favoriteMusic/{name}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Music favoriteMusic(@PathVariable(value = "name")String name) throws Exception
    {
        return ms.favoriteMusic(name);
    }



    //根据名称和歌手id查询
    @RequestMapping(value = "/searchPlay" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Music searchPlay(@RequestParam Map<String, Object> mp) throws Exception
    {
        return ms.searchPlay(mp);
    }


    //根据type查询音乐
    @RequestMapping(value = "/selectByMusicTypeId/{type}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Music> selectByMusicTypeId(@PathVariable(value = "type")Integer type) throws Exception
    {
        return ms.selectByMusicTypeId(type);
    }


    //更新数据
    @PostMapping(value = "/updateCount/{name}/{id}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer updateCount(@PathVariable(value = "name")String name,@PathVariable(value = "id")Integer id) throws Exception
    {
        return ms.updateCount(name,id);
    }

    @PostMapping(value = "/reduceCount/{name}/{id}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer reduceCount(@PathVariable(value = "name")String name,@PathVariable(value = "id")Integer id) throws Exception
    {
        return ms.reduceCount(name,id);
    }
}
