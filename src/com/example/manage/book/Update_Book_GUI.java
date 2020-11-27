package com.example.manage.book;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch5_table.BETableUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTableUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Update_Book_GUI {
    private List<Book> books;
    private String bookno;
    private String oper;
    private static final File file = new File("Book.txt");
    private int usertype;
    public Update_Book_GUI(int usertype1,String oper1){
        usertype=usertype1;
        oper=oper1;
        init();
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

            JFrame fr = new JFrame("图书修改界面");
            fr.setSize(500, 200);
            fr.pack();
            fr.setLayout(null);

            JLabel newNo = new JLabel("书号:");
            newNo.setBounds(130, 50, 100, 25);
            fr.add(newNo);

            JTextField newBookNo = new JTextField();
            newBookNo.setBounds(200, 50, 150, 25);
            fr.add(newBookNo);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 100, 70, 25);
            fr.add(Button_Comfirm);
            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bookno = newBookNo.getText().toString();
                    System.out.println(bookno);
                    int i;
                    for(i = 0; i < books.size(); i++){
                        if(bookno.equals(books.get(i).getNo())){
                            System.out.println(books.get(i).getNo());
                            JOptionPane.showMessageDialog(null, "查找成功", "查找成功", JOptionPane.NO_OPTION);
                            fr.setVisible(false);
                            detail(i);
                            break;
                        }
                    }
                    if(i == books.size()){
                        JOptionPane.showMessageDialog(null, "没找到这本书", "没找到这本书", JOptionPane.WARNING_MESSAGE);
                        newBookNo.setText("");
                    }
                }
            });

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(275, 100, 70, 25);
            fr.add(Button_Cancle);
            Button_Cancle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    BookManagement bookManagement = new BookManagement();
                    bookManagement.init(usertype,oper);
                }
            });

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

            JFrame fr = new JFrame("图书修改界面");
            fr.setSize(700, 400);
            fr.pack();
            fr.setLayout(null);

            Object[] columnName={"书号","书名","作者","出版社","藏书量"};
            Object[][] rowData={{books.get(i).getNo(),books.get(i).getName(),books.get(i).getAuthor(),books.get(i).getPress(),books.get(i).getCount()}};
            JTable table = new JTable(rowData,columnName);
            table.setBounds(20,10,700,20);


            fr.add(table);

//            JLabel newNo = new JLabel(books.get(i).toString());
//            newNo.setBounds(20, 50, 325, 25);
//            fr.add(newNo);

            JLabel newName = new JLabel("书名:");
            newName.setBounds(150, 50, 100, 25);
            fr.add(newName);

            JLabel newAuthor = new JLabel("作者:");
            newAuthor.setBounds(150, 100, 100, 25);
            fr.add(newAuthor);

            JLabel newPress = new JLabel("出版社:");
            newPress.setBounds(150, 150, 100, 25);
            fr.add(newPress);

            JLabel newCount = new JLabel("藏书量:");
            newCount.setBounds(150, 200, 100, 25);
            fr.add(newCount);

//            JTextField newBookNo = new JTextField();
//            newBookNo.setBounds(240, 50, 150, 25);
//            fr.add(newBookNo);


            JTextField newBookName = new JTextField();
            newBookName.setBounds(240, 50, 150, 25);
            fr.add(newBookName);

            JTextField newBookAuthor = new JTextField();
            newBookAuthor.setBounds(240, 100, 150, 25);
            fr.add(newBookAuthor);

            JTextField newBookPress = new JTextField();
            newBookPress.setBounds(240, 150, 150, 25);
            fr.add(newBookPress);

            JTextField newBookCount = new JTextField();
            newBookCount.setBounds(240, 200, 150, 25);
            fr.add(newBookCount);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 250, 70, 25);
            fr.add(Button_Comfirm);

            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    books.get(i).setName(newBookName.getText());
                    books.get(i).setPress(newBookPress.getText());
                    books.get(i).setAuthor(newBookAuthor.getText());
                    int count_int = Integer.parseInt(newBookCount.getText());
                    books.get(i).setCount(count_int);
                    BufferedWriter bw = null;
                    try{
                        bw = new BufferedWriter(new FileWriter(file));
                        for (int j = 0; j<books.size();j++ ){
                            bw.write(books.get(j).getNo()+"=>"+books.get(j).getName()+"=>"+books.get(j).getAuthor()+"=>"+books.get(j).getPress()+"=>"+books.get(j).getCount());
                            bw.newLine();
                            bw.flush();
                        }
                    }catch (IOException e1) {
                        System.out.println("修改失败："+e1.getMessage());
                    }finally{
                        try {
                            bw.close();
                        } catch (IOException e1) {
                            System.out.println("关闭BufferedWriter输出流异常："+e1.getMessage());
                        }
                    }
                    for (Iterator<Book> iterator = books.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                    JOptionPane.showMessageDialog(null, "修改成功", "修改成功", JOptionPane.NO_OPTION);
                    fr.setVisible(false);
                    BookManagement bookManagement = new BookManagement();
                    bookManagement.init(usertype,oper);
                }
            });

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(275, 250, 70, 25);
            fr.add(Button_Cancle);
            Button_Cancle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    BookManagement bookManagement = new BookManagement();
                    bookManagement.init(usertype,oper);
                }
            });

            fr.setSize(700, 400);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
