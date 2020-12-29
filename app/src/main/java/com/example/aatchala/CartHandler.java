package com.example.aatchala;

import java.util.ArrayList;

public class CartHandler {
    private static ArrayList<String> mCart;

    public CartHandler() {

            ArrayList<String> cart = new ArrayList<String>();
            this.mCart = cart;

    }

    public static ArrayList<String> getCart() {
        return mCart;
    }

    public static void addToCart(String item) {
        mCart.add(item);
    }

    public static void removeFromCart(String item) {
        mCart.remove(item);
    }

    public static void deleteCart() {
        mCart.clear();
    }

}
