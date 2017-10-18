package com.hacknews.hacknews.models;

/**
 * Created by Eshan on 10/18/17.
 */

public class UpdateNewEvent
{
    private final long news_id;

    public UpdateNewEvent(long message) {
        this.news_id = message;
    }

    public long getMessage() {
        return news_id;
    }
}
