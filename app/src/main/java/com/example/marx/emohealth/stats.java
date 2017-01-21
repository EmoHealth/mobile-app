package com.example.marx.emohealth;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class stats extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listdataHeader;
    private HashMap<String, List<String>> listHash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        listView = (ExpandableListView) findViewById(R.id.expandList);
        initData();
        listAdapter = new ExpandableListAdapter(this,listdataHeader,listHash);
        listView.setAdapter(listAdapter);

    }

    private void initData() {
        listdataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listdataHeader.add("Current Week");
        listdataHeader.add("Previous Week");

        List<String> dev = new ArrayList<>();
        dev.add("data data data");
        dev.add("data ");

        List<String> androidStudio = new ArrayList<>();
        androidStudio.add("data ");
        androidStudio.add("data ");
        androidStudio.add("data ");

        listHash.put(listdataHeader.get(0), dev);
        listHash.put(listdataHeader.get(1), androidStudio);

    }

}
