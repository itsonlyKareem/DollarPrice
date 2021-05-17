package com.itrex.dollarprice;

public class NewsModel {
    String title;
    String topic;

    public NewsModel() {
    }

    public NewsModel(String title, String topic) {
        this.title = title;
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }



    public String getTopic() {
        return topic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
