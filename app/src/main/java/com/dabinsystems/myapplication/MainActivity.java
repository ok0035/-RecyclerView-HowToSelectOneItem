package com.dabinsystems.myapplication;

import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.dabinsystems.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ArrayList<ListData> list = new ArrayList<>();

        for(int i=0; i< 100; i++) {
            list.add(new ListData(Math.random() + ""));
        }

        ListAdapter adapter = new ListAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();

    }
}
