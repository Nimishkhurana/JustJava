package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String notOrdered = "Please choose number of cups";
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whippedCream);
        Boolean hasWhippedCream =  whippedCream.isChecked();
        CheckBox chocolateToppings = (CheckBox) findViewById(R.id.chocolateTopping);
        Boolean hasChocolateToppings = chocolateToppings.isChecked();
        EditText name = findViewById(R.id.name);
        String nameoforderer = name.getText().toString();
        int price = calculatePrice(hasWhippedCream, hasChocolateToppings);
        String subject = "JustJava order for " + nameoforderer;
        String priceMessage= "Name : "+ nameoforderer + "\nQuantity : "+ quantity + "\n\nTOPPINGS\nWhipped Cream: "+hasWhippedCream+"\nChocolate Toppings: "+hasChocolateToppings+"\n\nTotal amount:" + getString(R.string.Rs)+price+"\n\nThank you!";
        if(quantity!=0){
            displayMessage("Order successfull !!");
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
            }

        else
            displayMessage(notOrdered);
    }

    private int calculatePrice(Boolean hasWhippedCream, Boolean hasChocolateToppings)
    {
        int price = quantity * 5;
        if(hasWhippedCream)
            price += quantity;
        if(hasChocolateToppings)
            price += quantity*2;
        return(price);

    }

    public void increment(View view) {
        if(quantity < 100 )
            quantity+=1;
        display(quantity);
    }
    public void decrement(View view) {
        if(quantity > 0)
            quantity-=1;
        display(quantity);
    }





    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    /*private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    */
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}
