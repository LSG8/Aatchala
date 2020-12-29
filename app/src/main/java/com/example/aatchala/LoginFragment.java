package com.example.aatchala;

import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private Session session;
    private UserViewModel nUserViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        nUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        Button submitLogin = (Button) rootView.findViewById(R.id.loginSubmit);
        Button submitSignUp = (Button) rootView.findViewById(R.id.signUpSubmit);
        Button forgetPass = (Button) rootView.findViewById(R.id.loginForgotPassword);
        submitLogin.setOnClickListener(this);
        submitSignUp.setOnClickListener(this);
        forgetPass.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        showErrorStatus("");
        switch (view.getId()) {
            case R.id.loginSubmit:
                String phone = readInput(R.id.userNameInput);
                String pass = readInput(R.id.userPasswordInput);
                //Log.d("user and pass", phone + pass);
                if (phone.matches("") || pass.matches(""))
                    showErrorStatus("Enter mobile number and password both");
                else if (invalidUser(phone))
                    showErrorStatus("Sorry this mobile number is not yet registered!");
                else if (invalidPass(phone, pass))
                    showErrorStatus("Sorry wrong password, type the correct one or tap the forgot password link!");
                else {
                    session.createLoginSession(phone);
                    NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_drawer);
                    View headerView = navigationView.getHeaderView(0);
                    Menu menu =navigationView.getMenu();
                    TextView navUsername = (TextView) headerView.findViewById(R.id.userNameHeader);
                    MenuItem loginItem = menu.findItem(R.id.logInDrawer);
                    navUsername.setText(nUserViewModel.getUserByPhone(session.getUserId()).getName());
                    loginItem.setTitle("Logout");
                    new CartHandler();
                    Toast.makeText(getActivity().getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    fragmentManager.popBackStack();
                    fragmentManager.beginTransaction().remove(this).commit();
                }
                break;
            case R.id.loginForgotPassword:
                fragmentManager.beginTransaction().replace(R.id.fragment_container_login,
                        new PassForgotFragment()).addToBackStack("login").commit();
                break;
            case R.id.signUpSubmit:
                if(session.checkLogin()) {
                    Toast.makeText(getActivity().getApplicationContext(), "First log out", Toast.LENGTH_LONG).show();
                    fragmentManager.popBackStack();
                    fragmentManager.beginTransaction().remove(this).commit();
                }
                else
                fragmentManager.beginTransaction().replace(R.id.fragment_container_login,
                        new RegisterFragment(), "register").addToBackStack("login").commit();
                break;
        }
    }

    private String readInput(int idName) {
        EditText et = (EditText) getView().findViewById(idName);
        String txt = et.getText().toString();
        return txt;
    }

    private boolean invalidUser(String user) {
        Boolean invalid = true;

        try {
            List<User> validUsers = nUserViewModel.getAllUsers();
            for (User checkUser : validUsers) {
                if (checkUser.getPhone().equals(user)) {
                    invalid = false;
                    break;
                }
            }
        } catch (Exception e) {
            Log.d("invalid user error", Log.getStackTraceString(e));
        }
        return invalid;
    }

    private boolean invalidPass(String user, String pass) {
        Boolean invalid = true;
        try {
            User login = nUserViewModel.getUserByPhone(user);
            if (!login.getPass().equals(pass))
                invalid = false;

        } catch (Exception e) {
            Log.d("invalid pass error", Log.getStackTraceString(e));
        }

        return invalid;
    }

    private void showErrorStatus(String errorText) {
        TextView tv = (TextView) getView().findViewById(R.id.loginErrorStatus);
        tv.setText(errorText);
    }

}