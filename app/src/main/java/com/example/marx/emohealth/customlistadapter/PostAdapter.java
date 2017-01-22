package com.example.marx.emohealth.customlistadapter;

/**
 * Created by weijieseow on 22/1/17.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marx.emohealth.PostActivity;
import com.example.marx.emohealth.R;
import com.example.marx.emohealth.post.Post;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {
    public PostAdapter(Context context, ArrayList<Post> post) {
        super(context, 0, post);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Post post = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_overview_listview, parent, false);
        }
        // Lookup view for data population
        ImageView emotionIcon = (ImageView) convertView.findViewById(R.id.emotionIcon);
        TextView postComment = (TextView) convertView.findViewById(R.id.item);
        // Populate the data into the template view using the data object
        postComment.setText(post.getComments());
        if (post.getMood() == PostActivity.MOOD_SAD) {
            emotionIcon.setImageResource(R.drawable.sad_default);
        }
        else if (post.getMood() == PostActivity.MOOD_NEUTRAL) {
            emotionIcon.setImageResource(R.drawable.neutral_default);
        }
        else {
            emotionIcon.setImageResource(R.drawable.happy_default);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
