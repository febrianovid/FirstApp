package com.example.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TransferPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_popup);

        // Taking data from the previous page / intent
        String destinationNumber = getIntent().getStringExtra("DESTINATION_NUMBER");
        String totalAmount = getIntent().getStringExtra("TOTAL_AMOUNT");

        // Pass data to TextViews
        TextView destinationTextView = findViewById(R.id.destinationTextView);
        destinationTextView.setText("Destination Account Number: " + destinationNumber);

        TextView totalAmountTextView = findViewById(R.id.totalAmountTextView);
        totalAmountTextView.setText("Total Amount: RM " + totalAmount);

        // Close button
        Button closeButton = findViewById(R.id.closeButton);

        // OnClickListener for Close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
