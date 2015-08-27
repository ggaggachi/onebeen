package com.example.hover.onebeen.diarylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hover.onebeen.R;

import java.util.ArrayList;

public class TravelDiaryListViewAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<TravelDiaryListItem> data;

    public TravelDiaryListViewAdapter(Context context, int layout, ArrayList<TravelDiaryListItem> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public TravelDiaryListItem getItem(int position) {
        return data.get(position);
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

        TravelDiaryListItem diaryItem = data.get(position);

        ((TextView) convertView.findViewById(R.id.diary_title)).setText(diaryItem.getTripTitle());
        ((TextView) convertView.findViewById(R.id.diary_subtitle)).setText(diaryItem.getSubTitle());

        String date = diaryItem.getTripEndDate() != null ? diaryItem.getTripStartDate() + " - " + diaryItem.getTripEndDate() : null;
        ((TextView) convertView.findViewById(R.id.diary_date)).setText(date);

        return convertView;
    }
}
