package com.example.manage;

import javax.swing.*;

public class UserManagement {
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton Button_Login;
    private JTextField textField1;
    private JButton Button_Sign;

    public void addview() {
        JFrame frame = new JFrame("登录界面");
        frame.setContentPane(new UserManagement().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



}
