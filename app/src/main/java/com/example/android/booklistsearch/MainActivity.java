package com.example.android.booklistsearch;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    public static final String LOG_TAG = MainActivity.class.getName();

   //private static final String USGS_REQUEST_URL="https://www.googleapis.com/books/v1/volumes?q=intitle+android";

    private BookListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView bookListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new BookListAdapter(this, new ArrayList<BookList>());
        bookListView.setAdapter(mAdapter);
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                BookList currentBook = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri bookUri = Uri.parse(currentBook.getmPreviewURL());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);


                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        Button searchButton=(Button)findViewById(R.id.searchBtn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookListAsyncTask task = new BookListAsyncTask();
                task.execute(getBookTitle());

            }
        });


        }


    public String getBookTitle() {

        final EditText NameField = (EditText) findViewById(R.id.searchName);
        String booktitle=NameField.getText().toString();
        String USGS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=intitle+"+booktitle;

        return USGS_REQUEST_URL;
    }


    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the list of earthquakes in the response.
     * <p>
     * AsyncTask has three generic parameters: the input type, a type used for progress updates, and
     * an output type. Our task will take a String URL, and return an Earthquake. We won't do
     * progress updates, so the second generic is just Void.
     * <p>
     * We'll only override two of the methods of AsyncTask: doInBackground() and onPostExecute().
     * The doInBackground() method runs on a background thread, so it can run long-running code
     * (like network activity), without interfering with the responsiveness of the app.
     * Then onPostExecute() is passed the result of doInBackground() method, but runs on the
     * UI thread, so it can use the produced data to update the UI.
     */
   private class BookListAsyncTask extends AsyncTask<String, Void, List<BookList>> {


        @Override
        protected List<BookList> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<BookList> result = QueryUtils.fetchBookList(urls[0]);
            return result;
        }


        @Override
        protected void onPostExecute(List<BookList> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();
            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }

        }
}






}
