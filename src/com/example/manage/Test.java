package com.example.manage;


public class Test {
    public static void main(String[] args) {
        User u1 =new User();
        u1.setID("123");
        u1.filein();
        System.out.println(u1);
        UserManagement management = new UserManagement();
        management.addview();
    }

}
