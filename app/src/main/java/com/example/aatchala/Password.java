package com.example.aatchala;

import android.widget.EditText;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    private EditText mEditText;

    public Password(EditText editText) {
        this.mEditText = editText;
    }

    public String getEncrypPass() {
        String input = mEditText.getText().toString();
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
