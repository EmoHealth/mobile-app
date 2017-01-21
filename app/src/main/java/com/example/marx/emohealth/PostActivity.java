package com.example.marx.emohealth;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.marx.emohealth.post.Post;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

public class PostActivity extends AppCompatActivity implements Serializable{

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
        setMoodListeners();
    }


    /**
     * The user has decided to post his mood.
     * The method will first save all the details of his current mood into the Internal Storage.
     * Then it will call the intent into the overview activity.
     */
    public void postMood(View view){

        Log.i("Info", "I got here");

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

        else {
            Post postToSave = new Post(currentMood, comment, currentTime);
            saveData(postToSave);
        }
    }

    protected void saveData(Post postToSave){

        try {
            FileOutputStream fos = openFileOutput(MainActivity.userFileToSave, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(postToSave);
            oos.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
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
            }
        });

        ImageButton neutralButton = (ImageButton) findViewById(R.id.neutral_button);
        neutralButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectMoodNeutral();
            }
        });

        ImageButton happyButton = (ImageButton) findViewById(R.id.happy_button);
        happyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectMoodHappy();
            }
        });
    }
}
