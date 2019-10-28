package com.util;

import com.entity.Sealusers;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Quartz_SealJob implements Job
{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("-------------" + printTime + ",检测封号");

        seal();
    }



//    检测封号
    private void seal()
    {
        //当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String datetime=dateFormat.format(date);

        //数据库连接参数
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/music";
        String username = "root";
        String password = "root";

        //连接
        Connection conn = null;
        Statement stmt = null;
        try
        {
            //注册驱动
            Class.forName(JDBC_DRIVER);
            //打开连接
            conn=DriverManager.getConnection(URL,username,password);
            //执行
            stmt=conn.createStatement();
            String sql="select * from sealusers where time <= ?";
            //创建PreparedStatement对象
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,datetime);
            //获得结果
            ResultSet rs=ps.executeQuery();
            List idList=new ArrayList();
            while (rs.next())
            {
                //得到所有的id,存入list
                idList.add(rs.getString(2));
            }

            if(idList.size()!=0)
            {
                for(int i=0;i<idList.size();i++)
                {
                    //删除sealusers内数据
                    String del="DELETE FROM sealusers WHERE userid= ? ";
                    ps=conn.prepareStatement(del);
                    String id=idList.get(i).toString();
                    ps.setString(1,id);
                    ps.execute();

                    //更新用户数据
                    String update="UPDATE users SET seal = 0 WHERE id= ? ";
                    ps=conn.prepareStatement(update);
                    ps.setString(1,id);
                    ps.execute();
                }
            }
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
    }
}
