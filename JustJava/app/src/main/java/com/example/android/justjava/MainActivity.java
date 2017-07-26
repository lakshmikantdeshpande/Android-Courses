package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();
    private int quantity = 1;
    private int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText editText = (EditText) findViewById(R.id.name);
        String name = editText.getText().toString();
        if (name.equals("")) {
            Toast.makeText(this, "Oops, you forgot to put a name in !", Toast.LENGTH_SHORT).show();
            return;
        }

        CheckBox checkBox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean whippedCream = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.chocolate);
        boolean chocolate = checkBox.isChecked();

        int price = calculatePrice(whippedCream, chocolate);
        String orderSummary = createOrderSummary(name, price, whippedCream, chocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    public void increment(View view) {
        if (quantity < 100) {
            displayQuantity(++quantity);
        }
    }

    public void decrement(View view) {
        if (quantity > 1) {
            displayQuantity(--quantity);
        }
    }

    /**
     * This method to calculate the price
     *
     * @Param numberOfCoffees
     */
    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        return quantity * (price + (whippedCream ? 1 : 0) + (chocolate ? 2 : 0));
    }

    /*
     * Create the order summary
     */
    private String createOrderSummary(String name, int price, boolean whippedCream, boolean chocolate) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name = " + name + "\n");
        stringBuilder.append("Add whipped cream? " + whippedCream + "\n");
        stringBuilder.append("Add chocolate? " + chocolate + "\n");
        stringBuilder.append("Quantity = " + quantity + "\n");
        stringBuilder.append("Total = $" + price + "\n");
        stringBuilder.append("Thank you!");
        return stringBuilder.toString();
    }

}
