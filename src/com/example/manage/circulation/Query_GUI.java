package com.example.manage.circulation;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Query_GUI {
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane tableScrollPanel;
    String[] columnName = new String[]{"流水号","用户名","书号","日期","借还书类型","操作人"};
    public Query_GUI(){
        init();
    }
    public void init(){
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("借阅信息查询界面");
            fr.setSize(600, 400);
            fr.pack();
            fr.setLayout(new BorderLayout());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
