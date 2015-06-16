package com.almoro.angela.mymovieapplication;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.almoro.angela.mymovieapplication.MovieListFragment.OnItemSelectedListener;

public class MovieListActivity extends FragmentActivity implements OnItemSelectedListener {
    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        determinePaneLayout();
    }

    private void determinePaneLayout() {
        FrameLayout fragmentMovieDetail = (FrameLayout) findViewById(R.id.movieDetailContainer);
        if (fragmentMovieDetail != null) {
            isTwoPane = true;
            MovieListFragment fragmentMovieList =
                    (MovieListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMovieList);
            fragmentMovieList.setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(Movie movie) {
        if (isTwoPane) { // single activity with list and detail
            // Replace framelayout with new detail fragment
            MovieDetailFragment fragmentItem = MovieDetailFragment.newInstance(movie);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.movieDetailContainer, fragmentItem);
            ft.commit();
        } else { // go to separate activity
            // For phone, launch detail activity using intent
            Intent i = new Intent(this, MovieDetailActivity.class);
            // Embed the serialized item
            i.putExtra("movie", movie);
            // Start the activity
            startActivity(i);
        }
    }
}
