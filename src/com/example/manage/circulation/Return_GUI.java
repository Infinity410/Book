package com.example.manage.circulation;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Return_GUI {
    private List<Circulation> circulations;
    private String bookno;
    private static final File file = new File("Circulation.txt");
    public Return_GUI(){
        init();
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
    void init() {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("借书界面");
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
                    for(i = 0; i < circulations.size(); i++){
                        if(bookno.equals(circulations.get(i).getNo())){
                            System.out.println(circulations.get(i).getNo());
                            JOptionPane.showMessageDialog(null, "还书成功", "还书成功", JOptionPane.NO_OPTION);
                            fr.setVisible(false);
                            break;
                        }
                    }
                    if(i == circulations.size()){
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

            fr.setSize(500, 200);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
