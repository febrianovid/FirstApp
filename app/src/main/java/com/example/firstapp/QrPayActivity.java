package com.example.firstapp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class QrPayActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_qr_pay);
    }
}
