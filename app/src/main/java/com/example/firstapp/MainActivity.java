package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        AppAdapter adapter = new AppAdapter(getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Item click listener
        adapter.setOnItemClickListener(new AppAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click based on position in the page
                switch (position) {
                    case 0:
                        Intent intent1 = new Intent(MainActivity.this, SavingAccountActivity.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(MainActivity.this, DuitNowTransferActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(MainActivity.this, QrPayActivity.class);
                        startActivity(intent3);
                    // Add more cases for additional menu items
                }
            }
        });
    }

    // Data for the RecyclerView
    private List<BoxItem> getData() {
        List<BoxItem> dataList = new ArrayList<>();
        dataList.add(new BoxItem(R.drawable.savingacc, "Saving Account"));
        dataList.add(new BoxItem(R.drawable.duitnowlogo, "DuitNow Transfer"));
        dataList.add(new BoxItem(R.drawable.qrpay, "QR Pay"));
        return dataList;
    }
}
