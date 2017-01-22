package com.example.marx.emohealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;


public class menuBarFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_bar, container, false);

        BottomBar bottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.post);
        bottomBar.getTabWithId(R.id.overview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OverviewActivity.class);
                startActivity(intent);
                view.setSelected(true);
            }
        });
        bottomBar.getTabWithId(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PostActivity.class);
                startActivity(intent);
                view.setSelected(true);
            }
        });
        bottomBar.getTabWithId(R.id.stats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), stats.class);
                startActivity(intent);
                view.setSelected(true);
            }
        });
        bottomBar.getTabWithId(R.id.circles).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CirclesActivity.class);
                startActivity(intent);
                view.setSelected(true);
            }
        });


        return view;
    }
}