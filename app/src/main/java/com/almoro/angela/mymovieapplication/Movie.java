package com.almoro.angela.mymovieapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ANGELA on 6/15/2015.
 */
public class Movie implements Serializable {
    private static final long serialVersionUID = -1213949467658913456L;
    private String title;
    private String year;
    private String rating;
    private String slug;
    private String overview;
    private Bitmap imageBackdrop;
    private Drawable imageCover;

    private static ArrayList<Movie> movieList = new ArrayList<Movie>();

    public Movie(String title, String year, String rating, String slug, String overview) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.slug = slug;
        this.overview = overview;
//        this.imageBackdrop = imageBackdrop;
//        this.imageCover = imageCover;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }
    public String getRating() {
        return rating;
    }
    public String getSlug() {
        return slug;
    }
    public String getOverview() {
        return overview;
    }

    public Bitmap getImageBackdrop(){
        return imageBackdrop;
    }

    public Drawable getImageCover(){
        return imageCover;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public static ArrayList<Movie> getMovies() {
        return movieList;
    }

    public static void addMovie(Movie movie){
        movieList.add(movie);
    }
}