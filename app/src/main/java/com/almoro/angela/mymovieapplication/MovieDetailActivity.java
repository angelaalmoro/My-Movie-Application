package com.almoro.angela.mymovieapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

/**
 * Created by ANGELA on 6/15/2015.
 */
public class MovieDetailActivity extends FragmentActivity {
    MovieDetailFragment fragmentMovieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        // Fetch the item to display from bundle
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        if (savedInstanceState == null) {
            // Insert detail fragment based on the item passed
            fragmentMovieDetail = MovieDetailFragment.newInstance(movie);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.movieDetailContainer, fragmentMovieDetail);
            ft.commit();
        }
    }
}