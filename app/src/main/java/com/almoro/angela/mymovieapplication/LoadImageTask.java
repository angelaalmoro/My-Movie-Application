package com.almoro.angela.mymovieapplication;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ANGELA on 6/13/2015.
 */
public class LoadImageTask extends AsyncTask<String, Void, ArrayList<Bitmap>> {
    private ProgressDialog dialog;
    ImageView bmImage;
    ImageView coverImage;
    TextView title;
    TextView year;
    TextView rating;
    TextView overview;
    Movie movie;

    public LoadImageTask(ImageView bmImage, ImageView coverImage, TextView title, TextView year, TextView rating, TextView overview, Movie movie) {
        this.bmImage = bmImage;
        this.coverImage = coverImage;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.overview = overview;
        this.movie = movie;
        dialog = new ProgressDialog(bmImage.getContext());
    }

    protected void onPreExecute() {
        this.dialog.setMessage("Loading information");
        this.dialog.show();
    }

    protected ArrayList<Bitmap> doInBackground(String... urls) {
        String urlBackdrop = urls[0];
        String urlCover = urls[1];
        Bitmap mIconBackdrop = null;
        Bitmap mIconCover = null;
        ArrayList<Bitmap> mIcons = new ArrayList<>();
        try {
            InputStream in = new java.net.URL(urlBackdrop).openStream();
            mIconBackdrop = BitmapFactory.decodeStream(in);
            mIcons.add(mIconBackdrop);

            in = new java.net.URL(urlCover).openStream();
            mIconCover = BitmapFactory.decodeStream(in);
            mIcons.add(mIconCover);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcons;
    }

    protected void onPostExecute(ArrayList<Bitmap> result) {
        bmImage.setImageBitmap(result.get(0));
        coverImage.setImageBitmap(result.get(1));
        title.setText(movie.getTitle());
        year.setText(movie.getYear());
        rating.setText(movie.getRating());
        overview.setText(movie.getOverview());

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}