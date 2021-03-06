package com.test.ilya.news_02;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsListActivity extends AppCompatActivity {
    SharedPreferences settingsPrefs;
    GetTitleNews getTitlesTask;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getTitlesTask = new GetTitleNews(this);
        getTitlesTask.execute();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            Intent intent = new Intent(NewsListActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return true;
    }



    public class GetTitleNews extends AsyncTask<Void, ArrayList<RssMessage>, ArrayList<RssMessage>> {

        Context context;
        public GetTitleNews(Context context){
            this.context = context;
        }

        @Override
        protected ArrayList<RssMessage> doInBackground(Void... params) {
            ArrayList<RssMessage> messages = new ArrayList<RssMessage>();
            String newsUrl;
            settingsPrefs = getSharedPreferences("news_settings",MODE_PRIVATE);
            newsUrl = settingsPrefs.getString("news_url","http://static.feed.rbc.ru/rbc/internal/rss.rbc.ru/rbc.ru/news.rss");
            Log.d("URL",newsUrl);
            RssParser parser = new RssParser(newsUrl);
            try {
                messages = parser.rssRead();
            } catch (Exception e) {
                e.printStackTrace();
                return messages;
            }
            return messages;
        }

        @Override
        protected void onPostExecute(final ArrayList<RssMessage>messages){
            final ListView listView = (ListView) findViewById(R.id.news_list);
            List <String> titles = new ArrayList<String>();
            for(RssMessage message : messages ){
                titles.add(message.getTitle());
            }
            ArrayAdapter<String> arAd = new ArrayAdapter<String>(context,
                    android.R.layout.simple_list_item_1,
                    titles);

            listView.setAdapter(arAd);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    String clickedNewsTitle =listView.getItemAtPosition(position).toString();
                    String urlToSend="";
                    for (RssMessage m:messages){
                        if(m.getTitle().equals(clickedNewsTitle))
                        {
                            urlToSend = m.getLink();
                            break;
                        }
                    }
                    intent.putExtra("url", urlToSend);
                    startActivity(intent);
                }
            });
        }
    }

}
