package com.example.manage.circulation;

public class Circulation {
    private int serialNo;
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "Circulation{" +
                "serialNo=" + serialNo +
                ", id='" + id + '\'' +
                ", no=" + no +
                ", datel='" + datel + '\'' +
                ", type=" + type +
                ", operator='" + operator + '\'' +
                '}';
    }
}
