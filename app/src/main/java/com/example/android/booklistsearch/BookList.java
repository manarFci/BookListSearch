package com.example.android.booklistsearch;

/**
 * Created by macbook on 1/20/19.
 */

public class BookList {
    private String mImageURL;
    private String mBookName;
    private String mBookAuthor;
    private String mPreviewURL;

    public BookList( String bookName,String author,String previewURL){

      mBookName=  bookName;
        mBookAuthor=author;
       mPreviewURL=previewURL;


    }
    public String getmImageURL(){

        return mImageURL;
    }
    public String getmBookName(){
        return mBookName;
    }
    public String getmBookAuthor(){
        return mBookAuthor;
    }
    public String  getmPreviewURL(){
        return mPreviewURL;
    }


}
