package com.example.sravankumar.myapplication.Unused;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sravankumar.myapplication.R;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter {

    private Context mContext;
    private ArrayList<Movie> moviesList = new ArrayList<>();


    public MovieAdapter(@NonNull Context context, ArrayList<Movie> list) {
        super(context , 0, list);
        mContext = context;
        moviesList = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Movie currentMovie = moviesList.get(position);



        TextView name = (TextView) listItem.findViewById(R.id.berth);
        name.setText(currentMovie.getmName());

        TextView release = (TextView) listItem.findViewById(R.id.gender_q);
        release.setText(currentMovie.getmRelease());

        return listItem;
    }
}
