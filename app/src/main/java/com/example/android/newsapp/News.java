package com.example.android.newsapp;

/**
 * Created by Hp on 07/06/2017.
 */

public class News {

    // Declaring variables
    private final String mCategory;
    private final String mTitle;
    private final String mPublicationDate;
    private final String mUrl;

    // Construct a new News object
    public News(String category, String title, String publicationDate, String url) {
        mCategory = category;
        mTitle = title;
        mPublicationDate = publicationDate;
        mUrl = url;
    }

    // Getters methods that return the parameters
    public String getCategory() {
        return mCategory;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
