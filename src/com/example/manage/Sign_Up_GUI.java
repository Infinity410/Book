package com.example.manage;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch6_textcoms.BETextAreaUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import  java.io.IOException;

public class Sign_Up_GUI {
    public Sign_Up_GUI(){
        Register();
    }
    public void Register(){
        UIManager.put("RootPane.setupButtonVisible",false);
        try{
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("注册界面");
            fr.setSize(500,500);
            fr.pack();
            fr.setLayout(null);

            JLabel newID = new JLabel("新账号:");
            newID.setBounds(150, 50, 100, 25);
            fr.add(newID);

            JLabel newPassword = new JLabel("密码:");
            newPassword.setBounds(150, 100, 100, 25);
            fr.add(newPassword);

            JLabel newPassword1 = new JLabel("确认密码:");
            newPassword1.setBounds(150, 150, 100, 25);
            fr.add(newPassword1);

            JLabel newName = new JLabel("姓名:");
            newName.setBounds(150, 200, 100, 25);
            fr.add(newName);

            JLabel newUnit = new JLabel("单位:");
            newUnit.setBounds(150, 250, 100, 25);
            fr.add(newUnit);

            JLabel newType = new JLabel("类型:");
            newType.setBounds(150, 300, 100, 25);
            fr.add(newType);

            JLabel newTelephone = new JLabel("电话:");
            newTelephone.setBounds(150, 350, 100, 25);
            fr.add(newTelephone);

            JLabel newCount = new JLabel("可借书数:");
            newCount.setBounds(150, 400, 100, 25);
            fr.add(newCount);

            JTextField newUserID = new JTextField();
            newUserID.setBounds(240, 50, 150, 25);
            fr.add(newUserID);

            JTextField newUserPassword = new JTextField();
            newUserPassword.setBounds(240, 100, 150, 25);
            fr.add(newUserPassword);

            JTextField newUserPassword1 = new JTextField();
            newUserPassword1.setBounds(240, 150, 150, 25);
            fr.add(newUserPassword1);

            JTextField newUserName = new JTextField();
            newUserName.setBounds(240, 200, 150, 25);
            fr.add(newUserName);

            JTextField newUserUnit = new JTextField();
            newUserUnit.setBounds(240, 250, 150, 25);
            fr.add(newUserUnit);

            JTextField newUserType = new JTextField();
            newUserType.setBounds(240, 300, 150, 25);
            fr.add(newUserType);

            JTextField newUserTelephone = new JTextField();
            newUserTelephone.setBounds(240, 350, 150, 25);
            fr.add(newUserTelephone);

            JTextField newUserCount = new JTextField();
            newUserCount.setBounds(240, 400, 150, 25);
            fr.add(newUserCount);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 450, 70, 25);
            fr.add(Button_Comfirm);

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(275, 450, 70, 25);
            fr.add(Button_Cancle);

            fr.setSize(500, 600);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

            UserDao userDaoImlp = new UserDaoImlp();

            Button_Comfirm.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    String s1=newUserPassword.getText();
                    boolean flag1 = s1.equals(newUserPassword1.getText());
                    if(flag1==true) {
                        int a = Integer.parseInt(newUserType.getText());
                        int b = Integer.parseInt(newUserCount.getText());
                        User nUser = new User();
                        nUser.User(newUserID.getText(),newUserPassword.getText(),newUserName.getText(),newUserUnit.getText(),a,newUserTelephone.getText(), b);
                        UserDaoImlp zxc= new UserDaoImlp();
                        zxc.registerUser(nUser);
                        JOptionPane.showMessageDialog(null, "注册成功", "注册成功", JOptionPane.NO_OPTION);
                        fr.dispose();
                        UserManagement management = new UserManagement();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "两次密码不一致", "密码不一致", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            Button_Cancle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    UserManagement management = new UserManagement();
                }
            });
        }catch(Exception e) {
                e.printStackTrace();
        }
    }
}
