package com.almoro.angela.mymovieapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ANGELA on 6/15/2015.
 */
public class MovieListFragment extends Fragment {
    private OnItemSelectedListener listener;
    public static ListView listItems;
    public static ArrayAdapter<Movie> adapterItems;

    public interface OnItemSelectedListener {
        public void onItemSelected(Movie movie);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(
                    activity.toString()
                            + " must implement ItemsListFragment.OnListItemSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Response: ", "> " + "1");
        if(Movie.getMovies().size()==0) {
            new ParseJSONTask((MovieListActivity) getActivity()).execute();
        }

//        // Create arraylist from item fixtures
//        ArrayList<Movie> movieList = Movie.getMovies();
//        // Create adapter based on items
//        adapterItems = new ArrayAdapter<Movie>(getActivity(),
//                android.R.layout.simple_list_item_activated_1, movieList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Response: ", "> " + "3");
        // Inflate view
        View view = inflater.inflate(R.layout.fragment_movie_list,
                container, false);
        // Attach adapter to listview
        listItems = (ListView) view.findViewById(R.id.movieListItems);
        //listItems.setAdapter(adapterItems);
        listItems.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position,
                                    long rowId) {
                // Retrieve item based on position
                Movie i = adapterItems.getItem(position);
                // Fire selected event for item
                listener.onItemSelected(i);
            }
        });
        // Return view
        return view;
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        listItems.setChoiceMode(
                activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
                        : ListView.CHOICE_MODE_NONE);
    }
}
