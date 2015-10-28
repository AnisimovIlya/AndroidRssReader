package com.test.ilya.news_02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button newsClickButton = (Button) findViewById(R.id.news_list_button);
        newsClickButton.setOnClickListener(
             new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final Intent intent = new Intent(MainActivity.this,NewsListActivity.class);
               startActivity(intent);
            }
        });
        Button settingsClickButton = (Button) findViewById(R.id.settings_button);
        settingsClickButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                        startActivity(intent);
                    }
                });

    }

}
