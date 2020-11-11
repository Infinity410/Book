package com.example.manage;

public interface UserDao {
    public void registerUser(User user);
    public boolean isLoginUser(String name,String password);
    public int getType();
}
