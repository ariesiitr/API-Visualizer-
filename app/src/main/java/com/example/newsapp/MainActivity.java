package com.example.newsapp;

import android.app.LoaderManager;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private String TAG = MainActivity.class.getSimpleName();

    private String url = "https://content.guardianapis.com/search?show-tags=contributor&api-key=test";
    private ListView list_view;
    //TextView that is displayed when the list is empty.
    private TextView mEmptyTextView;
    NewsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list_view = (ListView) findViewById(R.id.list);
        mEmptyTextView = (TextView) findViewById(R.id.emptyTextView);
        list_view.setEmptyView(mEmptyTextView);


        mAdapter = new NewsAdapter(this, new ArrayList<News>());
        list_view.setAdapter(mAdapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity.
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network.
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loadingIndicator);
            loadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message
            mEmptyTextView.setText(R.string.no_internet_connection);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);return true;
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();

    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loadingIndicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyTextView.setText(R.string.no_news);

        mAdapter.clear();

        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, url);
    }


}


