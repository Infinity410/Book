package com.example.manage.circulation;

import com.example.manage.book.Book;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Lend_GUI {
    private List<Circulation> circulations;
    private List<Book> books;
    private String bookno;
    private int usertype;
    private static final File file = new File("Circulation.txt");
    private static final File file2 = new File("Book.txt");
    private String operator;
    public Lend_GUI(int usertype1,String op1){
        init();
        setOperator(op1);
        setUsertype(usertype1);
        circulations = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String str = null;
            //循环判断
            while ((str = br.readLine()) != null) {
                String[] data = str.split("=>");
                Circulation circulation1 = new Circulation();
                int SerialNo = Integer.parseInt(data[0]);
                circulation1.setSerialNo(SerialNo);
                circulation1.setId(data[1]);
                circulation1.setNo(data[2]);
                circulation1.setDatel(data[3]);
                int type_cir = Integer.parseInt(data[4]);
                circulation1.setType(type_cir);
                circulation1.setOperator(data[5]);
                circulations.add(circulation1);
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    void init() {
        Calendar now = Calendar.getInstance();
        System.out.println("年: " + now.get(Calendar.YEAR));
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("借书界面");
            fr.setSize(500, 400);
            fr.pack();
            fr.setLayout(null);

            JLabel newNo = new JLabel("书号:");
            newNo.setBounds(150, 50, 100, 25);
            fr.add(newNo);

            JTextField newBookNo = new JTextField();
            newBookNo.setBounds(240, 50, 150, 25);
            fr.add(newBookNo);

            JLabel newName = new JLabel("用户名:");
            newName.setBounds(150, 100, 100, 25);
            fr.add(newName);

            JTextField newBookName = new JTextField();
            newBookName.setBounds(240, 100, 150, 25);
            fr.add(newBookName);

            JLabel newCount = new JLabel("日期:");
            newCount.setBounds(150, 150, 100, 25);
            fr.add(newCount);

            JTextField newBookCount = new JTextField();
            newBookCount.setBounds(240, 150, 150, 25);
            newBookCount.setText(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1) +"-"+now.get(Calendar.DAY_OF_MONTH));
            fr.add(newBookCount);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 300, 70, 25);
            fr.add(Button_Comfirm);
            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    books = new ArrayList<>();
                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new FileReader(file2));
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

                    } catch (FileNotFoundException e1) {
                        System.out.println("文件读入异常：" + e1.getMessage());
                    } catch (IOException e1) {
                        System.out.println("文件读入异常：" + e1.getMessage());
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e1) {
                            System.out.println("关闭BufferedReader输入流异常：" + e1.getMessage());
                        }
                    }
                    for (Iterator<Book> iterator = books.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                    bookno = newBookNo.getText().toString();
                    System.out.println(bookno);
                    int i;
                    for(i = 0; i < books.size(); i++){
                        if(bookno.equals(books.get(i).getNo())){
                            System.out.println(books.get(i).getNo());
                            if(books.get(i).getCount()!=0) {
                                books.get(i).setCount(books.get(i).getCount() - 1);
                                JOptionPane.showMessageDialog(null, "借书成功", "借书成功", JOptionPane.NO_OPTION);
                            }
                            break;
                        }
                    }
                    for (Iterator<Book> iterator = books.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                    BufferedWriter bw2 = null;
                    try{
                        bw2 = new BufferedWriter(new FileWriter(file2));
                        for (int j = 0; j<books.size();j++ ){
                            bw2.write(books.get(j).getNo()+"=>"+books.get(j).getName()+"=>"+books.get(j).getAuthor()+"=>"+books.get(j).getPress()+"=>"+books.get(j).getCount());
                            bw2.newLine();
                            bw2.flush();
                        }
                    }catch (IOException e1) {
                        System.out.println("修改失败："+e1.getMessage());
                    }finally{
                        try {
                            bw2.close();
                        } catch (IOException e1) {
                            System.out.println("关闭BufferedWriter输出流异常："+e1.getMessage());
                        }
                    }
                    if(i == books.size()){
                        JOptionPane.showMessageDialog(null, "没找到这本书", "没找到这本书", JOptionPane.WARNING_MESSAGE);
                        newBookNo.setText("");
                    }else{
                        int num = circulations.size() + 1;
                        System.out.println(num);
                        Circulation circulation2 = new Circulation();
                        circulation2.setSerialNo(num);
                        circulation2.setId(newBookName.getText().toString());
                        circulation2.setNo(newBookNo.getText().toString());
                        circulation2.setDatel(newBookCount.getText().toString());
                        circulation2.setType(1);
                        circulation2.setOperator(operator);
                        BufferedWriter bw = null;
                        if(circulation2 != null){
                            try{
                                bw = new BufferedWriter(new FileWriter(file,true));
                                bw.write(circulation2.getSerialNo()+"=>"+circulation2.getId()+"=>"+circulation2.getNo()+"=>"+circulation2.getDatel()+"=>"+circulation2.getType()+"=>"+circulation2.getOperator());
                                bw.newLine();
                                bw.flush();
                            }catch (IOException e2) {
                                System.out.println("添加失败："+e2.getMessage());
                            }finally{
                                try {
                                    bw.close();
                                    fr.dispose();
                                    CirculationManagement circulationManagement = new CirculationManagement(usertype,operator);
                                    circulationManagement.init(usertype,operator);
                                } catch (IOException e2) {
                                    System.out.println("关闭BufferedWriter输出流异常："+e2.getMessage());
                                }
                            }
                        }else{
                            //注册失败信息
                            System.out.println("添加失败：借书信息不能为空");
                        }
                    }
                }
            });

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(275, 300, 70, 25);
            fr.add(Button_Cancle);
            Button_Cancle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    CirculationManagement circulationManagement = new CirculationManagement(usertype,operator);
                    circulationManagement.init(usertype,operator);
                }
            });

            fr.setSize(500, 400);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
