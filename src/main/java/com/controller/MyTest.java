package com.controller;


import com.entity.*;
import com.service.ISealusersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
public class MyTest {

    @Autowired
    private ISealusersService iss;

    //判断是否解封
    @PostMapping(value = "/SealUser",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer SealUser(@RequestParam HashMap mp) throws Exception {
        System.out.println("SealUser");
        Integer id = Integer.valueOf((String)mp.get("id"));
        Integer month = Integer.valueOf((String)mp.get("month"));
        System.out.println(id+"-"+month);

        //测试 当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String NowDate = sdf.format(date);
        System.out.print("当前时间="+NowDate);

        //测试 封号1一个月 将WillDate的时间存入数据库
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        String WillDate = sdf.format(calendar.getTime());
        System.out.print("一个月后的时间="+WillDate);

        //测试 从数据库中拿到一个时间 在后面与当前时间作比较
        String tdate = "2019-9-16 16:58:03";
        Date TDate = sdf.parse(tdate);
        String testDate = sdf.format(TDate);
        System.out.print("测试时间="+testDate);

        //测试 当前时间与数据库解封时间 比较
        int result = NowDate.compareTo(WillDate);
        if(result>=0){
            System.out.print("已解封");
        }else{
            System.out.print("解封时间为="+WillDate);
        }

        return 1;
    }
}
