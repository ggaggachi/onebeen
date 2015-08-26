package com.example.hover.onebeen.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hover.onebeen.R;

import java.util.ArrayList;

public class TravelListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<TravelItem> data;
    private int layout;

    public TravelListViewAdapter(Context context, int layout, ArrayList<TravelItem> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }

        TravelItem travelItem = data.get(position);

        ImageView icon1 = (ImageView) convertView.findViewById(R.id.travel_line);
        icon1.setImageResource(travelItem.getStartLineImage());

        ImageView icon2 = (ImageView) convertView.findViewById(R.id.travel_count);
        icon2.setImageResource(travelItem.getStartLineImage());

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(travelItem.getTitle());

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(travelItem.getTitle());

        return convertView;
    }
}
