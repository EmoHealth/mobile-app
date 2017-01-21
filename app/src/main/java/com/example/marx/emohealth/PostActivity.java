package com.example.marx.emohealth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class PostActivity extends AppCompatActivity {

    final String MOOD_SAD = "mood-sad";
    final String MOOD_NEUTRAL = "mood-neutral";
    final String MOOD_HAPPY = "mood-happy";


    private String currentMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }


    /**
     * The user has decided to post his mood.
     * The method will first save all the details of his current mood into the Internal Storage.
     * Then it will call the intent into the overview activity.
     */
    protected void postMood(){

        Calendar currentTime = Calendar.getInstance();

        EditText commentMessage = (EditText) findViewById(R.id.comment_line);
        String comment = commentMessage.getText().toString();

        // The user has yet to select a mood. Prompt error pop up
        if (currentMood == null){

        }



    }

    protected void saveData(){

    }

    private void selectMoodSad(){
        currentMood = MOOD_SAD;
    }

    private void selectMoodHappy(){
        currentMood = MOOD_HAPPY;
    }

    private void selectMoodNeutral(){
        currentMood = MOOD_NEUTRAL;
    }

}
