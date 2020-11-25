package com.example.manage.book;
import com.example.manage.UserManagement;
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

public class BookManagement {
    private List<Book> books;
    private int userType;
    private static final File file = new File("Book.txt");
    public BookManagement(){
        books = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String str = null;
            //循环判断
            while ((str = br.readLine()) != null) {
                String[] data = str.split("=>");
                Book book1 = new Book();
                book1.setNo(data[0]);
                book1.setName(data[1]);
                book1.setAuthor(data[2]);
                book1.setPress(data[3]);
                int CountBook = Integer.parseInt(data[4]);
                book1.setCount(CountBook);
                books.add(book1);
                //System.out.println(user1);
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
        for (Iterator<Book> iterator = books.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }
    public void init(int userType1){
        userType=userType1;
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

            Button_Input.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    input();
                }
            });

            JButton Button_Update = new JButton("图书信息修改");
            Button_Update.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Update.setForeground(Color.white);
            Button_Update.setBounds(170, 100, 150, 25);
            frame.add(Button_Update);
            Button_Update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    update();
                }
            });

            JButton Button_Delete = new JButton("图书信息删除");
            Button_Delete.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Delete.setForeground(Color.white);
            Button_Delete.setBounds(170, 150, 150, 25);
            frame.add(Button_Delete);
            Button_Delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    delete();
                }
            });

            JButton Button_Search = new JButton("图书信息查询");
            Button_Search.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Search.setForeground(Color.white);
            Button_Search.setBounds(170, 200, 150, 25);
            frame.add(Button_Search);
            Button_Search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    search();
                }
            });

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
        Update_Book_GUI update_book_gui = new Update_Book_GUI();
    }
    public void input(){
        Add_Book_GUI add_book_gui = new Add_Book_GUI(userType);

    }
    public void delete(){
        Delete_Book_GUI delete_book_gui = new Delete_Book_GUI();
    }
    public void search() {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame frame = new JFrame("查询界面");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            frame.setSize(500, 200);
            frame.pack();
            frame.setLayout(null);

            JButton Button_BookSearchNo = new JButton("按书号查询");
            Button_BookSearchNo.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_BookSearchNo.setForeground(Color.white);
            Button_BookSearchNo.setBounds(170, 50, 150, 25);
            frame.add(Button_BookSearchNo);

            JButton Button_BookSearchName = new JButton("按书名查询");
            Button_BookSearchName.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_BookSearchName.setForeground(Color.white);
            Button_BookSearchName.setBounds(170, 100, 150, 25);
            frame.add(Button_BookSearchName);

            JButton Button_BookSearchAuthor = new JButton("按作者查询");
            Button_BookSearchAuthor.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_BookSearchAuthor.setForeground(Color.white);
            Button_BookSearchAuthor.setBounds(170, 150, 150, 25);
            frame.add(Button_BookSearchAuthor);

            JButton Button_SearchExit = new JButton("退出");
            Button_SearchExit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_SearchExit.setForeground(Color.white);
            Button_SearchExit.setBounds(170, 200, 150, 25);
            frame.add(Button_SearchExit);
            Button_SearchExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    init(userType);
                }
            });

            frame.setSize(500, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void queryByNo(){

    }
    public void queryByName(){

    }
    public void queryByAuthor(){

    }

}
