package com.example.newsapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String mUrl;
    private static final String LOG=MainActivity.class.getSimpleName();
    public NewsLoader(Context context, String url){
        super(context);
        mUrl=url;
    }
    @Nullable
    @Override
    public List<News> loadInBackground() {
        if (mUrl==null)
        {return null;}
        //Perform the network request, parse the response and extract data.
        List<News> newsList=QueryUtils.fetchNewsData(mUrl);
        return  newsList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
