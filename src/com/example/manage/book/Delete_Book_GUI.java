package com.example.manage.book;

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

public class Delete_Book_GUI {
    private List<Book> books;
    private String oper;
    private String bookno;
    private static final File file = new File("Book.txt");
    private int usertype;
    public Delete_Book_GUI(int u1,String oper1){
        usertype = u1;
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
    void init() {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("图书删除界面");
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
                    boolean flag = false;
                    bookno = newBookNo.getText().toString();
//                    System.out.println(bookno);
                    int i;
                    for(i = 0; i < books.size(); i++){
                        if(bookno.equals(books.get(i).getNo())){
                            System.out.println(books.get(i).getNo());
                            JOptionPane.showMessageDialog(null, "删除成功", "删除成功", JOptionPane.NO_OPTION);
                            fr.setVisible(false);
                            BookManagement bookManagement = new BookManagement();
                            bookManagement.init(usertype,oper);
                            flag=true;
                            break;
                        }
                    }
                    if(!flag){
                        JOptionPane.showMessageDialog(null, "没找到这本书", "没找到这本书", JOptionPane.WARNING_MESSAGE);
                        newBookNo.setText("");
                    }else{
                        books.remove(i);
                        BufferedWriter bw = null;
                        try{
                            bw = new BufferedWriter(new FileWriter(file));
                            for (int j = 0; j<books.size();j++ ){
                                bw.write(books.get(j).getNo()+"=>"+books.get(j).getName()+"=>"+books.get(j).getAuthor()+"=>"+books.get(j).getPress()+"=>"+books.get(j).getCount());
                                bw.newLine();
                                bw.flush();
                            }
                        }catch (IOException e1) {
                            System.out.println("删除失败："+e1.getMessage());
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
}
