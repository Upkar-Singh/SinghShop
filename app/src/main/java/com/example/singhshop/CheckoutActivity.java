// Student name: Upkar Singh
// Student ID: 1295545

package com.example.singhshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {
    private final double TVQ = 0.09975;
    private final double TPS = 0.05;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        displayTotals();
    }

    /**
     * Method that sets the taxes, total, and final total for each TextView
     */
    public void displayTotals() {
        Intent intent = getIntent();
        double total = Double.parseDouble(intent.getStringExtra(MenuActivity.EXTRA_MESSAGE));

        // References each view then sets the text to the calculations perfomed
        textView = findViewById(R.id.total_textView);
        textView.setText(textView.getText() + " " + String.format("%.2f", total));

        textView = findViewById(R.id.tvq_textView);
        textView.setText(textView.getText() + " " + String.format("%.2f", TVQ * total));

        textView = findViewById(R.id.tps_textView);
        textView.setText(textView.getText() + " " + String.format("%.2f", TPS * total));

        textView = findViewById(R.id.end_total_textView);
        textView.setText(textView.getText() + " " + String.format("%.2f", total + TVQ * total + TPS * total));
        Log.d("Call displayTotals", "displayingTotal");
    }
}
