package com.example.manage;

import com.example.manage.book.Book;
import com.example.manage.book.BookManagement;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch4_scroll.BEScrollPaneUI;
import org.jb2011.lnf.beautyeye.ch5_table.BETableUI;
import org.jb2011.lnf.beautyeye.ch6_textcoms.BETextAreaUI;
import com.example.manage.circulation.CirculationManagement;

import javax.naming.Name;
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

public class UserManagement extends JFrame {

    private int count = 0;
    private String ID;
    private String password1;
    private Object UserDaoImlp;
    private String operator;
    private List<User> users;
    private static final File file = new File("User.txt");
    private ImageIcon imageIcon = new ImageIcon("icon_book.png");
    private int usertype;
    private String userNo;

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

    public UserManagement(int u1, String operator1) {
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
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
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
            frame.setIconImage(imageIcon.getImage());
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
                        menu(usertype, operator);
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

    public void menu(int usertype1, String op1) {
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
            if (usertype1 == 3) {
                Button_BookManage.setVisible(false);
            } else {
                frame.add(Button_BookManage);
            }

            JButton Button_BookLiuTongManage = new JButton("图书流通管理");
            Button_BookLiuTongManage.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_BookLiuTongManage.setForeground(Color.white);
            Button_BookLiuTongManage.setBounds(170, 150, 150, 25);
            if (usertype1 == 2) {
                frame.add(Button_BookLiuTongManage);
            } else {
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
                    bookManagement.init(usertype1, operator);
                }
            });
            Button_BookLiuTongManage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    CirculationManagement circulationManagement = new CirculationManagement(usertype1, operator);
                    circulationManagement.init(usertype1, operator);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void manage_gui(int usertype_manage) {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("用户管理");
            fr.setSize(500, 400);
            fr.pack();
            fr.setLayout(null);

            JLabel title = new JLabel("用户管理子系统");
            Font font = new Font("宋体", Font.BOLD, 25);
            title.setFont(font);
            title.setBounds(155, 10, 500, 25);
            fr.add(title);

            JButton Button_Input = new JButton("用户信息录入");
            Button_Input.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Input.setForeground(Color.white);
            Button_Input.setBounds(170, 50, 150, 25);
            fr.add(Button_Input);
            if(usertype!=3){
                Button_Input.setEnabled(false);
            }

            Button_Input.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    input();
                }
            });

            JButton Button_Update = new JButton("用户信息修改");
            Button_Update.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Update.setForeground(Color.white);
            Button_Update.setBounds(170, 100, 150, 25);
            fr.add(Button_Update);
            if(usertype!=3){
                Button_Update.setEnabled(false);
            }
            Button_Update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    update();
                }
            });

            JButton Button_Delete = new JButton("用户信息删除");
            Button_Delete.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Delete.setForeground(Color.white);
            Button_Delete.setBounds(170, 150, 150, 25);
            fr.add(Button_Delete);
            if (usertype!=3){
                Button_Delete.setEnabled(false);
            }
            Button_Delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    delete();
                }
            });

            JButton Button_Search = new JButton("用户信息查询");
            Button_Search.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Search.setForeground(Color.white);
            Button_Search.setBounds(170, 200, 150, 25);
            fr.add(Button_Search);
            if(usertype!=3){
                Button_Search.setEnabled(false);
            }
            Button_Search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    query();
                }
            });

            JButton Button_ChangePassword = new JButton("用户密码修改");
            Button_ChangePassword.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_ChangePassword.setForeground(Color.white);
            Button_ChangePassword.setBounds(170, 250, 150, 25);
            fr.add(Button_ChangePassword);
            Button_ChangePassword.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    int i = 0,j=0;
                    for(i=0;i<users.size();i++){
                        if(users.get(i).getID().equals(ID)){
                            j = 1;
                            break;
                        }
                    }
                    if(j==1)
                    changePassword(i);
                }
            });

            JButton Button_MenuExit = new JButton("返回主菜单");
            Button_MenuExit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_MenuExit.setForeground(Color.white);
            Button_MenuExit.setBounds(170, 300, 150, 25);
            fr.add(Button_MenuExit);

            fr.setSize(500, 400);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

            Button_MenuExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    UserManagement userManagement = new UserManagement();
                    userManagement.menu(usertype, ID);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void input() {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("用户信息录入");
            fr.setSize(500, 500);
            fr.pack();
            fr.setLayout(null);

            JLabel newID = new JLabel("新账号:");
            newID.setBounds(150, 50, 100, 25);
            fr.add(newID);

            JLabel newPassword = new JLabel("密码:");
            newPassword.setBounds(150, 100, 100, 25);
            fr.add(newPassword);

            JLabel newPassword1 = new JLabel("确认密码:");
            newPassword1.setBounds(150, 150, 100, 25);
            fr.add(newPassword1);

            JLabel newName = new JLabel("姓名:");
            newName.setBounds(150, 200, 100, 25);
            fr.add(newName);

            JLabel newUnit = new JLabel("单位:");
            newUnit.setBounds(150, 250, 100, 25);
            fr.add(newUnit);

            JLabel newType = new JLabel("类型:");
            newType.setBounds(150, 300, 100, 25);
            fr.add(newType);

            JLabel newTelephone = new JLabel("电话:");
            newTelephone.setBounds(150, 350, 100, 25);
            fr.add(newTelephone);

            JLabel newCount = new JLabel("可借书数:");
            newCount.setBounds(150, 400, 100, 25);
            fr.add(newCount);

            JTextField newUserID = new JTextField();
            newUserID.setBounds(240, 50, 150, 25);
            fr.add(newUserID);

            JTextField newUserPassword = new JTextField();
            newUserPassword.setBounds(240, 100, 150, 25);
            fr.add(newUserPassword);

            JTextField newUserPassword1 = new JTextField();
            newUserPassword1.setBounds(240, 150, 150, 25);
            fr.add(newUserPassword1);

            JTextField newUserName = new JTextField();
            newUserName.setBounds(240, 200, 150, 25);
            fr.add(newUserName);

            JTextField newUserUnit = new JTextField();
            newUserUnit.setBounds(240, 250, 150, 25);
            fr.add(newUserUnit);

            JTextField newUserType = new JTextField();
            newUserType.setBounds(240, 300, 150, 25);
            fr.add(newUserType);

            JTextField newUserTelephone = new JTextField();
            newUserTelephone.setBounds(240, 350, 150, 25);
            fr.add(newUserTelephone);

            JTextField newUserCount = new JTextField();
            newUserCount.setBounds(240, 400, 150, 25);
            fr.add(newUserCount);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 450, 70, 25);
            fr.add(Button_Comfirm);

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(275, 450, 70, 25);
            fr.add(Button_Cancle);

            fr.setSize(500, 600);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

            UserDao userDaoImlp = new UserDaoImlp();

            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s1 = newUserPassword.getText();
                    boolean flag1 = s1.equals(newUserPassword1.getText());
                    if (flag1 == true) {
                        int a = Integer.parseInt(newUserType.getText());
                        int b = Integer.parseInt(newUserCount.getText());
                        User nUser = new User();
                        nUser.User(newUserID.getText(), newUserPassword.getText(), newUserName.getText(), newUserUnit.getText(), a, newUserTelephone.getText(), b);
                        UserDaoImlp zxc = new UserDaoImlp();
                        zxc.registerUser(nUser);
                        JOptionPane.showMessageDialog(null, "注册成功", "注册成功", JOptionPane.NO_OPTION);
                        fr.dispose();
                        UserManagement management = new UserManagement();
                    } else {
                        JOptionPane.showMessageDialog(null, "两次密码不一致", "密码不一致", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            Button_Cancle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    UserManagement management = new UserManagement();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("用户修改界面");
            fr.setSize(500, 200);
            fr.pack();
            fr.setLayout(null);

            JLabel newNo = new JLabel("用户名:");
            newNo.setBounds(130, 50, 100, 25);
            fr.add(newNo);

            JTextField newUserNo = new JTextField();
            newUserNo.setBounds(200, 50, 150, 25);
            fr.add(newUserNo);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 100, 70, 25);
            fr.add(Button_Comfirm);
            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userNo = newUserNo.getText().toString();
                    System.out.println(userNo);
                    int i;
                    for (i = 0; i < users.size(); i++) {
                        if (userNo.equals(users.get(i).getID())) {
                            System.out.println(users.get(i).getID());
                            JOptionPane.showMessageDialog(null, "查找成功", "查找成功", JOptionPane.NO_OPTION);
                            fr.setVisible(false);
                            detail(i);
                            break;
                        }
                    }
                    if (i == users.size()) {
                        JOptionPane.showMessageDialog(null, "没找到该用户", "没找到该用户", JOptionPane.WARNING_MESSAGE);
                        newUserNo.setText("");
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
                    init();
                }
            });

            fr.setSize(500, 200);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detail(int i) {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("用户信息修改界面");
            fr.setSize(700, 600);
            fr.pack();
            fr.setLayout(null);

            Object[] columnName = {"用户名", "名字", "密码", "学院","类型", "电话", "可借书数"};
            Object[][] rowData = {{users.get(i).getID(), users.get(i).getName(), users.get(i).getPassword(), users.get(i).getUnit(), users.get(i).getType(), users.get(i).getTelephone(), users.get(i).getCount()}};
            JTable table = new JTable(rowData, columnName);
            table.setBounds(20, 10, 700, 20);


            fr.add(table);

            JLabel newName = new JLabel("用户名:");
            newName.setBounds(150, 50, 100, 25);
            fr.add(newName);

            JLabel newAuthor = new JLabel("名字:");
            newAuthor.setBounds(150, 100, 100, 25);
            fr.add(newAuthor);

            JLabel newPress = new JLabel("密码:");
            newPress.setBounds(150, 150, 100, 25);
            fr.add(newPress);

            JLabel newType = new JLabel("类型:");
            newType.setBounds(150, 200, 100, 25);
            fr.add(newType);

            JLabel newTelephone = new JLabel("电话:");
            newTelephone.setBounds(150, 250, 100, 25);
            fr.add(newTelephone);

            JLabel newCount = new JLabel("可借书数:");
            newCount.setBounds(150, 300, 100, 25);
            fr.add(newCount);

            JTextField newUserID = new JTextField();
            newUserID.setBounds(240, 50, 150, 25);
            fr.add(newUserID);

            JTextField newUserName = new JTextField();
            newUserName.setBounds(240, 100, 150, 25);
            fr.add(newUserName);

            JTextField newUserPassword = new JTextField();
            newUserPassword.setBounds(240, 150, 150, 25);
            fr.add(newUserPassword);

            JTextField newUserType = new JTextField();
            newUserType.setBounds(240, 200, 150, 25);
            fr.add(newUserType);

            JTextField newUserTelephone = new JTextField();
            newUserTelephone.setBounds(240, 250, 150, 25);
            fr.add(newUserTelephone);

            JTextField newUserCount = new JTextField();
            newUserCount.setBounds(240, 300, 150, 25);
            fr.add(newUserCount);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 355, 70, 25);
            fr.add(Button_Comfirm);

            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    users.get(i).setID(newUserID.getText());
                    users.get(i).setName(newUserName.getText());
                    users.get(i).setPassword(newUserPassword.getText());
                    users.get(i).setTelephone(newUserTelephone.getText());
                    int newtype = Integer.parseInt(newUserType.getText());
                    users.get(i).setType(newtype);
                    int count = Integer.parseInt(newUserCount.getText());
                    users.get(i).setCount(count);
                    for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                    BufferedWriter bw = null;
                    try{
                        bw = new BufferedWriter(new FileWriter(file));
                        for (int j = 0; j<users.size();j++ ){
                            bw.write(users.get(j).getID()+"=>"+users.get(j).getPassword()+"=>"+users.get(j).getType()+"=>"+users.get(j).getName()+"=>"+users.get(j).getUnit()+"=>"+users.get(j).getTelephone()+"=>"+users.get(j).getCount());
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
                    for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                    JOptionPane.showMessageDialog(null, "修改成功", "修改成功", JOptionPane.NO_OPTION);
                    fr.dispose();
                    UserManagement userManagement = new UserManagement();
                }
            });

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(275, 355, 70, 25);
            fr.add(Button_Cancle);
            Button_Cancle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                }
            });


            fr.setSize(700, 600);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("用户删除界面");
            fr.setSize(500, 200);
            fr.pack();
            fr.setLayout(null);

            JLabel newNo = new JLabel("用户名:");
            newNo.setBounds(130, 50, 100, 25);
            fr.add(newNo);

            JTextField newUserNo = new JTextField();
            newUserNo.setBounds(200, 50, 150, 25);
            fr.add(newUserNo);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 100, 70, 25);
            fr.add(Button_Comfirm);
            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean flag = false;
                    userNo = newUserNo.getText().toString();
//                    System.out.println(bookno);
                    int i;
                    for (i = 0; i < users.size(); i++) {
                        if (userNo.equals(users.get(i).getID())) {
                            System.out.println(users.get(i).getID());
                            JOptionPane.showMessageDialog(null, "删除成功", "删除成功", JOptionPane.NO_OPTION);
                            fr.setVisible(false);
                            UserManagement userManagement = new UserManagement();
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        JOptionPane.showMessageDialog(null, "没找到该用户", "没找到该用户", JOptionPane.WARNING_MESSAGE);
                        newUserNo.setText("");
                    } else {
                        users.remove(i);
                        BufferedWriter bw = null;
                        try{
                            bw = new BufferedWriter(new FileWriter(file));
                            for (int j = 0; j<users.size();j++ ){
                                bw.write(users.get(j).getID()+"=>"+users.get(j).getPassword()+"=>"+users.get(j).getType()+"=>"+users.get(j).getName()+"=>"+users.get(j).getUnit()+"=>"+users.get(j).getTelephone()+"=>"+users.get(j).getCount());
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
                        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
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
                    UserManagement userManagement = new UserManagement();
                }
            });

            fr.setSize(500, 200);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void query() {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame frame = new JFrame("查询界面");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            frame.setSize(500, 200);
            frame.pack();
            frame.setLayout(null);

            JButton Button_BookSearchNo = new JButton("按ID查询");
            Button_BookSearchNo.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_BookSearchNo.setForeground(Color.white);
            Button_BookSearchNo.setBounds(170, 50, 150, 25);
            frame.add(Button_BookSearchNo);
            Button_BookSearchNo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    queryByID();
                    frame.dispose();
                }
            });

            JButton Button_BookSearchName = new JButton("按姓名查询");
            Button_BookSearchName.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_BookSearchName.setForeground(Color.white);
            Button_BookSearchName.setBounds(170, 100, 150, 25);
            frame.add(Button_BookSearchName);
            Button_BookSearchName.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    queryByName();
                    frame.dispose();
                }
            });

            JButton Button_SearchExit = new JButton("退出");
            Button_SearchExit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_SearchExit.setForeground(Color.white);
            Button_SearchExit.setBounds(170, 200, 150, 25);
            frame.add(Button_SearchExit);
            Button_SearchExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    UserManagement userManagement = new UserManagement();
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

    public void changePassword(int i) {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("用户密码修改界面");
            fr.setSize(700, 400);
            fr.pack();
            fr.setLayout(null);

            Object[] columnName = {"用户名", "名字", "密码", "学院", "电话", "可借书数"};
            Object[][] rowData = {{users.get(i).getID(), users.get(i).getName(), users.get(i).getPassword(), users.get(i).getUnit(), users.get(i).getTelephone(), users.get(i).getCount()}};
            JTable table = new JTable(rowData, columnName);
            table.setBounds(20, 10, 700, 20);


            fr.add(table);

            JLabel newPress = new JLabel("密码:");
            newPress.setBounds(150, 150, 100, 25);
            fr.add(newPress);

            JTextField newUserPassword = new JTextField();
            newUserPassword.setBounds(240, 150, 150, 25);
            fr.add(newUserPassword);

            JButton Button_Comfirm = new JButton("确定");
            Button_Comfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
            Button_Comfirm.setForeground(Color.white);
            Button_Comfirm.setBounds(175, 300, 70, 25);
            fr.add(Button_Comfirm);

            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    users.get(i).setPassword(newUserPassword.getText());
                    for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                    BufferedWriter bw = null;
                    try{
                        bw = new BufferedWriter(new FileWriter(file));
                        for (int j = 0; j<users.size();j++ ){
                            bw.write(users.get(j).getID()+"=>"+users.get(j).getPassword()+"=>"+users.get(j).getType()+"=>"+users.get(j).getName()+"=>"+users.get(j).getUnit()+"=>"+users.get(j).getTelephone()+"=>"+users.get(j).getCount());
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
                    for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                    JOptionPane.showMessageDialog(null, "修改成功", "修改成功", JOptionPane.NO_OPTION);
                    fr.setVisible(false);
                    UserManagement userManagement = new UserManagement();
                }
            });

            JButton Button_Cancle = new JButton("取消");
            Button_Cancle.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
            Button_Cancle.setForeground(Color.white);
            Button_Cancle.setBounds(275, 300, 70, 25);
            fr.add(Button_Cancle);
            Button_Comfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    init();
                }
            });

            fr.setSize(700, 400);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void queryByName() {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("用户查找界面（通过Name）");
            fr.setSize(610, 450);
            fr.pack();
            fr.setLayout(null);

            JLabel newName = new JLabel("Name:");
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
                    userNo = newBookame.getText().toString();
//                    System.out.println(bookno);
                    String[] columnName = new String[]{"ID","密码","姓名","学院","类型","电话","可借书数"};

                    int i;
                    Pattern pattern = Pattern.compile(userNo, Pattern.CASE_INSENSITIVE);
                    List<User> results = new ArrayList<>();
                    for(i = 0; i < users.size(); i++){
                        Matcher matcher = pattern.matcher((users.get(i)).getName());
                        if(matcher.find()){
                            results.add(users.get(i));
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
                    for (Iterator<User> iterator = results.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                    if(!flag){
                        JOptionPane.showMessageDialog(null, "没找到该用户", "没找到该用户", JOptionPane.WARNING_MESSAGE);
                        newBookame.setText("");
                    }else{
                        String[][] columnBook = new String[results.size()][7];
                        for (int a=0; a<results.size(); a++){
                            columnBook[a][0]=results.get(a).getID();
                            columnBook[a][2]=results.get(a).getName();
                            columnBook[a][1]=results.get(a).getPassword();
                            columnBook[a][3]=results.get(a).getTelephone();
                            columnBook[a][5]=results.get(a).getUnit();
                            columnBook[a][6]=String.valueOf(results.get(a).getCount());
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
            Button_Cancle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    UserManagement userManagement = new UserManagement();
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

    private void queryByID() {
        UIManager.put("RootPane.setupButtonVisible", false);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            JFrame fr = new JFrame("用户查找界面（通过ID）");
            fr.setSize(610, 450);
            fr.pack();
            fr.setLayout(null);

            JLabel newName = new JLabel("ID:");
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
                    userNo = newBookame.getText().toString();
//                    System.out.println(bookno);
                    String[] columnName = new String[]{"ID","密码","姓名","学院","类型","电话","可借书数"};

                    int i;
                    Pattern pattern = Pattern.compile(userNo, Pattern.CASE_INSENSITIVE);
                    List<User> results = new ArrayList<>();
                    for(i = 0; i < users.size(); i++){
                        Matcher matcher = pattern.matcher((users.get(i)).getID());
                        if(matcher.find()){
                            results.add(users.get(i));
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
                    for (Iterator<User> iterator = results.iterator(); iterator.hasNext(); ) {
                        System.out.println(iterator.next());
                    }
                    if(!flag){
                        JOptionPane.showMessageDialog(null, "没找到该用户", "没找到该用户", JOptionPane.WARNING_MESSAGE);
                        newBookame.setText("");
                    }else{
                        String[][] columnBook = new String[results.size()][7];
                        for (int a=0; a<results.size(); a++){
                            columnBook[a][0]=results.get(a).getID();
                            columnBook[a][2]=results.get(a).getName();
                            columnBook[a][1]=results.get(a).getPassword();
                            columnBook[a][3]=results.get(a).getTelephone();
                            columnBook[a][5]=results.get(a).getUnit();
                            columnBook[a][6]=String.valueOf(results.get(a).getCount());
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
            Button_Cancle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    UserManagement userManagement = new UserManagement();
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

