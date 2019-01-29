package com.example.android.booklistsearch;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by macbook on 7/18/18.
 */

public class BookListAdapter extends ArrayAdapter<BookList> {


    public BookListAdapter(@NonNull Context context, ArrayList<BookList> bookList) {
        super(context, 0, bookList);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_view, parent, false);
        }

        final BookList current_book = getItem(position);



        TextView bookTitle=(TextView)  listItemView.findViewById(R.id.bookTitle);
        String bookName=current_book.getmBookName();
        bookTitle.setText(bookName);

        TextView bookPages=(TextView)  listItemView.findViewById(R.id.author);
        String bookAuthor=current_book.getmBookAuthor();

        bookPages.setText(bookAuthor);


        return listItemView;
    }



}
