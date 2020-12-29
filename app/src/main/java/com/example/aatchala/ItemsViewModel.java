package com.example.aatchala;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemsViewModel extends AndroidViewModel {

    private ItemsRepository mRepository;

    public ItemsViewModel(Application application) {
        super(application);
        mRepository = new ItemsRepository(application);
    }

    public List<Items> getAllItems() {
        return mRepository.getAllItems();
    }
    public List<Items> getItemsByCategory(String category) {
        return mRepository.getAllItemsByCategory(category);
    }
    public Items getItemById(int id) {
        return mRepository.getItemById(id);
    }

    public void updatePrice(int id, float newPrice) {mRepository.updatePrice(id,newPrice);}

    public void updateInStock(int id, boolean newInStock) {}
}
