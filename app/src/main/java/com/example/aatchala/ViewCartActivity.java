package com.example.aatchala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;

public class ViewCartActivity extends AppCompatActivity {
    private CartHandler mCartItems;
    private TextView cartDetails;
    private Session session;
    private Button del;
    private Button share;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        del = (Button) findViewById(R.id.deleteCart);
        share = (Button) findViewById(R.id.submitCart);
        String text ="";
        cartDetails = findViewById(R.id.cartView);
        if(session.checkLogin()){
            Log.d("cart", String.valueOf(mCartItems.getCart().size()));
            del.setVisibility(View.VISIBLE);
            share.setVisibility(View.VISIBLE);
            if(mCartItems.getCart().size()>0){
                for(int i=0;i<mCartItems.getCart().size();i++){
                    text+=String.valueOf(i+1)+". ";
                    text+=mCartItems.getCart().get(i);
                    text+="\n";
                }
            }
            else
                text = "Cart is empty";
        }
        else{
            del.setVisibility(View.GONE);
            share.setVisibility(View.GONE);
            text = "Please login first";
        }
        cartDetails.setText(text);
    }

    public void deleteCart(View v) {
        mCartItems.deleteCart();
        cartDetails.setText("");
    }

    public void shareCart(View v) {
        //startActivity(new Intent(HomeActivity.this, ContactActivity.class));
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        /*intent.putExtra(Intent.EXTRA_TEXT,cartDetails.getText());
        startActivity(Intent.createChooser(intent,"Share via"));*/

        intent.setPackage("com.whatsapp");

        //intent.putExtra("jid", "+919477019653" + "@s.whatsapp.net");
        intent.putExtra(Intent.EXTRA_TEXT, cartDetails.getText());

        /*String url = "https://api.whatsapp.com/send?phone="+ "9477019653" +"&text=" + "my message";*/
        /*String url = "whatsapp://send?phone=+919477019653";
        intent.setData(Uri.parse(url));*/
        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            //Toast.makeText(this, "Error/n" + ex.toString(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
        }
    }
}