package com.almoro.angela.mymovieapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ANGELA on 6/11/2015.
 */
public class ParseJSONTask extends AsyncTask<String, Void, Boolean> {
    private ProgressDialog dialog;

    private MovieListActivity activity;

    private Context context;
    private static String url = "https://dl.dropboxusercontent.com/u/5624850/movielist/list_movies_page1.json";

    private static final String M_MOVIES = "movies";
    private static final String M_DATA = "data";
    private static final String M_ID = "id";
    private static final String M_TITLE = "title";
    private static final String M_YEAR = "year";
    private static final String M_RATING = "rating";
    private static final String M_OVERVIEW = "overview";
    private static final String M_SLUG = "slug";

    private static ArrayList<HashMap<String, String>> movieList = new ArrayList<HashMap<String, String>>();
    private JSONArray movies;

    public ParseJSONTask(MovieListActivity activity) {
        this.activity = activity;
        context = activity;
        dialog = new ProgressDialog(context);
    }

    protected void onPreExecute() {
        this.dialog.setMessage("Loading movies");
        this.dialog.show();
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        final ArrayList<Movie> movieList = Movie.getMovies();
//        ArrayList<String> movieTitles = new ArrayList<>();
//
//        for(int i=0; i<movieList.size(); i++){
//            movieTitles.add(movieList.get(i).getTitle());
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
//                R.layout.row_layout, R.id.movieTitle, movieTitles);

//        ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(context,
//                android.R.layout.simple_list_item_activated_1, movieList);

        //cannot set background image to a list view item T.T

        ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(context, android.R.layout.simple_list_item_2, android.R.id.text1, movieList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
//                ImageView icon = (ImageView) view.findViewById(android.R.id.icon);

                text1.setText(movieList.get(position).getTitle());
                text2.setText(movieList.get(position).getYear());
//                icon.setBackgroundResource(R.mipmap.ic_launcher);
                return view;
            }
        };

        MovieListFragment.adapterItems = adapter;
        MovieListFragment.listItems.setAdapter(adapter);

        Log.d("Response: ", "> " + "2");
    }

    protected Boolean doInBackground(final String... args) {
        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

        Log.d("Response: ", "> " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                movies = jsonObj.getJSONObject(M_DATA).getJSONArray(M_MOVIES);

                for (int i = 0; i < movies.length(); i++) {
                    JSONObject c = movies.getJSONObject(i);

                    String id = c.getInt(M_ID)+"";
                    String title = c.getString(M_TITLE);
                    String year = c.getInt(M_YEAR)+"";
                    String rating = c.getString(M_RATING)+"";
                    String overview = c.getString(M_OVERVIEW);
                    String slug = c.getString(M_SLUG);

//                    String urlBackdrop = "https://dl.dropboxusercontent.com/u/5624850/movielist/images/" + slug + "-backdrop.jpg";
//                    String urlCover = "https://dl.dropboxusercontent.com/u/5624850/movielist/images/" + slug + "-cover.jpg";

//                    Bitmap mIconBackdrop = null;
//                    Bitmap mIconCover = null;
//
//                    try {
//                        InputStream in = new java.net.URL(urlBackdrop).openStream();
//                        mIconBackdrop = BitmapFactory.decodeStream(in);
//
//                        InputStream in2 = new java.net.URL(urlCover).openStream();
//                        mIconCover = BitmapFactory.decodeStream(in2);
//                    } catch (Exception e) {
//                        Log.e("Error", e.getMessage());
//                        e.printStackTrace();
//                    }
//
//                   // Drawable imageBackdrop = new BitmapDrawable(context.getResources(),mIconBackdrop);
//                    Drawable imageCover = new BitmapDrawable(context.getResources(),mIconCover);

                    Log.d("title: ", "> " + title);

//                    Movie.addMovie(new Movie(title,year, slug, overview, imageBackdrop, imageCover));
                    Movie.addMovie(new Movie(title,year,rating,slug,overview));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context,"Couldn't get any data from the url",Toast.LENGTH_SHORT).show();
        }

        return null;
    }
}