package com.example.manage.book;

import com.example.manage.UserManagement;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BookManagement {
    private List<Book> books;
    private int userType;
    private static final File file = new File("Book.txt");
    public BookManagement(){
        books = new ArrayList<>();
    }
    public void init(int userType1){
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame frame = new JFrame("图书管理");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            frame.setSize(500, 400);
            frame.pack();
            frame.setLayout(null);

            JLabel title = new JLabel("图书管理子系统");
            Font font = new Font("宋体", Font.BOLD, 25);
            title.setFont(font);
            title.setBounds(155, 10, 500, 25);
            frame.add(title);

            JButton Button_Input = new JButton("图书信息录入");
            Button_Input.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Input.setForeground(Color.white);
            Button_Input.setBounds(170, 50, 150, 25);
            frame.add(Button_Input);

            JButton Button_Update = new JButton("图书信息修改");
            Button_Update.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Update.setForeground(Color.white);
            Button_Update.setBounds(170, 100, 150, 25);
            frame.add(Button_Update);

            JButton Button_Delete = new JButton("图书信息删除");
            Button_Delete.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Delete.setForeground(Color.white);
            Button_Delete.setBounds(170, 150, 150, 25);
            frame.add(Button_Delete);

            JButton Button_Search = new JButton("图书信息查询");
            Button_Search.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Search.setForeground(Color.white);
            Button_Search.setBounds(170, 200, 150, 25);
            frame.add(Button_Search);

            JButton Button_MenuExit = new JButton("返回主菜单");
            Button_MenuExit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_MenuExit.setForeground(Color.white);
            Button_MenuExit.setBounds(170, 250, 150, 25);
            frame.add(Button_MenuExit);

            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            Button_MenuExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    UserManagement userManagement = new UserManagement();
                    userManagement.menu(userType1);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(){

    }
    public void input(){

    }
    public void delete(){

    }
    public void queryByNo(){

    }
    public void queryByName(){

    }
    public void queryByAuthor(){

    }

}
