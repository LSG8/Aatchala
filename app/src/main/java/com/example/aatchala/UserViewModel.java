package com.example.aatchala;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    //private LiveData<List<User>> mAllUsers;

    public UserViewModel(Application application) {
        super(application);
        mRepository = new UserRepository(application);
        //mAllUsers = mRepository.getAllUsers();
    }

    public List<User> getAllUsers() {
        return mRepository.getAllUsers();
    }

    public void addUser(User user) {
        mRepository.insert(user);
    }

    public void updatePassword(String passNew, String phone) {
        mRepository.updatePassword(passNew, phone);
    }

    public User getUserByPhone(String phone) {
        return mRepository.getUserByPhone(phone);
    }
}
