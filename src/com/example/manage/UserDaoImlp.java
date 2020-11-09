package com.example.manage;

import javax.swing.*;
import java.io.*;

public class UserDaoImlp implements UserDao{
    private static final File file = new File("User.txt");
    @Override
    public void registerUser(User user) {
        BufferedWriter bw = null;
        if(user!=null){
            try{
                bw = new BufferedWriter(new FileWriter(file,true));
                bw.write(user.getID()+"=>"+user.getPassword()+"=>"+user.getType()+"=>"+user.getName()+"=>"+user.getUnit()+"=>"+user.getTelephone()+"=>"+user.getCount());
                bw.newLine();
                bw.flush();
            }catch (IOException e) {
                System.out.println("注册失败："+e.getMessage());
            }finally{
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println("关闭BufferedWriter输出流异常："+e.getMessage());
                }
            }
        }else{
            //注册失败信息
            System.out.println("注册失败：用户信息不能为空");
        }
    }

    @Override
    public boolean isLoginUser(String name, String password) {
        boolean flag = false;
        //初始化BufferedReader
        BufferedReader br = null;
        if(!name.equals("") && !password.equals("")){
            try {
                br = new BufferedReader(new FileReader(file));
                String str = null;
                //循环判断
                while((str = br.readLine()) != null){
                    String[] data = str.split("=>");
                    if(data[0].equals(name)&&data[1].equals(password)){
                        flag = true;
                        break;
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("登录异常："+e.getMessage());
            } catch (IOException e) {
                System.out.println("登录异常："+e.getMessage());
            }finally{
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("关闭BufferedReader输入流异常："+e.getMessage());
                }
            }
        }else{

            System.out.println("用户名和密码不能为空");
        }
        return flag;
    }
}
