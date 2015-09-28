package com.test.ilya.news_02;


public class RssMessage {
    String title;
    String link;

    public RssMessage(){
        this("","");
    }
    public RssMessage(String title,String link){
        this.title = title;
        this.link = link;
    }

    public String getTitle(){
        return this.title;
    }
    public String getLink(){
        return this.link;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setLink(String link){
        this.link = link;
    }
}
