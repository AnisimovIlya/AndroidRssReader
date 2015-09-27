package com.test.ilya.news_02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


public class SettingsActivity extends Activity {


    SharedPreferences settingsUrl;
    String url;
    View.OnClickListener radioListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = (RadioButton) v;
                switch (radioButton.getId()) {
                    case R.id.rbk_auto_radio_btn:
                        url = "http://static.feed.rbc.ru/rbc/internal/rss.rbc.ru/autonews.ru/mainnews.rss";
                        break;
                    case R.id.rbk_all_radio_btn:
                        url = "http://static.feed.rbc.ru/rbc/internal/rss.rbc.ru/rbc.ru/news.rss";
                        break;
                    case R.id.rbk_sport_btn:
                        url = "http://static.feed.rbc.ru/rbc/internal/rss.rbc.ru/sport.rbc.ru/newsline.rss";
                        break;
                    default:
                        break;
                }
            }
        };
        Button sportClick = (Button) findViewById(R.id.rbk_sport_btn);
        sportClick.setOnClickListener(radioListener);
        Button allNewsClick =(Button) findViewById(R.id.rbk_all_radio_btn);
        allNewsClick.setOnClickListener(radioListener);
        Button autoNewsClick = (Button) findViewById(R.id.rbk_auto_radio_btn);
        autoNewsClick.setOnClickListener(radioListener);

        Button saveButton = (Button)findViewById(R.id.save_btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsUrl = getSharedPreferences("news_settings",MODE_PRIVATE);
                SharedPreferences.Editor ed = settingsUrl.edit();
                ed.putString("news_url",url);
                ed.commit();
                final Intent intent = new Intent(SettingsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }




}
