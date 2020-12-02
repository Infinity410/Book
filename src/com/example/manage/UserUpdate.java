package com.example.manage;

import com.example.manage.book.Book;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserUpdate {
    private List<User> users;
    private String userNo;
    private static final File file = new File("User.txt");

    public void Update_User_GUI(){
        init();
        users = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String str = null;
            //循环判断
            while ((str = br.readLine()) != null) {
                String[] data = str.split("=>");
                User user = new User();
                user.setID(data[0]);
                user.setName(data[1]);
                user.setPassword(data[2]);
                user.setTelephone(data[3]);
                int CountBook = Integer.parseInt(data[4]);
                user.setCount(CountBook);
                users.add(user);
            }

        } catch (FileNotFoundException e) {
            System.out.println("文件读入异常：" + e.getMessage());
        } catch (IOException e) {
            System.out.println("文件读入异常：" + e.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("关闭BufferedReader输入流异常：" + e.getMessage());
            }
        }
    }

    public void init(){
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("用户修改界面");
            fr.setSize(500, 200);
            fr.pack();
            fr.setLayout(null);

            JLabel newNo = new JLabel("用户名:");
            newNo.setBounds(130, 50, 100, 25);
            fr.add(newNo);

            JTextField newUserNo = new JTextField();
            newUserNo.setBounds(200, 50, 150, 25);
            fr.add(newUserNo);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 100, 70, 25);
            fr.add(Button_Comfirm);
            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userNo = newUserNo.getText().toString();
                    System.out.println(userNo);
                    int i;
                    for(i = 0; i < users.size(); i++){
                        if(userNo.equals(users.get(i).getID())){
                            System.out.println(users.get(i).getID());
                            JOptionPane.showMessageDialog(null, "查找成功", "查找成功", JOptionPane.NO_OPTION);
                            fr.setVisible(false);
                            detail(i);
                            break;
                        }
                    }
                    if(i == users.size()){
                        JOptionPane.showMessageDialog(null, "没找到这本书", "没找到这本书", JOptionPane.WARNING_MESSAGE);
                        newUserNo.setText("");
                    }
                }
            });

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(275, 100, 70, 25);
            fr.add(Button_Cancle);

            fr.setSize(500, 200);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detail(int i){
        UIManager.put("RootPane.setupButtonVisible",false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("用户信息修改界面");
            fr.setSize(700, 400);
            fr.pack();
            fr.setLayout(null);

            Object[] columnName={"用户名","名字","密码","学院","电话","可借书数"};
            Object[][] rowData={{users.get(i).getID(),users.get(i).getName(),users.get(i).getPassword(),users.get(i).getUnit(),users.get(i).getTelephone(),users.get(i).getCount()}};
            JTable table = new JTable(rowData,columnName);
            table.setBounds(20,10,700,20);


            fr.add(table);

            JLabel newName = new JLabel("用户名:");
            newName.setBounds(150, 50, 100, 25);
            fr.add(newName);

            JLabel newAuthor = new JLabel("名字:");
            newAuthor.setBounds(150, 100, 100, 25);
            fr.add(newAuthor);

            JLabel newPress = new JLabel("密码:");
            newPress.setBounds(150, 150, 100, 25);
            fr.add(newPress);

            JLabel newTelephone = new JLabel("电话:");
            newTelephone.setBounds(150, 200, 100, 25);
            fr.add(newTelephone);

            JLabel newCount = new JLabel("可借书数:");
            newCount.setBounds(150, 250, 100, 25);
            fr.add(newCount);

            JTextField newUserID = new JTextField();
            newUserID.setBounds(240, 50, 150, 25);
            fr.add(newUserID);

            JTextField newUserName = new JTextField();
            newUserName.setBounds(240, 100, 150, 25);
            fr.add(newUserName);

            JTextField newUserPassword = new JTextField();
            newUserPassword.setBounds(240, 150, 150, 25);
            fr.add(newUserPassword);

            JTextField newUserTelephone = new JTextField();
            newUserTelephone.setBounds(240, 200, 150, 25);
            fr.add(newTelephone);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 250, 70, 25);
            fr.add(Button_Comfirm);

            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    users.get(i).setID(newUserID.getText());
                    users.get(i).setName(newUserName.getText());
                    users.get(i).setPassword(newUserPassword.getText());
                    users.get(i).setTelephone(newUserTelephone.getText());
                    int count_int = Integer.parseInt(newCount.getText());
                    users.get(i).setCount(count_int);
                    for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                }
            });

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(275, 250, 70, 25);
            fr.add(Button_Cancle);

            fr.setSize(700, 400);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

