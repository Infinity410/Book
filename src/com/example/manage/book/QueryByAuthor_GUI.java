package com.example.manage.book;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch4_scroll.BEScrollPaneUI;
import org.jb2011.lnf.beautyeye.ch5_table.BETableUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryByAuthor_GUI {
    private List<Book> books;
    private String bookname;
    private static final File file = new File("Book.txt");
    public QueryByAuthor_GUI(){
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
    void init(){
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("图书查找界面（通过作者）");
            fr.setSize(610, 450);
            fr.pack();
            fr.setLayout(null);

            JLabel newName = new JLabel("作者名:");
            newName.setBounds(180, 20, 100, 25);
            fr.add(newName);

            JTextField newBookame = new JTextField();
            newBookame.setBounds(250, 20, 150, 25);
            fr.add(newBookame);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(220, 370, 70, 25);
            fr.add(Button_Comfirm);
            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean flag = false;
                    bookname = newBookame.getText().toString();
//                    System.out.println(bookno);
                    String[] columnName = new String[]{"书号","书名","作者","出版社","藏书量"};

                    int i;
                    Pattern pattern = Pattern.compile(bookname, Pattern.CASE_INSENSITIVE);
                    List<Book> results = new ArrayList<>();
                    for(i = 0; i < books.size(); i++){
                        Matcher matcher = pattern.matcher((books.get(i)).getAuthor());
                        if(matcher.find()){
                            results.add(books.get(i));
                            flag=true;
                        }
//                        if(newBookame.equals(books.get(i).getName())){
//                            System.out.println(books.get(i).getName());
//                            JOptionPane.showMessageDialog(null, "查找成功", "查找成功", JOptionPane.NO_OPTION);
//                            fr.setVisible(false);
//                            flag=true;
//                            break;
//                        }
                    }
                    System.out.println();
                    for (Iterator<Book> iterator = results.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                    if(!flag){
                        JOptionPane.showMessageDialog(null, "没找到这本书", "没找到这本书", JOptionPane.WARNING_MESSAGE);
                        newBookame.setText("");
                    }else{
                        String[][] columnBook = new String[results.size()][5];
                        for (int a=0; a<results.size(); a++){
                            columnBook[a][0]=results.get(a).getNo();
                            columnBook[a][1]=results.get(a).getName();
                            columnBook[a][2]=results.get(a).getAuthor();
                            columnBook[a][3]=results.get(a).getPress();
                            columnBook[a][4]=String.valueOf(results.get(a).getCount());
                        }
                        JTable table = new JTable(columnBook, columnName);
                        table.setUI(new BETableUI());
                        table.getColumnModel().getColumn(0).setPreferredWidth(40);
                        table.getColumnModel().getColumn(1).setPreferredWidth(120);
                        table.setEnabled(false);
                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setUI(new BEScrollPaneUI());
                        scrollPane.setBounds(10, 60, 570, 300);
                        fr.add(scrollPane);
                    }
                }
            });

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(320, 370, 70, 25);
            fr.add(Button_Cancle);

            fr.setSize(610, 450);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
