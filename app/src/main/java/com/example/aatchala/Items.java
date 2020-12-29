package com.example.aatchala;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "items_info")
public class Items {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "inStock")
    private boolean inStock;
    @ColumnInfo(name = "price")
    private float price;
    @ColumnInfo(name = "unit")
    private String unit;

    @Ignore
    public Items(){}

    public Items(@NonNull int id, String name, String category, boolean inStock, float price, String unit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.inStock = inStock;
        this.price = price;
        this.unit = unit;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int iid) {
        this.id = iid;
    }

    public String getName() {
        return name;
    }

    public void setName(String iName) {
        this.name = iName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String iCategory) {
        this.category = iCategory;
    }

    public boolean getInStock() {
        return inStock;
    }

    public void setInStock(boolean iInStock) {
        this.inStock = iInStock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float iPrice) {
        this.price = iPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setStatus(String iUnit) {
        this.unit = iUnit;
    }
}
