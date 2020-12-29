package com.example.aatchala;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemsRepository {

    private ItemsDao mItemDao;
    private List<Items> mAllItems;
    private List<Items> itemsByCategory;
    private Items itemById;

    public ItemsRepository(Application application) {
        AatchalaDatabase db = AatchalaDatabase.getDatabase(application);
        //AatchalaDatabase db = AatchalaDatabase.getInstance(application);
        mItemDao = db.itemsDao();
    }

    public List<Items> getAllItems() {
        //AatchalaDatabase.databaseWriteExecutor.execute(() -> {
            mAllItems = mItemDao.getAllItems();
        //});
        return mAllItems;
    }

    public List<Items> getAllItemsByCategory(String category) {
        //AatchalaDatabase.databaseWriteExecutor.execute(() -> {
        itemsByCategory = mItemDao.getItemsByCategory(category);
        //});
        return itemsByCategory;
    }

    public Items getItemById(int id) {
        //AatchalaDatabase.databaseWriteExecutor.execute(() -> {
        itemById = mItemDao.getItemById(id);
        //});
        return itemById;
    }

    public void updatePrice(int id, float newPrice) {
        //AatchalaDatabase.databaseWriteExecutor.execute(() -> {
        mItemDao.updatePrice(id,newPrice);
        //});
    }

    public void updateInStock(int id, boolean newInStock) {
        //AatchalaDatabase.databaseWriteExecutor.execute(() -> {
        mItemDao.updateInStock(id,newInStock);
        //});
    }


}
