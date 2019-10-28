package com.controller;

import com.entity.Album;
import com.entity.Music;
import com.entity.Singer;
import com.entity.Users;
import com.service.IAlbumService;
import com.service.IMusicService;
import com.service.ISingerService;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
@PropertySource(value = "classpath:path.properties",encoding = "UTF-8")
public class UserCenterController {

    @Autowired
    private IUserService ius;

    @Autowired
    private IMusicService ims;

    @Autowired
    private IAlbumService ias;

    @Autowired
    private ISingerService iss;

    @PostMapping(value = "/SelectAllUser",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public HashMap SelectAllUser(@RequestParam HashMap mp) throws Exception
    {
        System.out.println("SelectAllUser----");

        Integer id = Integer.valueOf((String)mp.get("id"));
        System.out.println(id);
        Users user = ius.selectById(id);

        String likemusic = user.getLikemusic();
        String likealbum = user.getLikealbum();
        String[] musics = likemusic.split(";");
        String[] albums = likealbum.split(";");
//        System.out.println("所有音乐--"+musics);
//        System.out.println("所有专辑--"+albums);

        List<Music> listmusic = new ArrayList();
        List<Album> listalbum = new ArrayList();
        List<Singer> listalbumsinger = new ArrayList();
        List<Singer> listmusicsinger = new ArrayList();
        Integer musicCount = 0;
        Integer albumCount = 0;

        //拿到所有喜欢的音乐信息
        if(!likemusic.equals(""))
        {
            for (int i = 0; i < musics.length; i++)
            {
                Integer mid = Integer.parseInt(musics[i]);
                Music music = ims.selectByPrimaryKey(mid);
                listmusic.add(music);

                Integer sid = music.getSinger();
                Singer singer = iss.selectByPrimaryKey(sid);
                listmusicsinger.add(singer);

                musicCount++;

                if (i == 8)
                {
                    break;
                }
            }
        }

        //拿到所有收藏的专辑信息
        if(!likealbum.equals(""))
        {
            for (int i = 0; i < albums.length; i++)
            {
                Integer aid = Integer.parseInt(albums[i]);
                Album album = ias.selectByPrimaryKey(aid);
                listalbum.add(album);

                Integer sid = album.getSinger();
                Singer singer = iss.selectByPrimaryKey(sid);
                listalbumsinger.add(singer);

                albumCount++;

                if (i == 8)
                {
                    break;
                }
            }
        }

        System.out.println("所有音乐信息-list---:"+listmusic);
        System.out.println("所有专辑信息-list---:"+listalbum);


        HashMap tohm = new HashMap();
        tohm.put("listalbumsinger",listalbumsinger);
        tohm.put("listmusicsinger",listmusicsinger);
        tohm.put("listmusic",listmusic);
        tohm.put("listalbum",listalbum);
        tohm.put("albumCount",albumCount);
        tohm.put("musicCount",musicCount);


        return tohm;
    }

    @PostMapping(value = "/UserRevise",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer ReviseUsers(@RequestParam HashMap mp) throws Exception {
            System.out.println("1");
            Integer result = ius.updateUser(mp);
            System.out.println(result);
        return result;

    }

    @PostMapping(value = "/lookusers",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Users lookusers(@RequestParam HashMap mp) throws Exception {
        System.out.println("lookusers");
        Integer id = Integer.valueOf((String)mp.get("id"));
        System.out.println(id);
        Users user = ius.selectById(id);
        return user;

    }


}
