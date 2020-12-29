package com.example.aatchala;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "order_info")
public class Order {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "orderList")
    private String orderList;

    @Ignore
    public Order(){}

    public Order(@NonNull String name, String phone, String address, String orderList) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.orderList = orderList;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String uPhone) {
        this.phone = uPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String uName) {
        this.name = uName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String uAddress) {
        this.address = uAddress;
    }

    public String getOrderList() {
        return orderList;
    }

    public void setOrderList(String uOrderList) {
        this.orderList = uOrderList;
    }
}
