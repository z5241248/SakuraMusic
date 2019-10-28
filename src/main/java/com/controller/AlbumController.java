package com.controller;

import com.entity.Album;
import com.entity.Singer;
import com.service.IAlbumService;
import com.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
public class AlbumController
{
    @Autowired
    private IAlbumService as;

    @RequestMapping(value = "/selectMaxByAlbumLikecount",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Album> selectMaxByAlbumLikecount() throws Exception
    {
        return as.selectMaxByAlbumLikecount();
    }


    @RequestMapping(value = "selectAlbumById/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Album selectByPrimaryKey(@PathVariable(value = "id")Integer id) throws Exception
    {
        return as.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "selectAlbumByName/{name}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Album> selectAlbumByName(@PathVariable(value = "name")String name) throws Exception
    {
        return as.selectAlbumByName(name);
    }

    @RequestMapping(value = "/selectAlbumInfo/{name}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<String> selectAlbumInfo(@PathVariable(value = "name")String name) throws Exception
    {
        String str = as.selectAlbumInfo(name);
        ArrayList<String> arrayList=new ArrayList<String>();
        if(str!="" || str!=null)
        {
            int len=str.length();
            if(len > 1)
            {
                String[] musics=str.split(",");
                for (String i : musics)
                {
                    Album b = as.selectByPrimaryKey(Integer.parseInt(i));
                    arrayList.add(b.getName());
                }
            }
            else
            {
                Album b = as.selectByPrimaryKey(Integer.parseInt(str));
                arrayList.add(b.getName());
            }
        }
        return arrayList;
    }
}
