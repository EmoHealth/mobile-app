package com.example.marx.emohealth;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.marx.emohealth.com.example.marx.emohealth.data.DataStorage;
import com.example.marx.emohealth.post.Post;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

public class PostActivity extends AppCompatActivity implements Serializable{

    final Integer MOOD_SAD = 0;
    final Integer MOOD_NEUTRAL = 1;
    final Integer MOOD_HAPPY = 2;

    private Integer currentMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabWithId(R.id.post);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.overview){
                    Intent intent = new Intent(getApplicationContext(), OverviewActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        setMoodListeners();



    }


    /**
     * The user has decided to post his mood.
     * The method will first save all the details of his current mood into the Internal Storage.
     * Then it will call the intent into the overview activity.
     */
    public void postMood(View view){

        Calendar currentTime = Calendar.getInstance();

        EditText commentMessage = (EditText) findViewById(R.id.comment_line);
        String comment = commentMessage.getText().toString();

        // The user has yet to select a mood. Prompt error pop up
        if (currentMood == null){
            Context context = getApplicationContext();
            CharSequence text = "You need to set a mood.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        // Saves the post into the file and calls the overview activity
        else {
            Post postToSave = new Post(currentMood, comment, currentTime);
            DataStorage.saveData(getApplicationContext(), postToSave);

            Intent intent = new Intent(this, OverviewActivity.class);
            startActivity(intent);
        }
    }


    private void selectMoodSad(){
        currentMood = MOOD_SAD;
        Log.i("Info", "The current mood is " + currentMood);
    }

    private void selectMoodHappy(){
        currentMood = MOOD_HAPPY;
        Log.i("Info", "The current mood is " + currentMood);
    }

    private void selectMoodNeutral(){
        currentMood = MOOD_NEUTRAL;
        Log.i("Info", "The current mood is " + currentMood);
    }

    private void setMoodListeners(){
        ImageButton sadButton = (ImageButton) findViewById(R.id.sad_button);
        sadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectMoodSad();
                v.setSelected(true);
                findViewById(R.id.neutral_button).setSelected(false);
                findViewById(R.id.happy_button).setSelected(false);
            }
        });

        final ImageButton neutralButton = (ImageButton) findViewById(R.id.neutral_button);
        neutralButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectMoodNeutral();
                v.setSelected(true);
                findViewById(R.id.sad_button).setSelected(false);
                findViewById(R.id.happy_button).setSelected(false);

            }
        });

        ImageButton happyButton = (ImageButton) findViewById(R.id.happy_button);
        happyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectMoodHappy();
                v.setSelected(true);
                findViewById(R.id.sad_button).setSelected(false);
                findViewById(R.id.neutral_button).setSelected(false);
            }
        });
    }
}
