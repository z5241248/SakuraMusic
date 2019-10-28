package com.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(allowCredentials = "true",maxAge = 3600)//ajax跨域
@RestController
public class MessageController
{
    @Autowired
    private SmsUtil smsUtil;

    String yzm;

    @RequestMapping(value = "/sms/{phone}")
    public void sms(@PathVariable(value = "phone") String phone) throws ClientException
    {
        System.out.println("------------进入短信验证功能");
        System.out.println(phone);

        final String smscode = (long)(Math.random()*1000000)+"";
        /*定全局变量等于随机数*/
        yzm = smscode;
        Map<String,String> map1 = new HashMap<String, String>();
        map1.put("code",smscode);

        Map<String,String> map = new HashMap<String, String>();
        map.put("phone",phone);
        map.put("sign_name","益西江译");
        map.put("template_code","SMS_175241629");
        map.put("sms_code", JSON.toJSONString(map1));

        smsUtil.sendSms(map.get("phone"),map.get("sign_name"), map.get("template_code"),map.get("sms_code"));

    }



    //判断验证码
    @RequestMapping(value = "/judge/{judge}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer judge(@PathVariable(value = "judge") String judge) throws Exception
    {
        System.out.println("进入判断-----judge="+judge);
        System.out.println(yzm);
        Integer num=0;
        if(Integer.parseInt(judge)==Integer.parseInt(yzm))
        {
            num=1;
        }
        return num;
    }

}
