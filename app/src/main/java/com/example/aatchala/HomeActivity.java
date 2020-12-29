package com.example.aatchala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private String user;
    private String status;
    private Session session;
    private UserViewModel nUserViewModel;
    private ItemsViewModel nItemsViewModel;
    private TextView navUsername;
    private MenuItem loginItem;
    private int updateId=1;
    private float updatePrice=580;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nItemsViewModel = new ViewModelProvider(this).get(ItemsViewModel.class);

        //nItemsViewModel.updatePrice(1,35);
        Log.d("item", String.valueOf(updatePrice));
        nItemsViewModel.updatePrice(updateId,updatePrice);
        nUserViewModel = new ViewModelProvider(this).get(UserViewModel .class);
        Log.d("login status",String.valueOf(session.checkLogin()));
        if(session.checkLogin())
        {   String phone = session.getUserId();
            user = nUserViewModel.getUserByPhone(phone).getName();
            status = "Logout";
        } else {
            user = "Guest";
            status = "Login/SignUp";
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawerWork);
        NavigationView navigationView = findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        Menu menu = navigationView.getMenu();
        navUsername =(TextView)headerView.findViewById(R.id.userNameHeader);
        navUsername.setText(user);
        loginItem =menu.findItem(R.id.logInDrawer);
        loginItem.setTitle(status);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void openDonate(View v) {
        startActivity(new Intent(HomeActivity.this, DonateActivity.class));
    }

    public void openContact(View v) {
        startActivity(new Intent(HomeActivity.this, ContactActivity.class));
    }

    public void openStayHome(View v) {
        startActivity(new Intent(HomeActivity.this, StayHomeActivity.class));
    }
    public void share(View v) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "@string/labelHome");
            String shareMessage= "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Choose one"));
            /*Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,cartDetails.getText());
            startActivity(Intent.createChooser(intent,"Share via"));*/
        } catch(Exception e) {
            Log.d("share error", Log.getStackTraceString(e));
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    //@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cart:
                Log.d("error", "is not here");
                viewCart();
                //Toast.makeText(getApplicationContext(), "Clicked cart", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void viewCart() {
        startActivity(new Intent(HomeActivity.this, ViewCartActivity.class));
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profileDrawer:
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                break;
            /*case R.id.orderDrawer:
                startActivity(new Intent(HomeActivity.this, OrderActivity.class));
                break;*/
            case R.id.cartDrawer:
                startActivity(new Intent(HomeActivity.this, ViewCartActivity.class));
                break;
            case R.id.logInDrawer:
                if (!session.checkLogin()) {
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_login,
                            new LoginFragment(), "login").commit();
                } else {
                    session.createLogoutSession();
                    Toast.makeText(getApplicationContext(), "Logged out successfully", Toast.LENGTH_LONG).show();
                    navUsername.setText("Guest");
                    loginItem.setTitle("Login/SignUp");
                }
                break;
            case R.id.aboutUsDrawer:
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                break;
            case R.id.contactUsDrawer:
                startActivity(new Intent(HomeActivity.this, ContactActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
