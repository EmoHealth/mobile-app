package com.example.marx.emohealth;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import com.example.marx.emohealth.com.example.marx.emohealth.data.DataStorage;
import com.example.marx.emohealth.post.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

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
        ArrayList<Post> postData = DataStorage.readData(this);
        int[] postInfo;
        int totalPost;
        int numberOfWeeks;
        int firstPostDate = 0;
        int firstPostMonth = 0;
        int postDate = 0;

        // Stubs stub dub dub
        //Calendar rightNow = Calendar.getInstance();
        //Calendar nextNow = Calendar.getInstance();
       // nextNow.set(2017, 3, 02);
       // Post stubPost = new Post(0, "Hello World", rightNow);
       // Post stubPost2 = new Post(0, "Hello World", nextNow);
       // ArrayList<Post> postData = new ArrayList<Post>();
       // postData.add(0,stubPost);
       // postData.add(1,stubPost2);

        // Read through postData
        postInfo = readPost(postData);

        totalPost = postInfo[0];
        firstPostDate = postInfo[1];
        numberOfWeeks = postInfo[2];

        if (firstPostDate != 0) {
            postDate = firstPostDate;
            ColumnChartFrag chart = new ColumnChartFrag();

            for (int i = 0; i <= numberOfWeeks; i++) {
                if (i == 0 ) {

                    listdataHeader.add("Current Week, " + intToStringDate(firstPostDate) + " ~");
                } else {

                    if ((postDate % 100) < 7) {
                        firstPostMonth = (postDate / 100) - 1;
                        postDate = firstPostMonth * 100;
                        postDate += 23 + (postDate % 100) ;
                    } else {
                        postDate -= 7;
                    }
                    System.out.println("Current postDate is: "+  postDate);
                    listdataHeader.add("Previous Week, from: " + intToStringDate(postDate) + " ~");
                }
                listHash.put(listdataHeader.get(i), chart);
            }
        }
    }

    private int[] readPost(ArrayList<Post> postData) {

        int firstPostDate = -1;
        int lastPostDate = 100000;
        int tempDate;
        int[] postInfo = new int[3];

        // Get data from storage.
        for (int i = 0; i < postData.size(); i++) {
            tempDate = dateToInt(postData.get(i).getTimeOfPost());
            if (tempDate < lastPostDate) {
                lastPostDate = tempDate;
            }
            if (tempDate > firstPostDate) {
                firstPostDate = tempDate;
            }
        }

        postInfo[0] = postData.size();
        postInfo[1] = firstPostDate;
        postInfo[2] = getWeeks(firstPostDate, lastPostDate);
        return postInfo;
    }

    private int getWeeks(int first, int last) {
        int firstDays;
        int lastDays;
        int firstMonths;
        int lastMonths;
        int difference; // number of days apart from the first post to the last

        firstDays = first % 100;
        firstMonths = first/100;

        lastDays = last % 100;
        lastMonths = last/100;

        difference = firstDays - lastDays;
        difference += (firstMonths - lastMonths) * (30);

        return difference/7;
    }

    private int dateToInt(Calendar toIntCalender) {

        // 30/december post will return 30 + 11 *100 = 1130
        Date toIntDate = toIntCalender.getTime();
        int value = toIntDate.getDate();
        value += (toIntDate.getMonth() *100);
        return value;
    }

    private String intToStringDate(int value) {

        // get Day & Month
        int day = value % 100;
        int month = value / 100;

        String monthString = " ";
        switch (month) {
            case 0:  monthString = "January";
                break;
            case 1:  monthString = "February";
                break;
            case 2:  monthString = "March";
                break;
            case 3:  monthString = "April";
                break;
            case 4:  monthString = "May";
                break;
            case 5:  monthString = "June";
                break;
            case 6:  monthString = "July";
                break;
            case 7:  monthString = "August";
                break;
            case 8:  monthString = "September";
                break;
            case 9: monthString = "October";
                break;
            case 10: monthString = "November";
                break;
            case 11: monthString = "December";
                break;
        }
        StringBuilder build = new StringBuilder();
        build.append(Integer.toString(day));
        build.append(" ");
        build.append(monthString);
        return build.toString();
    }
}
