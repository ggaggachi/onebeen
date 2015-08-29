package com.example.hover.onebeen.diarylist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
//        ((TextView) convertView.findViewById(R.id.diary_subtitle)).setText(diaryItem.getSubTitle());

        Log.e("TravelViewAdapter", "diaryItem:"+diaryItem.toString());

        String date = getCalculatedDiaryDate(diaryItem);

        ((TextView) convertView.findViewById(R.id.diary_date)).setText(date);

        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TravelViewAdapter", "travelStatus:" + travelStatus);
                Log.e("TravelViewAdapter", "travelDiaryId:" + String.valueOf(diaryItem.getId()));
                Intent intent = new Intent(finalConvertView.getContext(), TravelDiaryActivity.class);
                intent.putExtra("travelStatus", travelStatus);
                intent.putExtra("travelDiaryId", String.valueOf(diaryItem.getId()));
                finalConvertView.getContext().startActivity(intent);
                Log.e("TravelViewAdapter", "ClickEventExit");
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(finalConvertView.getContext(), "ASDF", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return convertView;
    }

    @NonNull
    private String getCalculatedDiaryDate(TravelDiaryListItem diaryItem) {
        String date;

        // 계획 중인 경우만 날짜 데이터가 없다.
        // 계획을 세웠으면 여행 시작 날짜가 세팅되어 있을테고
        // 여행을 종료했다면 여행 종료 날짜가 같이 세팅되어 있을 것이다.
        if (diaryItem.getTripStartDate() != null && diaryItem.getTripEndDate() == null) {
            date = diaryItem.getTripStartDate() + " - " + "여행 중";
        } else if (diaryItem.getTripStartDate() != null && diaryItem.getTripEndDate() != null) {
            date = diaryItem.getTripStartDate() + " - " + diaryItem.getTripEndDate();
        } else {
            date = "계획 중";
        }

        return date;
    }
}
