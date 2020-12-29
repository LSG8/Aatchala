package com.example.aatchala;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class PassForgotFragment extends Fragment implements View.OnClickListener {

    private UserViewModel nUserViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pass_forgot, container, false);
        Button submitNewPass = (Button) rootView.findViewById(R.id.newPassSubmit);
        nUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        submitNewPass.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (view.getId() == R.id.newPassSubmit) {
            String phone = readInput(R.id.userIdNewPass);
            String newPass = readPassword(R.id.newPass);
            String newRePass = readPassword(R.id.reNewPass);
            if (phone.matches("") || newPass.matches("")
                    || newRePass.matches(""))
                showErrorStatus("Please fill all required fields!");
            else if (invalidUser(newPass))
                showErrorStatus("Sorry this mobile number is not yet registered!");
            else if (!newPass.equals(newRePass))
                showErrorStatus("Password doesn't match!");
            else {
                updatePassword(newPass,phone);
                Toast.makeText(getApplicationContext(), "Password reset successful", Toast.LENGTH_SHORT).show();
                FragmentTransaction trans = fragmentManager.beginTransaction();
                trans.remove(this).commit();
                fragmentManager.popBackStackImmediate();
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

    private void showErrorStatus(String errorText) {
        TextView tv = (TextView) getView().findViewById(R.id.resetPassErrorStatus);
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

    private void updatePassword(String pass, String phone) {
        nUserViewModel.updatePassword(pass, phone);
    }
}
