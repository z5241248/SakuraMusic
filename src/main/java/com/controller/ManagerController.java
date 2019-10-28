package com.controller;

import com.entity.Music;
import com.entity.Musictype;
import com.entity.Sealusers;
import com.entity.Singer;
import com.entity.Users;
import com.service.IMusicService;
import com.service.IMusicTypeService;
import com.service.ISealusersService;
import com.service.ISingerService;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
public class ManagerController {

    @Autowired
    private IMusicService ims;

    @Autowired
    private ISingerService iss;

    @Autowired
    private ISealusersService ise;

    @Autowired
    private IUserService iue;

    @Autowired
    private IMusicTypeService imts;

    @PostMapping(value = "/MusicManager",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public HashMap MusicManager(@RequestParam HashMap mp) throws Exception{

        Integer nowpage = Integer.valueOf((String)mp.get("nowpage"));
        String name = (String)mp.get("name");
        Integer max = Integer.valueOf((String)mp.get("max"));

        Integer pages;
        Integer maxpage;

        Integer MusicCount = ims.selectCount(mp);
//        System.out.println("查找歌曲总数="+MusicCount);

        if(MusicCount%max==0){
            maxpage = MusicCount/max;
        }else{
            maxpage = 1+(MusicCount/max);
        }

        if(maxpage==0){
            nowpage = 1;
        }else{
            if(nowpage>maxpage){
                nowpage = maxpage;
            }else if(nowpage<=0){
                nowpage = 1;
            }
        }


        pages =  (nowpage-1)*max;

        HashMap tomp = new HashMap();

        tomp.put("pages",pages);
        tomp.put("max",max);

        if(name!=null && name!=""){
            tomp.put("name",name);
        }

        List<Music> musiclist = ims.selectMusicByNameAndSingerOfpage(tomp);

//        for(Music m : musiclist ){
//            System.out.println(m.toString());
//        }

        HashMap tohm = new HashMap();
        tohm.put("musiclist",musiclist);
        tohm.put("maxpage",maxpage);
        tohm.put("MusicCount",MusicCount);
        tohm.put("nowpage",nowpage);

        return tohm;

    }

    @PostMapping(value = "/OneMusicManager",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public HashMap OneMusicManager(@RequestParam HashMap mp) throws Exception{
        System.out.println("OneMusicManager");
        Integer id = Integer.valueOf((String)mp.get("id"));
        Music music = ims.selectByPrimaryKey(id);
        List<Musictype> typelist = imts.selectAllMusicType2();

        HashMap tohm = new HashMap();
        tohm.put("music",music);
        tohm.put("typelist",typelist);
        System.out.println(typelist);
        return tohm;
    }

    @PostMapping(value = "/MusicDelete",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer MusicDelete(@RequestParam HashMap mp) throws Exception{
//        System.out.println("MusicDelete");
        Integer id = Integer.valueOf((String)mp.get("id"));
        Integer result = ims.musicdelete(id);
        return result;
    }

    @PostMapping(value = "/MusicRevise",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer MusicRevise(@RequestParam HashMap mp){
//        System.out.println("MusicRevise");

        String name = (String)mp.get("name");
        Integer singer = Integer.valueOf((String)mp.get("singer"));

        HashMap tomp = new HashMap();
        tomp.put("id",Integer.valueOf((String)mp.get("id")));
        tomp.put("type",Integer.valueOf((String)mp.get("type")));
        tomp.put("name",name);
        tomp.put("singer",singer);
        Integer result = ims.updateByPrimaryKey(tomp);
        return result;
    }

    @PostMapping(value = "/UserManager",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public HashMap UserManager(@RequestParam HashMap mp) throws Exception{
        System.out.println("UserManager");
        String username = (String)mp.get("username");
        System.out.println(username);
        Users users = iue.selectByIdAndSealtime(username);

        HashMap tohm = new HashMap();

        if(users!=null){
            tohm.put("users",users);
            tohm.put("ck","true");

            if(users.getSeal()==1){
                Integer userid = users.getId();
                Sealusers seal = ise.select(userid);
                tohm.put("time",seal.getTime());
            }

        }else{
            tohm.put("ck","false");
        }
        System.out.println(tohm);
        return tohm;
    }

    @PostMapping(value = "/UserSealing",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer UserSealing(@RequestParam HashMap mp) throws Exception{
        System.out.println("UserSealing");
        Integer userid = Integer.valueOf((String)mp.get("userid"));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar="+calendar);
        calendar.add(Calendar.MONTH, 1);
        String WillDate = sdf.format(calendar.getTime());
        System.out.print("一个月后的时间="+WillDate);

        //seal封号
        HashMap tomp = new HashMap();
        tomp.put("userid",userid);
        tomp.put("time",WillDate);

        //user封号标记:1
        HashMap usermp = new HashMap();
        usermp.put("id",userid);
        usermp.put("seal",1);
        Integer Sealresult = ise.insert(tomp);

        Integer Usersresult = iue.updateUser(usermp);

        Integer result = Sealresult + Usersresult;

        return result;

    }

    @PostMapping(value = "/UserSealed",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer UserSealed(@RequestParam HashMap mp) throws Exception{
        System.out.println("UserSealing");
        Integer userid = Integer.valueOf((String)mp.get("userid"));

        //seal解封
        Integer Sealresult = ise.deleteByUserid(userid);


        //user封号标记:0
        HashMap usermp = new HashMap();
        usermp.put("id",userid);
        usermp.put("seal",0);


        Integer Usersresult = iue.updateUser(usermp);

        Integer result = Sealresult + Usersresult;

        return result;

    }

}
