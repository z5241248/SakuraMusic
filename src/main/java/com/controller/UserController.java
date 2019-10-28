package com.controller;

import com.entity.Music;
import com.entity.Users;
import com.service.IMusicService;
import com.service.IUserService;
import com.util.RedisClient;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
@PropertySource(value = "classpath:path.properties",encoding = "UTF-8")
public class UserController
{
    @Value("${headImagePath}")
    private String headPath;

    @Autowired
    private IUserService us;

    @Autowired
    private RedisClient client;

    @PostMapping(value = "/login",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Users login(@RequestParam HashMap mp, HttpSession session) throws Exception
    {
        String name=(String)mp.get("name");
        String password=(String)mp.get("password");
        Users u=us.login(new Users(name,password));
        if(u!=null)
        {
            session.setAttribute("user",u);
        }
        return u;
    }


    @PostMapping(value = "/insertUser",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer insertUser(@RequestParam HashMap mp) throws Exception
    {
        Users u=new Users((String) mp.get("name") , (String)mp.get("password") , (String)mp.get("username") ,  (String)mp.get("phone") );
        Integer num = us.insertUser(u);
        return num;
    }


    //根据名字查询信息
    @PostMapping(value = "/selectByName/{name}" ,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Users selectByName(@PathVariable(value = "name") String name) throws Exception
    {
        return us.selectUserByName(name);
    }

    //查询重复内容
    @RequestMapping(value = "/selectName/{name}" ,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer selectName(@PathVariable(value = "name")String name) throws Exception
    {
        return us.selectName(name);
    }


    //更新likecount

    //增加
    @Transactional
    @PostMapping(value = "/updateLikeCount" ,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer updateLikeCount(@RequestParam Map<String, Object> mp) throws Exception
    {
        return us.updateLikeCount(mp);
    }


    //减少
    @Transactional
    @PostMapping(value = "/reduceLikeCount" ,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer reduceLikeCount(@RequestParam Map<String, Object> mp) throws Exception
    {
        System.out.println(mp.toString());
        return us.reduceLikeCount(mp);
    }


    //查询session
    @PostMapping(value = "/selectUserSession",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Users selectUserSession(HttpSession session) throws Exception
    {
        Object obj=session.getAttribute("user");
        Users u=(Users)obj;
        return u;
    }


    //根据手机号查询信息
    @PostMapping(value = "/selectByPhone/{phone}" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Users selectByPhone(@PathVariable(value = "phone") String phone) throws Exception
    {
        System.out.println("selectByPhone");
        return us.selectByPhone(phone);
    }


    //查询所有封号用户
    @PostMapping(value = "/selectBySeal" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List selectBySeal()
    {
//        System.out.println("查询所有封号用户");
        return us.selectBySeal();
    }


    //退出
    @RequestMapping(value = "/takeOff")
    public void takeOff(HttpSession session) throws Exception
    {
        client.del("user");
        session.removeAttribute("user");
    }
}
