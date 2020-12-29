package com.example.aatchala;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemCategoryRepository {

    private ItemCategoryDao mCategoryDao;
    private LiveData<List<ItemCategory>> mAllCategories;

    public ItemCategoryRepository(Application application) {
        AatchalaDatabase db = AatchalaDatabase.getDatabase(application);
        //AatchalaDatabase db = AatchalaDatabase.getInstance(application);
        mCategoryDao = db.itemCategoryDao();
    }

    public LiveData<List<ItemCategory>> getAllCategories() {
        //AatchalaDatabase.databaseWriteExecutor.execute(() -> {
        mAllCategories = mCategoryDao.getCategoryNames();
        //});
        return mAllCategories;
    }
}
