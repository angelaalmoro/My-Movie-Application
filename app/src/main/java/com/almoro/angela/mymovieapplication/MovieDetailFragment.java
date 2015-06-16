package com.almoro.angela.mymovieapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ANGELA on 6/15/2015.
 */
public class MovieDetailFragment extends Fragment {
    private Movie movie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = (Movie) getArguments().getSerializable("movie");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate view
        View view = inflater.inflate(R.layout.fragment_movie_detail,
                container, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.movieTitleView);
        TextView tvOverview = (TextView) view.findViewById(R.id.movieOverviewView);
        TextView tvYear = (TextView) view.findViewById(R.id.movieYearView);
        TextView tvRating = (TextView) view.findViewById(R.id.movieRatingView);
        ImageView ivBackdrop = (ImageView) view.findViewById(R.id.movieBackdropView);
        ImageView ivCover = (ImageView) view.findViewById(R.id.movieCoverView);

        String urlBackdrop = "https://dl.dropboxusercontent.com/u/5624850/movielist/images/" + movie.getSlug() + "-backdrop.jpg";
        String urlCover = "https://dl.dropboxusercontent.com/u/5624850/movielist/images/" + movie.getSlug() + "-cover.jpg";

        new LoadImageTask(ivBackdrop,ivCover,tvTitle, tvYear, tvRating, tvOverview,movie).execute(urlBackdrop, urlCover);

        // Return view
        return view;
    }

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fr = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("movie", movie);
        fr.setArguments(args);
        return fr;
    }
}