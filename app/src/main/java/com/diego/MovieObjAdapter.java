package com.diego;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.diego.diego.R;
import com.pojos.MovieObj;

import java.util.List;

/**
 * Created by dam on 18/11/15.
 */
public class MovieObjAdapter extends ArrayAdapter<MovieObj> {


    public MovieObjAdapter(Context context, int resource, List<MovieObj> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieObj movie = getItem(position);

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.pelis_row, parent, false);
        }

        TextView tvPeli = (TextView) convertView.findViewById(R.id.tvPeli);
        tvPeli.setText(movie.getTitle());

        return convertView;
    }
}
