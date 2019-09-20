package com.example.singhshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {

    private int mCount;
    private double newTotal;
    private double price;
    private double total;
    public static final String EXTRA_MESSAGE =
            "com.example.android.singhshop.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void addItem(View view) {
        Button addBut = findViewById(view.getId());
        ViewGroup cardParentView = (ViewGroup) addBut.getParent().getParent();
        TextView priceTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(3)).getChildAt(1);
        TextView subTotalTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(4)).getChildAt(3);
        TextView quantityTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(3)).getChildAt(3);
        mCount = Integer.parseInt(quantityTextView.getText().toString()) + 1;
        price = Double.parseDouble(priceTextView.getText().toString());
        newTotal = price * mCount;
        total += price;
        subTotalTextView.setText(String.format("$%.2f", newTotal));
        quantityTextView.setText(String.format("%d", mCount));
}

    public void removeItem(View view) {
        Button minusBut = findViewById(view.getId());
        ViewGroup cardParentView = (ViewGroup) minusBut.getParent().getParent();
        TextView priceTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(3)).getChildAt(1);
        TextView subTotalTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(4)).getChildAt(3);
        TextView quantityTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(3)).getChildAt(3);
        mCount = Integer.parseInt(quantityTextView.getText().toString());

        if (mCount > 0) {
            mCount = Integer.parseInt(quantityTextView.getText().toString()) - 1;
            price = Double.parseDouble(priceTextView.getText().toString());
            newTotal = price * mCount;
            total -= price;
            subTotalTextView.setText(String.format("$%.2f", newTotal));
            quantityTextView.setText(String.format("%d", mCount));
        }
    }

    public void launchCheckoutActivity(View view) {
        if (total > 0) {
            Intent intent = new Intent(this, CheckoutActivity.class);
            intent.putExtra(EXTRA_MESSAGE, String.format("%.2f", total));
            startActivity(intent);
        }
    }
}

