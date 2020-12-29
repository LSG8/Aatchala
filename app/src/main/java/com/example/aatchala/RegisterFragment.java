package com.example.aatchala;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;
import java.util.List;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private UserViewModel nUserViewModel;
    private Session session;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        Button submitRegister = (Button) rootView.findViewById(R.id.registerSubmit);
        submitRegister.setOnClickListener(this);
        /*EditText et = (EditText) rootView.findViewById(R.id.registerDOB);
        DatePickerUniversal dob = new DatePickerUniversal(et, "dd-MM-yyyy");*/
        nUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerSubmit) {
            String name = readInput(R.id.registerName);
            String phone = readInput(R.id.registerPhone);
            String address = readInput(R.id.registerAddress);
            //String dob = readDOB(R.id.registerDOB);
            String pass = readPassword(R.id.registerPassword);
            String rePass = readPassword(R.id.registerReTypePassword);
            //String email = readInput(R.id.registerEmail);
            showErrorStatus("");

            if (name.matches("") || address.matches("")
                    || phone.matches("") || pass.matches(""))
                showErrorStatus("Please fill all required fields");
            else if (!pass.equals(rePass))
                showErrorStatus("Password doesn't match");
            else if (!invalidUser(phone))
                showErrorStatus("Sorry this mobile number is already registered");
            else {
                User newDBUser = new User(phone, name, address, pass);
                //Log.d("userdb",newDBUser.toString());
                nUserViewModel.addUser(newDBUser);
                //session.createLoginSession(phone);
                Toast.makeText(getActivity().getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                //getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            }
        }
    }

    private String readInput(int idName) {
        EditText et = (EditText) getView().findViewById(idName);
        String txt = et.getText().toString();
        return txt;
    }

    private String readPassword(int idName) {
        EditText et = (EditText) getView().findViewById(idName);
        Password readPass = new Password(et);
        String txt = readPass.getEncrypPass();
        return txt;
    }

    private String readDOB(int idName) {
        EditText et = (EditText) getView().findViewById(idName);
        DatePickerUniversal dob = new DatePickerUniversal(et, "dd-MMM-yyyy");
        String txt = dob.toString();
        return txt;
    }

    private void showErrorStatus(String errorText) {
        TextView tv = (TextView) getView().findViewById(R.id.registerErrorStatus);
        tv.setText(errorText);
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
}
