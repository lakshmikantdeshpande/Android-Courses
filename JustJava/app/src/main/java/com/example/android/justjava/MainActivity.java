package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int quantity = 1;
    private int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    public void increment(View view) {
        displayQuantity(++quantity);
    }

    public void decrement(View view) {
        if (quantity > 1) {
            displayQuantity(--quantity);
        } else
            Toast.makeText(this, "Oops, quantity can't be less than one", Toast.LENGTH_SHORT).show();
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method to calculate the price
     *
     * @Param numberOfCoffees
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /*
     * Create the order summary
     */
    private String createOrderSummary(int price) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name = Kaptain Kunal\n");
        stringBuilder.append("Quantity = " + quantity + "\n");
        stringBuilder.append("Total = $" + price + "\n");
        stringBuilder.append("Thank you!");
        return stringBuilder.toString();
    }

}
