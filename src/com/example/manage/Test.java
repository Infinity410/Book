package com.example.manage;


public class Test {
    public static void main(String[] args) {
        User u1 =new User();
        String st="2019-12-20";
        u1.setSerialNo(1);
        u1.setID("1998017");
        u1.setNo("1014");
        u1.setType(1);
        u1.setOperator("1988003");
        u1.setDatel(st);
        System.out.println(u1);
        UserManagement management = new UserManagement();
        management.addview();
    }

}
