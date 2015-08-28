package com.example.hover.onebeen.diarylist;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.diary.TravelDiaryActivity;

import java.util.ArrayList;

public class TravelDiaryListViewAdapter extends BaseAdapter{

    private final String travelStatus;
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<TravelDiaryListItem> data;

    public TravelDiaryListViewAdapter(Context context, int layout, ArrayList<TravelDiaryListItem> data, String travelStatus) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
        this.data = data;
        this.travelStatus = travelStatus;
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

        final TravelDiaryListItem diaryItem = data.get(position);

        ((TextView) convertView.findViewById(R.id.diary_title)).setText(diaryItem.getTripTitle());
        ((TextView) convertView.findViewById(R.id.diary_subtitle)).setText(diaryItem.getSubTitle());

        String date = diaryItem.getTripEndDate() != null ? diaryItem.getTripStartDate() + " - " + diaryItem.getTripEndDate() : null;
        ((TextView) convertView.findViewById(R.id.diary_date)).setText(date);

        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TravelViewAdapter", "travelStatus:"+travelStatus);
                Log.e("TravelViewAdapter", "travelDiaryId:"+String.valueOf(diaryItem.getId()));
                Intent intent = new Intent(finalConvertView.getContext(), TravelDiaryActivity.class);
                intent.putExtra("travelStatus", travelStatus);
                intent.putExtra("travelDiaryId", String.valueOf(diaryItem.getId()));
                finalConvertView.getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
