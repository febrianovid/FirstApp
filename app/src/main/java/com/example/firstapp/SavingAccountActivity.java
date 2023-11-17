package com.example.firstapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class SavingAccountActivity extends AppCompatActivity {

    private static final String TAG = "SavingAccountActivity";
    private TextView accountNameTextView;
    private TextView moneyTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Activity created");
        setContentView(R.layout.activity_saving_account);

        accountNameTextView = findViewById(R.id.accountNameTextView);
        moneyTextView = findViewById(R.id.moneyTextView);

        // Url to fetch JSON data
        String apiUrl = "https://mocki.io/v1/ad804626-c982-4fc2-86e5-41feb8fa491b";

        // Making the network request
        fetchAccountDetails(apiUrl);
    }

    private void fetchAccountDetails(String apiUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handleResponse(response);
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handleFailure(e);
            }
        });
    }

    private void handleResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            String responseData = response.body().string();
            JsonObject jsonObject = new Gson().fromJson(responseData, JsonObject.class);

            String accountNameHolder = jsonObject.get("account_name_holder").getAsString();
            double money = jsonObject.get("money").getAsDouble();

            logAndUIUpdate(accountNameHolder, money);
        } else {
            Log.e(TAG, "Response unsuccessful: " + response.code());
        }
    }

    private void handleFailure(IOException e) {
        Log.e(TAG, "Network request failed: " + e.getMessage());
        e.printStackTrace();
    }

    private void logAndUIUpdate(String accountNameHolder, double money) {
        Log.d(TAG, "Account Name Holder: " + accountNameHolder);
        Log.d(TAG, "Total Balance: RM" + money);

        runOnUiThread(() -> updateUI(accountNameHolder, money));
    }

    private void updateUI(String accountNameHolder, double money) {
        Log.d(TAG, "Updating UI with: " + accountNameHolder + ", RM" + money);
        accountNameTextView.setText("Hi " + accountNameHolder);
        moneyTextView.setText("Total Balance\n" + "RM" + money);
    }
}
