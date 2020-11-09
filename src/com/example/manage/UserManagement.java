package com.example.manage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagement extends JFrame{
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton Button_Login;
    private JTextField textField1;
    private JButton Button_Sign;
    private int count = 0;

    public UserManagement() {

        JFrame frame = new JFrame("登录界面");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        Button_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = textField1.getText();
                String password = new String(passwordField1.getPassword());
                UserDaoImlp userDaoImlp = new UserDaoImlp();
                boolean flag = userDaoImlp.isLoginUser(ID,password);

                if(flag == false){

                    count++;
                    if(count != 3){
                        if(ID.isEmpty() == true||password.isEmpty() == true){
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
