package com.almoro.angela.mymovieapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by ANGELA on 6/16/2015.
 *
 * Note: Not used and finished.
 */
public class MovieListAdapter extends ArrayAdapter {
    private LayoutInflater inflater;

    public MovieListAdapter(Activity activity, String[] items){
        super(activity, R.layout.row_layout, items);
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return inflater.inflate(R.layout.row_layout, parent, false);
    }

}