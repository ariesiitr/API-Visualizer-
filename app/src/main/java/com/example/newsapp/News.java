package com.example.newsapp;
import java.util.ArrayList;

public class News {
    /*sectionName	The name of the section	String
    webPublicationDate	The combined date and time of publication	Datetime
    webUrl	The URL of the html content	String*/
    private String mSectionName;
    private ArrayList<String> mAuthor;
    private String mwebTitle;
    private String mPublicationDate;
    private String mUrl;

    public News(String sectionName, ArrayList<String> author, String webTitle,String publicationDate,String Url){
        mSectionName=sectionName;
        mAuthor=author;
        mwebTitle=webTitle;
        mPublicationDate=publicationDate;
        mUrl=Url;

    }
    public String getSectionName(){return mSectionName;}
    public ArrayList<String> getAuthorName(){return mAuthor;}
    public String getWebTitle(){return mwebTitle;}
    public String getUrl(){return mUrl;}
    public String getPublicationDate(){return mPublicationDate;}

}
