package com.example.marx.emohealth;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.marx.emohealth.com.example.marx.emohealth.data.DataStorage;
import com.example.marx.emohealth.post.Post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class OverviewActivity extends AppCompatActivity {

    String[] itemArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    ArrayList<Post> overviewItems = new ArrayList<Post>();
    ArrayList<Post> displayedList = new ArrayList<Post>();
    ArrayAdapter<Post> adapter;
    ArrayList<Post> currentPosts;

    private TextView overviewDisplayDate;
    private Button btnChangeDate;
    private ListView listView;

    private int year;
    private int month;
    private int day;

    static final int DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateArray();
        currentPosts = DataStorage.readData(getApplicationContext());
        setContentView(R.layout.overview);
        setCurrentDateOnView();
        setListView();
        addListenerOnButton();
    }

    @Override
    protected void onResume(){
        super.onResume();
        addListenerOnButton();
    }

    // display current date
    public void setCurrentDateOnView() {

        overviewDisplayDate = (TextView) findViewById(R.id.dateView);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        String date = new StringBuilder()
                .append(day).append("/").append(month + 1).append("/")
                .append(year).toString();

        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date finalDate;
        try {
            finalDate = newDateFormat.parse(date);
        } catch (ParseException e) {
            finalDate = null;
        }
        newDateFormat.applyPattern("EEEE d MMM yyyy");
        String displayDate = newDateFormat.format(finalDate);
        // set selected date into textview
        overviewDisplayDate.setText(displayDate);
    }

    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DIALOG_ID);

            }

        });

    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, datePickerListener,
                    year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            String date = new StringBuilder()
                    .append(day).append("/").append(month + 1).append("/")
                    .append(year).toString();

            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date finalDate;
            try {
                finalDate = newDateFormat.parse(date);
            } catch (ParseException e) {
                finalDate = null;
            }
            newDateFormat.applyPattern("EEEE d MMM yyyy");
            String displayDate = newDateFormat.format(finalDate);
            // set selected date into textview
            overviewDisplayDate.setText(displayDate);

            displayedList.clear();

            for (int i=0; i < currentPosts.size(); i++) {
                if (dateCompare(currentPosts.get(i).getTimeOfPost().getTime(), finalDate)) {
                    displayedList.add(currentPosts.get(i));
                }
            }

            adapter.notifyDataSetChanged();

        }
    };

    public void setListView() {

        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("313"));

        for (int i=0; i < currentPosts.size(); i++) {
            if (dateCompare(currentPosts.get(i).getTimeOfPost().getTime(), c.getTime())) {
                displayedList.add(currentPosts.get(i));
            }
        }


        adapter = new ArrayAdapter<Post>(this,
                R.layout.activity_overview_listview, displayedList);

        listView = (ListView) findViewById(R.id.overview_item_list);
        listView.setAdapter(adapter);
    }

    public void populateArray() {
        Calendar c1 =  Calendar.getInstance();
        c1.set(2017, 0, 21);
        overviewItems.add(new Post(1, "i am the best", c1));
        overviewItems.add(new Post(1, "i am stupid", c1));
        Calendar c2 =  Calendar.getInstance();
        c2.set(2017, 0, 1);
        overviewItems.add(new Post(1, "i bought a pen", c2));
        overviewItems.add(new Post(1, "i ate chocolate", c2));
    }

    public boolean dateCompare(Date d1, Date d2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

        return fmt.format(d1).equals(fmt.format(d2));
    }
}
