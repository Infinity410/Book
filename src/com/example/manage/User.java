package com.example.manage;
import java.io.*;

public class User {
    private String ID;
    private String password;
    private String name;
    private String unit;
    private int type;
    private String telephone;
    private int count;

    public void User(){
        ID="";password="";name="";unit="";type=0;telephone="";count=0;
    }

    public void User(String id,String pass,String na,String un,int ty,String tele,int co){
        ID=id;password=pass;name=na;unit=un;type=ty;telephone=tele;count=co;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;

    }
    public void filein(){
        try {
            File bookmsg = new File("User.txt");
            if (!bookmsg.exists()) {
                try {
                    boolean createResult = bookmsg.createNewFile();
                    if (createResult) {
                        System.out.println("文件创建成功！");
                    } else {
                        System.out.println("文件创建失败！");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fos = new FileOutputStream(bookmsg);
            ObjectOutputStream writein = new ObjectOutputStream(fos);
            writein.writeObject(this.ID);
            writein.writeObject(this.password);
            writein.writeObject(this.name);
            writein.writeObject(this.unit);
            writein.writeObject(this.type);
            writein.writeObject(this.telephone);
            writein.writeObject(this.count);
            fos.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", type=" + type +
                ", telephone='" + telephone + '\'' +
                ", count=" + count +
                '}';
    }
}
