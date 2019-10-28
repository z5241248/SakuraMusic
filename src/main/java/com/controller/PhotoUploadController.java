package com.controller;

import com.service.IMusicService;
import com.service.IUserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@PropertySource(value = "classpath:path.properties",encoding = "UTF-8")
public class PhotoUploadController {

    @Value("${filePath}")
    private String filePath;

    @Value("${webPath}")
    private String webPath;

//Music 图片上传地址
    @Value("${MusicfilePath}")
    private String MusicfilePath;

    @Value("${MusicwebPath}")
    private String MusicwebPath;

    @Autowired
    private IUserService ius;

    @Autowired
    private IMusicService ims;

    @PostMapping("/uploadfile")
    @ResponseBody
    public Boolean uploadfile(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id){
        Boolean ckall  = false;
        String webname = null ;
        System.out.println(ckall);
        System.out.println(" ---- filePath = "+filePath);
        System.out.println(" ---- webPath = "+webPath);
        System.out.println(" ---- files == "+file);
        System.out.println(" ---- id == "+id);
        List<String> fileList = new ArrayList();
        // 上传文件
        boolean ckup = true ;//
        try{
            if(file !=null) {
                    String fname = file.getOriginalFilename();
                    int index = fname.lastIndexOf(".");
                    fname = fname.substring(index);
                    UUID uuid = UUID.randomUUID();
                    fname = uuid.toString().replace("-","") + fname;

                    webname = webPath + fname;
                    System.out.println(webname);
                    fileList.add(webname);

                    File ff = new File(filePath,fname);
                    FileUtils.copyInputStreamToFile(file.getInputStream(),ff);

            }
        }catch (Exception e){
            ckup = false;
            e.printStackTrace();
        }

        System.out.println(" ---- fileList = "+fileList);

        if(ckup){ // 存数据
            try {
                HashMap inmp = new HashMap();

                inmp.put("id",id);
                inmp.put("image",webname);
                Integer num2 = ius.updateUser(inmp);
                System.out.println(" ---- num2 = "+num2);
                if(num2 > 0) {
                    ckall = true;
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        return ckall;
    }

    @PostMapping("/MusicRevisePhoto")
    @ResponseBody
    public Boolean MusicRevisePhoto(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id){
        Boolean ckall  = false;
        String webname = null ;
        System.out.println(ckall);
        System.out.println(" ---- MusicfilePath = "+MusicfilePath);
        System.out.println(" ---- MusicwebPath = "+MusicwebPath);
        System.out.println(" ---- files == "+file);
        System.out.println(" ---- id == "+id);
        List<String> fileList = new ArrayList();
        // 上传文件
        boolean ckup = true ;//
        try{
            if(file !=null) {
                String fname = file.getOriginalFilename();
                int index = fname.lastIndexOf(".");
                fname = fname.substring(index);
                UUID uuid = UUID.randomUUID();
                fname = uuid.toString().replace("-","") + fname;

                webname = MusicwebPath + fname;
                System.out.println(webname);
                fileList.add(webname);

                File ff = new File(MusicfilePath,fname);
                FileUtils.copyInputStreamToFile(file.getInputStream(),ff);

            }
        }catch (Exception e){
            ckup = false;
            e.printStackTrace();
        }

        System.out.println(" ---- fileList = "+fileList);

        if(ckup){ // 存数据
            try {
                HashMap inmp = new HashMap();

                inmp.put("id",id);
                inmp.put("image",webname);
                Integer num2 = ims.updateByHeadPhoto(inmp);
                System.out.println(" ---- num2 = "+num2);
                if(num2 > 0) {
                    ckall = true;
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        return ckall;
    }

}
