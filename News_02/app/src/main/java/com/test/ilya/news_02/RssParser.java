package com.test.ilya.news_02;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RssParser {

    final URL url;

    public  RssParser(String newsUrl){
        try{
            this.url = new URL(newsUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public ArrayList<RssMessage> rssRead()throws Exception{
        ArrayList<RssMessage> messages= new ArrayList<RssMessage>();
        InputStream inputStream = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(10 * 1000);
        connection.setConnectTimeout(10 * 1000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.connect();
        inputStream = connection.getInputStream();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xmlPullParser = factory.newPullParser();
        xmlPullParser.setInput(inputStream, null);

        boolean insideMessage = false;
        int eventType = xmlPullParser.getEventType();
        String link="";
        String title="";

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                if (xmlPullParser.getName().equals("item")) {
                            insideMessage = true;
                }
                else if (xmlPullParser.getName().equals("title")) {
                    if (insideMessage){
                        title = xmlPullParser.nextText();
                    }
                }
                else if(xmlPullParser.getName().equals("link")){
                    if(insideMessage)
                    {
                        if(!title.equals(""))
                        link = xmlPullParser.nextText();
                    }
                }
            }
            else if (eventType == xmlPullParser.END_TAG &&xmlPullParser.getName().equalsIgnoreCase("item"))
            {
                insideMessage = false;
            }
            if(!title.equals("")&&!link.equals("")) {
                messages.add(new RssMessage(title, link));
                title="";
                link="";
            }
            eventType = xmlPullParser.next();
        }
    return messages;
    }

}
