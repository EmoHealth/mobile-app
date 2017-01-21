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
    private int lastExpandedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        listView = (ExpandableListView) findViewById(R.id.expandList);
        initData();
        listAdapter = new ExpandableListAdapter(this,listdataHeader,listHash);
        listView.setAdapter(listAdapter);

        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    listView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

    }

    private void initData() {
        listdataHeader = new ArrayList<>();
        listHash = new HashMap<>();

       // listdataHeader.add("Current Week");
       // listdataHeader.add("Previous Week");

        ColumnChartFrag chart = new ColumnChartFrag();
        for (int i = 0; i < 10; i++) {
            listdataHeader.add("Hello World");
            listHash.put(listdataHeader.get(i), chart);
        }

    }

}
