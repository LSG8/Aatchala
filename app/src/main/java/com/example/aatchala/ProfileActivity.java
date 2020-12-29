package com.example.aatchala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private TextView hi;
    private TextView name;
    private TextView phone;
    private TextView email;
    private TextView address;
    private UserViewModel nUserViewModel;
    private Session session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        hi=findViewById(R.id.HiProfile);
        name=findViewById(R.id.NameProfile);
        phone=findViewById(R.id.PhoneProfile);
        address=findViewById(R.id.AddressProfile);
        email=findViewById(R.id.EmailProfile);
        String text;
        if(session.checkLogin()){
            User user = nUserViewModel.getUserByPhone(session.getUserId());
            name.setText("Name: "+user.getName());
            phone.setText("Phone number: "+user.getPhone());
            address.setText("Address: "+user.getAddress());
            text = "Hello "+user.getName()+"!";
        }
        else{
            text = "Please login first";
        }

        hi.setText(text);
    }
}
