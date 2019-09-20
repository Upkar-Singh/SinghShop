// Student name: Upkar Singh
// Student ID: 1295545

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

    public static final String EXTRA_MESSAGE =
            "com.example.android.singhshop.extra.MESSAGE";
    private int mCount; // stores the quantity
    private double newTotal; // stores the new total mCount * price
    private double price; // the price of each individual item
    private double total; // the overall total of each item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    /**
     * Method adds an item from any cardview's quantity
     * and calls the updateFinalTotal() method to update the
     * overall total
     *
     * @param view the button that is being clicked
     */
    public void addItem(View view) {
        Button addBut = findViewById(view.getId());
        ViewGroup cardParentView = (ViewGroup) addBut.getParent().getParent();

        // references each TextView for the price, subtotal and quantity
        TextView priceTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(3)).getChildAt(1);
        TextView subTotalTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(4)).getChildAt(3);
        TextView quantityTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(3)).getChildAt(3);

        // sets mCount according to each CardView's quantity when a button is clicked
        // and updates the quantity and the subtotal for that CardView then calls updateFinalTotal()
        // to update the overall price
        mCount = Integer.parseInt(quantityTextView.getText().toString()) + 1;
        price = Double.parseDouble(priceTextView.getText().toString());
        newTotal = price * mCount;
        subTotalTextView.setText(String.format("$%.2f", newTotal));
        quantityTextView.setText(String.format("%d", mCount));
        updateFinalTotal();
        Log.d("Call addItem", "addItem");
    }

    /**
     * Method that removes an item from any CardView's quantity
     * and calls the updateFinalTotal() method to update the
     * overall total
     *
     * @param view the button that is being clicked
     */
    public void removeItem(View view) {
        Button minusBut = findViewById(view.getId());
        ViewGroup cardParentView = (ViewGroup) minusBut.getParent().getParent();

        // references each TextView for the price, subtotal and quantity
        TextView priceTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(3)).getChildAt(1);
        TextView subTotalTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(4)).getChildAt(3);
        TextView quantityTextView = (TextView) ((ViewGroup) cardParentView.getChildAt(3)).getChildAt(3);
        mCount = Integer.parseInt(quantityTextView.getText().toString());

        // sets mCount according to each CardView's quantity when a button is clicked
        // and updates the quantity and the subtotal for that CardView then calls updateFinalTotal()
        // to update the overall price
        if (mCount > 0) {
            mCount = Integer.parseInt(quantityTextView.getText().toString()) - 1;
            price = Double.parseDouble(priceTextView.getText().toString());
            newTotal = price * mCount;
            subTotalTextView.setText(String.format("$%.2f", newTotal));
            quantityTextView.setText(String.format("%d", mCount));
            updateFinalTotal();
            Log.d("Call removeItem", "removeItem");
        }
    }

    /**
     * Method called by addItem and removeItem. Updates the overall total for
     * by adding each items subtotal
     */
    public void updateFinalTotal() {
        TextView textView_total = findViewById(R.id.total_textView);

        // stores each CardView's subtotal in a string
        String subtotal_item1 =
                (((TextView) findViewById(R.id.subtotal1_price_textView)).getText().toString());
        String subtotal_item2 =
                (((TextView) findViewById(R.id.subtotal2_price_textView)).getText().toString());
        String subtotal_item3 =
                (((TextView) findViewById(R.id.subtotal3_price_textView)).getText().toString());

        // adds all the subtotals and and sets
        total = Double.parseDouble(subtotal_item1.substring(1)) + Double.parseDouble(subtotal_item2.substring(1))
                + Double.parseDouble(subtotal_item3.substring(1));
        textView_total.setText("Total: " + String.format("%.2f", total));
        Log.d("Call updateFinalTotal", "updateFinalTotal");
    }

    /**
     * Method that creates an intent and stores the overall total, and then
     * changes to the CheckoutActivity
     *
     * @param view the button being clicked
     */
    public void launchCheckoutActivity(View view) {
        if (total > 0) {
            Intent intent = new Intent(this, CheckoutActivity.class);
            intent.putExtra(EXTRA_MESSAGE, String.format("%.2f", total));
            Log.d("Call launchCheckout", "launchCheckoutActivity");
            startActivity(intent);
        }
    }
}

