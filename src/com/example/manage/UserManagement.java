package com.example.manage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagement extends JFrame{
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton Button_Login;
    private JTextField textField1;
    private JButton Button_Sign;
    private int count = 0;
    private String ID;
    private String password1;

    public UserManagement() {
        init();
//        JFrame frame = new JFrame("登录界面");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setSize(500,300);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        textField1 = new JTextField();
//        passwordField1 = new JPasswordField();

//
//        User user1 = new User();
//        user1.setID("abc");
//        user1.setPassword("123456");
//        user1.setType(1);
//        user1.setName("李四");
//        user1.setUnit("网络中心");
//        user1.setTelephone("2284036");
//        user1.setCount(10);
//
//        userDaoImlp.registerUser(user1);


    }

    public void init() {
        JFrame frame = new JFrame("登录界面");
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        frame.setSize(500,200);
        frame.pack();
        frame.setLayout(null);

        JLabel nameStr = new JLabel("账号:");
        nameStr.setBounds(150, 0, 100, 25);
        frame.add(nameStr);

        JLabel passwordStr = new JLabel("密码:");
        passwordStr.setBounds(150, 50, 100, 25);
        frame.add(passwordStr);

        JTextField userID = new JTextField();
        userID.setBounds(200, 0, 150, 25);
        frame.add(userID);

        JPasswordField password = new JPasswordField();
        password.setBounds(200, 50, 150, 25);
        frame.add(password);

        JButton Button_Login = new JButton("登录");
        Button_Login.setBounds(175, 100, 70, 25);
        frame.add(Button_Login);

        JButton Button_Sign = new JButton("注册");
        Button_Sign.setBounds(275, 100, 70, 25);
        frame.add(Button_Sign);

        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        UserDao userDaoImlp = new UserDaoImlp();

        Button_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ID = userID.getText();
                System.out.println(ID);
                password1 = new String(password.getPassword());
                System.out.println(password1);
                boolean flag = userDaoImlp.isLoginUser(ID,password1);

                if(flag == false){

                    count++;
                    if(count != 3){
                        if(ID.isEmpty() == true||password1.isEmpty() == true){
                            JOptionPane.showMessageDialog(null, "用户名和密码不能为空", "用户名和密码不能为空", JOptionPane.WARNING_MESSAGE);
                        }else {
                            //弹出账号或密码错误的窗口
                            JOptionPane.showMessageDialog(null, "账号或密码错误", "账号或密码错误", JOptionPane.WARNING_MESSAGE);
                            //清除密码框中的信息
                            passwordField1.setText("");
                            //清除账号框中的信息
                            textField1.setText("");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "账号或密码错误超过上限", "账号或密码错误超过上限", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                    }

                }else{
                    //弹出登录成功的窗口
                    JOptionPane.showMessageDialog(null, "登陆成功", "登陆成功", JOptionPane.NO_OPTION);
                    //点击确定后会跳转到主窗口
                    //frame.setVisible(false);
                }
            }
        });
        Button_Sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDaoImlp userDaoImlp = new UserDaoImlp();
                User u1 = new User();
                userDaoImlp.registerUser(u1);

            }
        });
    }




}
