package com.test.ilya.news_02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
