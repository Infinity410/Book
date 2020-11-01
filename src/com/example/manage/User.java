package com.example.manage;


public class User {
    private int serialNo;
    private String ID;
    private String no;
    private String datel;
    private int type;
    private String operator;

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDatel() {
        return datel;
    }

    public void setDatel(String datel) {
        this.datel = datel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "User{" +
                "serialNo=" + serialNo +
                ", ID='" + ID + '\'' +
                ", no='" + no + '\'' +
                ", datel=" + datel +
                ", type=" + type +
                ", operator='" + operator + '\'' +
                '}';
    }

}
