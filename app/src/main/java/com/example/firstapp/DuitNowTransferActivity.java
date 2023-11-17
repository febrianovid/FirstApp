package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DuitNowTransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duit_now_transfer);

        Button transferButton = findViewById(R.id.transferButton);
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTransferButtonClick(view);
            }
        });
    }

    public void onTransferButtonClick(View view) {
        EditText destinationEditText = findViewById(R.id.destinationAccountEditText);
        EditText amountEditText = findViewById(R.id.amountToTransferEditText);

        String destinationAccountNumber = destinationEditText.getText().toString();
        String amountToTransfer = amountEditText.getText().toString();

        // Create Intent to go to TransferPopupActivity
        Intent intent = new Intent(this, TransferPopupActivity.class);

        // Pass data with intent
        intent.putExtra("DESTINATION_NUMBER", destinationAccountNumber);
        intent.putExtra("TOTAL_AMOUNT", amountToTransfer);

        // Start TransferPopupActivity
        startActivity(intent);
    }
}


//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;

//public class DuitNowTransferActivity extends AppCompatActivity {
//
//    private TextView accountNameTextView;
//    private TextView moneyTextView;
//    private EditText destinationAccountEditText;
//    private EditText amountToTransferEditText;
//    private Button transferButton;
//

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_duit_now_transfer);
//
//        // Initialize views
//        accountNameTextView = findViewById(R.id.accountNameTextView);
//        moneyTextView = findViewById(R.id.moneyTextView);
//        destinationAccountEditText = findViewById(R.id.destinationAccountEditText);
//        amountToTransferEditText = findViewById(R.id.amountToTransferEditText);
//        transferButton = findViewById(R.id.transferButton);
//
//        // Set click listener for the transfer button
//        transferButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Check if all input fields are filled
//                if (isValidInput()) {
//                    // Check if the user has enough money
//                    if (hasEnoughMoney()) {
//                        // Perform the transfer
//                        performTransfer();
//                    } else {
//                        // Show insufficient funds message
//                        showToast("Insufficient funds");
//                    }
//                } else {
//                    // Show missing input fields message
//                    showToast("Please fill all input fields");
//                }
//            }
//        });
//    }
//
//    private boolean isValidInput() {
//        // Check if all input fields are filled
//        return !destinationAccountEditText.getText().toString().isEmpty() &&
//                !amountToTransferEditText.getText().toString().isEmpty();
//    }
//
//    private boolean hasEnoughMoney() {
//        // Read user data from JSON file
//        double userMoney = readUserMoneyFromJson();
//
//        // Get the amount to transfer from the input field
//        double amountToTransfer = Double.parseDouble(amountToTransferEditText.getText().toString());
//
//        // Check if the user has enough money
//        return userMoney >= amountToTransfer;
//    }
//
//    private void performTransfer() {
//        // Read user data from JSON file
//        double userMoney = readUserMoneyFromJson();
//
//        // Get the amount to transfer from the input field
//        double amountToTransfer = Double.parseDouble(amountToTransferEditText.getText().toString());
//
//        // Log the values for debugging
//        Log.d("DuitNowTransferActivity", "User Money: " + userMoney);
//        Log.d("DuitNowTransferActivity", "Amount to Transfer: " + amountToTransfer);
//
//        // Update user's balance in JSON file
//        double updatedBalance = userMoney - amountToTransfer;
//        updateBalanceInJson(updatedBalance);
//
//        // Show success message or navigate to a success screen
//        showToast("Transfer successful");
//    }
//    private double readUserMoneyFromJson() {
//        // Read user data from raw resources and return the money amount
//        try {
//            InputStream inputStream = getResources().openRawResource(R.raw.users);
//            int size = inputStream.available();
//            byte[] buffer = new byte[size];
//            inputStream.read(buffer);
//            inputStream.close();
//
//            String json = new String(buffer, "UTF-8");
//            JSONObject jsonObject = new JSONObject(json);
//            return jsonObject.getDouble("money");
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//            return 0; // Handle error gracefully in a real application
//        }
//    }
//
//
//
//    private void updateBalanceInJson(double updatedBalance) {
//        try {
//            // Read the original JSON content from the file in internal storage
//            InputStream inputStream = getResources().openRawResource(R.raw.users);
//            int size = inputStream.available();
//            byte[] buffer = new byte[size];
//            inputStream.read(buffer);
//            inputStream.close();
//
//            // Convert the byte array to a string
//            String json = new String(buffer, "UTF-8");
//
//            // Parse the original JSON string
//            JSONObject jsonObject = new JSONObject(json);
//
//            // Update the balance in the JSON object
//            jsonObject.put("money", updatedBalance);
//
//            // Convert the updated JSON object to a string
//            String updatedJson = jsonObject.toString();
//
//            // Write the updated JSON back to the file in internal storage
//            writeJsonToFile(updatedJson);
//
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//            // Handle error gracefully in a real application
//        }
//    }
//
//    private void writeJsonToFile(String json) {
//        try {
//            // Specify the file name
//            String fileName = "users.json";
//
//            // Open the file in private mode (MODE_PRIVATE)
//            FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
//
//            // Create a BufferedWriter to write the JSON to the file
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
//
//            // Write the JSON to the file
//            bufferedWriter.write(json);
//
//            // Close the streams
//            bufferedWriter.close();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle error gracefully in a real application
//        }
//    }
//
//    private void showToast(String message) {
//        // Implement the logic to show a toast message
//        // Example:
//        // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//}
