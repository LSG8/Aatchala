package com.example.aatchala;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemCategoryViewModel extends AndroidViewModel {

    private ItemCategoryRepository mRepository;

    public ItemCategoryViewModel(Application application) {
        super(application);
        mRepository = new ItemCategoryRepository(application);
    }

    public LiveData<List<ItemCategory>> getAllCategories() {
        return mRepository.getAllCategories();
    }
}
