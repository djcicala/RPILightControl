package com.example.light_controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ConnectionsActivity extends AppCompatActivity {

    ListView connectionsList;
    ListAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connections);

        connectionsList = (ListView) findViewById(R.id.bluetoothConnections);

        intent = getIntent();
        String[] addresses = intent.getStringArrayExtra("addresses");
        String[] names     = intent.getStringArrayExtra("names");

        adapter = new ListViewArrayAdapter(this.getApplicationContext(), addresses, names);
        connectionsList.setAdapter(adapter);
        connectionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                intent.putExtra("deviceID", position);
                setResult(0,intent);
                finish();
            }

        });
    }
}
