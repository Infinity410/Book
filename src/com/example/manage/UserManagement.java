package com.example.manage;

import com.example.manage.book.BookManagement;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch6_textcoms.BETextAreaUI;
import com.example.manage.circulation.CirculationManagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserManagement extends JFrame {

    private int count = 0;
    private String ID;
    private String password1;
    private Object UserDaoImlp;
    private String operator;
    private List<User> users;
    private static final File file = new File("User.txt");
    private int usertype;

    public UserManagement() {
        //init();
        users = new ArrayList<>();
//        JFrame frame = new JFrame("登录界面");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setSize(500,300);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        textField1 = new JTextField();
//        passwordField1 = new JPasswordField();

//
//        User user1 = new User();
//        user1.setID("abc");
//        user1.setPassword("123456");
//        user1.setType(1);
//        user1.setName("李四");
//        user1.setUnit("网络中心");
//        user1.setTelephone("2284036");
//        user1.setCount(10);
//
//        userDaoImlp.registerUser(user1);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String str = null;
            //循环判断
            while ((str = br.readLine()) != null) {
                String[] data = str.split("=>");
                User user1 = new User();
                user1.setID(data[0]);
                user1.setPassword(data[1]);
                int type = Integer.parseInt(data[2]);
                user1.setType(type);
                user1.setName(data[3]);
                user1.setUnit(data[4]);
                user1.setTelephone(data[5]);
                int CountBook = Integer.parseInt(data[6]);
                user1.setCount(CountBook);
                users.add(user1);
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
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }

    }

    public UserManagement(int u1,String operator1){
        users = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String str = null;
            //循环判断
            while ((str = br.readLine()) != null) {
                String[] data = str.split("=>");
                User user1 = new User();
                user1.setID(data[0]);
                user1.setPassword(data[1]);
                int type = Integer.parseInt(data[2]);
                user1.setType(type);
                user1.setName(data[3]);
                user1.setUnit(data[4]);
                user1.setTelephone(data[5]);
                int CountBook = Integer.parseInt(data[6]);
                user1.setCount(CountBook);
                users.add(user1);
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
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
        setOperator(operator);
        setUsertype(u1);
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public void init() {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame frame = new JFrame("登录界面");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            frame.setSize(500, 200);
            frame.pack();
            frame.setLayout(null);

            JLabel title = new JLabel("欢迎使用图书管理系统");
            Font font = new Font("宋体", Font.BOLD, 25);
            title.setFont(font);
            title.setBounds(125, 10, 500, 25);
            frame.add(title);

            JLabel nameStr = new JLabel("账号:");
            nameStr.setBounds(150, 50, 100, 25);
            frame.add(nameStr);

            JLabel passwordStr = new JLabel("密码:");
            passwordStr.setBounds(150, 100, 100, 25);
            frame.add(passwordStr);

            JTextField userID = new JTextField();
            userID.setBounds(200, 50, 150, 25);
            frame.add(userID);

            JPasswordField password = new JPasswordField();
            password.setBounds(200, 100, 150, 25);
            frame.add(password);

            JButton Button_Login = new JButton("登录");
            Button_Login.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Login.setForeground(Color.white);
            Button_Login.setBounds(175, 150, 70, 25);
            frame.add(Button_Login);

            JButton Button_Sign = new JButton("注册");
            Button_Sign.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Sign.setForeground(Color.white);
            Button_Sign.setBounds(275, 150, 70, 25);
            frame.add(Button_Sign);

            frame.setSize(500, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);


            UserDao userDaoImlp = new UserDaoImlp();

            Button_Login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ID = userID.getText();
                    System.out.println(ID);
                    setOperator(ID);
                    password1 = new String(password.getPassword());
                    System.out.println(password1);
                    boolean flag = userDaoImlp.isLoginUser(ID, password1);

                    if (flag == false) {

                        count++;
                        if (count != 3) {
                            if (ID.isEmpty() == true || password1.isEmpty() == true) {
                                JOptionPane.showMessageDialog(null, "用户名和密码不能为空", "用户名和密码不能为空", JOptionPane.WARNING_MESSAGE);
                            } else {
                                //弹出账号或密码错误的窗口
                                JOptionPane.showMessageDialog(null, "账号或密码错误", "账号或密码错误", JOptionPane.WARNING_MESSAGE);
                                //清除密码框中的信息
                                password.setText("");
                                //清除账号框中的信息
                                userID.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "账号或密码错误超过上限", "账号或密码错误超过上限", JOptionPane.WARNING_MESSAGE);
                            System.exit(0);
                        }

                    } else {
                        //弹出登录成功的窗口
                        JOptionPane.showMessageDialog(null, "登陆成功", "登陆成功", JOptionPane.NO_OPTION);
                        //点击确定后会跳转到主窗口
                        frame.setVisible(false);
                        usertype = userDaoImlp.getType();
                        menu(usertype,operator);
                    }
                }
            });
            Button_Sign.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    UserDaoImlp = new UserDaoImlp();
//                    User u1 = new User();
//                    userDaoImlp.registerUser(u1);
                    frame.setVisible(false);
                    Sign_Up_GUI sign_up_gui = new Sign_Up_GUI();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void menu(int usertype1,String op1) {
        UIManager.put("RootPane.setupButtonVisible", false);
        setOperator(op1);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame frame = new JFrame("菜单界面");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            frame.setSize(500, 200);
            frame.pack();
            frame.setLayout(null);

            JLabel title = new JLabel("欢迎使用图书管理系统");
            Font font = new Font("宋体", Font.BOLD, 25);
            title.setFont(font);
            title.setBounds(125, 10, 500, 25);
            frame.add(title);

            JButton Button_UserManage = new JButton("用户管理");
            Button_UserManage.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_UserManage.setForeground(Color.white);
            Button_UserManage.setBounds(170, 50, 150, 25);
            frame.add(Button_UserManage);
            Button_UserManage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manage_gui(usertype1);
                }
            });

            JButton Button_BookManage = new JButton("图书管理");
            Button_BookManage.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_BookManage.setForeground(Color.white);
            Button_BookManage.setBounds(170, 100, 150, 25);
            if(usertype1 == 3){
                Button_BookManage.setVisible(false);
            }else {
                frame.add(Button_BookManage);
            }

            JButton Button_BookLiuTongManage = new JButton("图书流通管理");
            Button_BookLiuTongManage.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_BookLiuTongManage.setForeground(Color.white);
            Button_BookLiuTongManage.setBounds(170, 150, 150, 25);
            if(usertype1==2){
                frame.add(Button_BookLiuTongManage);
            }else{
                Button_BookLiuTongManage.setVisible(false);
            }

            JButton Button_MenuExit = new JButton("退出");
            Button_MenuExit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_MenuExit.setForeground(Color.white);
            Button_MenuExit.setBounds(170, 200, 150, 25);
            frame.add(Button_MenuExit);

            frame.setSize(500, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            Button_MenuExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    init();
                }
            });
            Button_BookManage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    BookManagement bookManagement = new BookManagement();
                    bookManagement.init(usertype1,operator);
                }
            });
            Button_BookLiuTongManage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    CirculationManagement circulationManagement = new CirculationManagement(usertype1,operator);
                    circulationManagement.init(usertype1,operator);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void manage_gui(int usertype_manage){

    }
    public void input(){

    }
    public void update(){

    }
    public void delete(){

    }
    public void query(){

    }
    public void changePassword(){

    }
}

