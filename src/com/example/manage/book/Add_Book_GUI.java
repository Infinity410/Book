package com.example.manage.book;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Add_Book_GUI {
    private int userType;
    private static final File file = new File("Book.txt");
    public Add_Book_GUI(int userType1){
        userType=userType1;
        init();
    }
    public void init(){
        UIManager.put("RootPane.setupButtonVisible",false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("图书添加界面");
            fr.setSize(500, 400);
            fr.pack();
            fr.setLayout(null);

            JLabel newNo = new JLabel("书号:");
            newNo.setBounds(150, 50, 100, 25);
            fr.add(newNo);

            JLabel newName = new JLabel("书名:");
            newName.setBounds(150, 100, 100, 25);
            fr.add(newName);

            JLabel newAuthor = new JLabel("作者:");
            newAuthor.setBounds(150, 150, 100, 25);
            fr.add(newAuthor);

            JLabel newPress = new JLabel("出版社:");
            newPress.setBounds(150, 200, 100, 25);
            fr.add(newPress);

            JLabel newCount = new JLabel("藏书量:");
            newCount.setBounds(150, 250, 100, 25);
            fr.add(newCount);

            JTextField newBookNo = new JTextField();
            newBookNo.setBounds(240, 50, 150, 25);
            fr.add(newBookNo);


            JTextField newBookName = new JTextField();
            newBookName.setBounds(240, 100, 150, 25);
            fr.add(newBookName);

            JTextField newBookAuthor = new JTextField();
            newBookAuthor.setBounds(240, 150, 150, 25);
            fr.add(newBookAuthor);

            JTextField newBookPress = new JTextField();
            newBookPress.setBounds(240, 200, 150, 25);
            fr.add(newBookPress);

            JTextField newBookCount = new JTextField();
            newBookCount.setBounds(240, 250, 150, 25);
            fr.add(newBookCount);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 300, 70, 25);
            fr.add(Button_Comfirm);

            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Book newBook = new Book();
                    newBook.setNo(newBookNo.getText());
                    newBook.setName(newBookName.getText());
                    newBook.setAuthor(newBookAuthor.getText());
                    newBook.setPress(newBookPress.getText());
                    int count_int = Integer.parseInt(newBookCount.getText());
                    newBook.setCount(count_int);
                    add(newBook);
                    JOptionPane.showMessageDialog(null, "添加成功", "添加成功", JOptionPane.NO_OPTION);
                    fr.dispose();
                    BookManagement bookManagement = new BookManagement();
                    bookManagement.init(userType);
                }
            });

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(275, 300, 70, 25);
            fr.add(Button_Cancle);

            fr.setSize(500, 400);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void add(Book book){
        BufferedWriter bw = null;
        if(book!=null){
            try{
                bw = new BufferedWriter(new FileWriter(file,true));
                bw.write(book.getNo()+"=>"+book.getName()+"=>"+book.getAuthor()+"=>"+book.getPress()+"=>"+book.getCount());
                bw.newLine();
                bw.flush();
            }catch (IOException e) {
                System.out.println("添加失败："+e.getMessage());
            }finally{
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println("关闭BufferedWriter输出流异常："+e.getMessage());
                }
            }
        }else{
            //注册失败信息
            System.out.println("添加失败：图书信息不能为空");
        }
    }
}
