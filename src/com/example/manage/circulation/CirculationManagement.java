package com.example.manage.circulation;

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

public class CirculationManagement {
    List<Circulation> circulations;
    private int userType;
    private String operator;
    private static final File file = new File("Circulation.txt");
    public CirculationManagement(){
        circulations = new ArrayList<>();
    }
    public void init(int userType1,String operator1){
        UIManager.put("RootPane.setupButtonVisible", false);
        System.out.println(operator1);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame frame = new JFrame("图书流通管理");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            frame.setSize(500, 300);
            frame.pack();
            frame.setLayout(null);

            JLabel title = new JLabel("图书流通管理子系统");
            Font font = new Font("宋体", Font.BOLD, 25);
            title.setFont(font);
            title.setBounds(125, 10, 500, 25);
            frame.add(title);

            JButton Button_Lend = new JButton("借书处理");
            Button_Lend.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Lend.setForeground(Color.white);
            Button_Lend.setBounds(170, 50, 150, 25);
            frame.add(Button_Lend);

            JButton Button_Return = new JButton("还书处理");
            Button_Return.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Return.setForeground(Color.white);
            Button_Return.setBounds(170, 100, 150, 25);
            frame.add(Button_Return);

            JButton Button_Query = new JButton("借阅信息查询");
            Button_Query.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Query.setForeground(Color.white);
            Button_Query.setBounds(170, 150, 150, 25);
            frame.add(Button_Query);

            JButton Button_MenuExit = new JButton("返回主菜单");
            Button_MenuExit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_MenuExit.setForeground(Color.white);
            Button_MenuExit.setBounds(170, 200, 150, 25);
            frame.add(Button_MenuExit);

            frame.setSize(500, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            Button_MenuExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    UserManagement userManagement = new UserManagement(operator1);
                    userManagement.menu(userType1);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void lendBook(){

    }
    public void returnBook(){

    }
    public void query(){

    }
}