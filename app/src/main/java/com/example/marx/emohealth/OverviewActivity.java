package com.example.marx.emohealth;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OverviewActivity extends AppCompatActivity {

    private TextView overviewDisplayDate;
    private Button btnChangeDate;

    private int year;
    private int month;
    private int day;

    static final int DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);
        setCurrentDateOnView();
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
        }
    };
}
