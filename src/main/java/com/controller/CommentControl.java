package com.controller;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
@PropertySource(value = "classpath:path.properties",encoding = "UTF-8")
public class CommentControl
{
    //通用xml地址
    @Value("${xmlPath}")
    String path;

    @PostMapping(value = "/backCommentInfo",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer backCommentInfo(@RequestParam Map<String,Object> mp) throws Exception
    {
        int num=0;

        //用户名
        String username=(String) mp.get("username");
        //评论内容
        String info=(String) mp.get("info");
        //歌曲名
        String musicName=(String) mp.get("musicName");
        //歌手名
        String singerName=(String) mp.get("singerName");

        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //当前日期
        String date=df.format(new Date());

        //获取路径
        CommentControl com=new CommentControl();
        String address = path +musicName+"-"+singerName+".xml";
        File file=new File(address);
        //判断文件是否存在,true为存在
        boolean bool=judgeFileExists(file);

        //不存在则新建文件
        if(!bool)
        {
            try
            {
                int judgeNum=0;
                //创建xml
                Document doc= DocumentHelper.createDocument();
                //新建root节点Comments
                Element root=doc.addElement("comments");

                //格式化xml内容和设置头部标签
                OutputFormat format= OutputFormat.createPrettyPrint( );

                //设置xml文档编码为utf-8
                format.setEncoding( "UTF-8" );

                //输出
                Writer out;
                try
                {
                    //创建一个输出流对象
                    out=new FileWriter( address );
                    //创建一个dom4j创建xml对象
                    XMLWriter writer=new XMLWriter( out , format );
                    //写入doc文档到指定路径
                    writer.write( doc );
                    writer.close( );
                    System.out.println( "生成xml成功!" );
                    judgeNum=1;
                }
                catch ( Exception e )
                {
                    System.out.println( "生成xml失败!" );
                    e.printStackTrace( );
                    num = 0;
                }

                if(judgeNum==1)
                {
                    num = insertInfo(date,username,info,address);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            num = insertInfo(date,username,info,address);
        }

        System.out.println(num);
        return num;
    }


    //查询评论数额
    @RequestMapping(value = "/commentCount",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Integer commentCount(@RequestParam Map<String,Object> mp)throws Exception
    {
        int num=0;
        //歌曲名
        String musicName=(String) mp.get("musicName");
        //歌手名
        String singerName=(String) mp.get("singerName");

        //获取路径
        CommentControl com=new CommentControl();
        String address = path +musicName+"-"+singerName+".xml";
        File file=new File(address);
        //判断文件是否存在,true为存在
        boolean bool=judgeFileExists(file);

//        System.out.println(bool);

        if(bool)
        {
            //读取文件
            Document doc= null;
            try
            {
                SAXReader reader=new SAXReader();
                doc = reader.read(file);
                //得到根节点
                Element root = doc.getRootElement();
                //获取根节点下节点数
                int size = root.elements().size();
//            System.out.println(size);
                num = size;
            }
            catch (Exception e)
            {
                System.out.println("查询评论数额错误");
            }
        }
        else
        {
            num = 0;
        }

        return num;
    }


    //判断文件是否存在
    public static boolean judgeFileExists(File file)
    {
        boolean bool=false;
        if(file.exists())
        {
            //存在
            bool=true;
        }

        return bool;
    }


    //插入信息
    public static Integer insertInfo(String date,String username,String info,String address)
    {
        int num=0;
        //创建解析器对象
        SAXReader reader=new SAXReader( );
        try
        {
            //得到document
            Document doc=reader.read( new File(address));
            //得到根节点
            Element root=doc.getRootElement();
            //获取根节点下元素
            List<Element> list=root.elements();

            //创建需要插入的元素
            Element comment=DocumentHelper.createElement("comment");

            //添加id属性
            if(list.size()==0)
            {
                comment.addAttribute("id","1");
            }
            else
            {
                comment.addAttribute("id",list.size()+1+"");
            }

            //创建子节点
            //添加一个子节点username并赋值
            Element XMLusername=comment.addElement("username");
            XMLusername.setText(username);
            //继续添加
            Element XMLdate=comment.addElement("date");
            XMLdate.setText(date);
            Element XMLinfo=comment.addElement("info");
            XMLinfo.setText(info);

            //添加在最前面
            list.add(0,comment);

            try
            {
                //回写xml
                OutputFormat format=OutputFormat.createPrettyPrint( );
                XMLWriter writer=new XMLWriter( new FileOutputStream( address ) , format );
                writer.write( doc );
                writer.close( );
                System.out.println( "写入成功!" );
                num=1;
            }
            catch (Exception e)
            {
                System.out.println( "写入失败!" );
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return num;
    }



    //查询节点
    @RequestMapping(value = "/backInfo" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List backInfo(@RequestParam Map<String,Object> mp) throws Exception
    {
        System.out.println(mp.toString());
        //得到数据
        String musicName=(String) mp.get("musicName");
        String singerName=(String)mp.get("singerName");
        String str=(String)mp.get("page");
        int page= Integer.parseInt(str);

        ArrayList list=new ArrayList();
        //创建对象
        SAXReader reader=new SAXReader();
        String address=path+musicName+"-"+singerName+".xml";
        File file=new File(address);
        //判断文件是否存在
        if(!judgeFileExists(file))
        {
            return new ArrayList();
        }
        else
        {
            //读取文件
            Document doc= null;
            try
            {
                doc = reader.read( file );
                //得到根节点
                Element root=doc.getRootElement( );
                //获取根节点下节点数
                int size=root.elements().size();

                try
                {
                    int num=size-page*10;
                    while (size>num)
                    {
                        if(size <= root.elements().size()-(page-1)*10)
                        {
                            Node node=root.selectSingleNode("comment[@id='"+size+"']");
                            if(node.hasContent())
                            {
//                                System.out.println(node.getStringValue());
                                list.add(node.getStringValue());
                            }
                        }
                        size--;
                    }
                }
                catch (Exception e)
                {
                    System.out.println("已经读取到了最后一个节点");
                }
//                System.out.println(list);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return list;
        }
    }
}
