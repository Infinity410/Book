package com.example.manage.circulation;

import com.example.manage.book.Book;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch4_scroll.BEScrollPaneUI;
import org.jb2011.lnf.beautyeye.ch5_table.BETableUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Query_GUI {
    private List<Circulation> circulations;
    private static final File file = new File("Circulation.txt");
    private String[] columnName = new String[]{"流水号","用户名","书号","日期","借还书类型","操作人"};
    private String[][] columnBook;
    private String bookno;
    public Query_GUI(){
        circulations = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String str = null;
            //循环判断
            while ((str = br.readLine()) != null) {
                String[] data = str.split("=>");
                Circulation circulation1 = new Circulation();
                int CirSeri = Integer.parseInt(data[0]);
                circulation1.setSerialNo(CirSeri);
                circulation1.setId(data[1]);
                circulation1.setNo(data[2]);
                circulation1.setDatel(data[3]);
                int CirType = Integer.parseInt(data[4]);
                circulation1.setType(CirType);
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
        init();
    }
    public void init(){
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("借阅信息查询界面");
            fr.setSize(610, 450);
            fr.pack();
            fr.setLayout(null);

            JLabel newNo = new JLabel("用户名:");
            newNo.setBounds(180, 20, 100, 25);
            fr.add(newNo);

            JTextField newBookNo = new JTextField();
            newBookNo.setBounds(250, 20, 150, 25);
            fr.add(newBookNo);

            JButton Button_Comfirm = new JButton("确定并返回");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(220, 370, 140, 25);
            fr.add(Button_Comfirm);

            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean flag = false;
                    bookno = newBookNo.getText().toString();
                    int i;
                    for(i = 0; i < circulations.size(); i++){
                        if(bookno.equals(circulations.get(i).getId())){
                            System.out.println(circulations.get(i).getId());
//                            JOptionPane.showMessageDialog(null, "查找成功", "查找成功", JOptionPane.NO_OPTION);
//                            fr.setVisible(false);
                            flag=true;
                            break;
                        }
                    }
                    if(!flag){
                        JOptionPane.showMessageDialog(null, "没找到这个人", "没找到这个人", JOptionPane.WARNING_MESSAGE);
                        newBookNo.setText("");
                    }else{
                        columnBook = new String[][]{{String.valueOf(circulations.get(i).getSerialNo())}};
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

            fr.setSize(610, 450);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
