package com.example.marx.emohealth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class stats extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listdataHeader;
    private HashMap<String, ColumnChartFrag> listHash;


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

        ColumnChartFrag chart = new ColumnChartFrag();
        ColumnChartFrag chart2 = new ColumnChartFrag();

        listHash.put(listdataHeader.get(0), chart);
        listHash.put(listdataHeader.get(1), chart2);

    }

}
