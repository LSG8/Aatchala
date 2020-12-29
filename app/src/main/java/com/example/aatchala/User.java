package com.example.aatchala;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_info")
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "pass")
    private String pass;

    @Ignore
    public User(){}

    public User(@NonNull String phone, String name, String address, String pass) {
        this.phone=phone;
        this.name = name;
        this.address=address;
        this.pass=pass;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String uPass) {
        this.pass = uPass;
    }

    @Override
    public String toString() {
        return "User: " +
                "name= " + name +
                ", phone= " + phone +
                ", address= " + address +
                ", password= " + pass;
    }
}
