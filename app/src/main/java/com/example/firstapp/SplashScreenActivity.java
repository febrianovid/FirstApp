package com.example.firstapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000; // 2 sec

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Pass the TextView in splash screen layout
        TextView versionTextView = findViewById(R.id.versionTextView);

        // Getting app version from versions util class
        String versionName = AppVersionsUtils.getAppVersion(this);
//        int versionCode = AppVersionsUtils.getAppVersionCode(this);

        Log.d("APP VERSION :  ", versionName);

        // Display app version in TextView
        versionTextView.setText("Version " + versionName);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity after the splash time out
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);

                // Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
