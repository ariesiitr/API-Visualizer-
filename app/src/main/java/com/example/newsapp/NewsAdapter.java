package com.example.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> News) {
        super(context, 0, News);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_controls, parent, false);

        }
        News currentNews = getItem(position);
        TextView sectionView = (TextView) listItemView.findViewById(R.id.sectionNameText);
        sectionView.setText(currentNews.getSectionName());
        TextView webTitleView = (TextView) listItemView.findViewById(R.id.webTitleText);
        webTitleView.setText(currentNews.getWebTitle());
        TextView authorView = (TextView) listItemView.findViewById(R.id.authorText);
        // ArrayList<String> authorName=new ArrayList<>();
        //  authorName.add(currentNews.getAuthorName());
        String author = "";
        for (int i = 0; i < currentNews.getAuthorName().size(); i++) {
            author += currentNews.getAuthorName().get(i) + "  ";
        }
        authorView.setText(author);
        TextView publishDateView = (TextView) listItemView.findViewById(R.id.pubDateText);
        try {
            Date pubDate = formatString(currentNews.getPublicationDate());
            publishDateView.setText(formatDate(pubDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private Date formatString(String data) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = format.parse(data);
        return date;

    }
}
