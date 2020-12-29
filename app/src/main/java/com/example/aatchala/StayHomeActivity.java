package com.example.aatchala;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class StayHomeActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay_home);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), getResources().getString(R.string.tab1));
        adapter.addFragment(new Tab2Fragment(), getResources().getString(R.string.tab2));
        adapter.addFragment(new Tab3Fragment(), getResources().getString(R.string.tab3));
        adapter.addFragment(new Tab4Fragment(), getResources().getString(R.string.tab4));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}