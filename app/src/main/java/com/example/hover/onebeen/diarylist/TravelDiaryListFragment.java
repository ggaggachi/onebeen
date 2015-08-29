package com.example.hover.onebeen.diarylist;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.TravelDiaryDataSource;
import com.example.hover.onebeen.db.dto.TravelDiary;
import com.example.hover.onebeen.db.dto.TravelStatus;
import com.example.hover.onebeen.diary.TravelDiaryActivity;

import java.util.ArrayList;

public class TravelDiaryListFragment extends Fragment {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.travel_diary_list_view, null);

        Bundle bundle = getArguments();

        final String travelStatus = bundle.getString("travelStatus");

        Log.e("TravelDiaryListFragment", travelStatus);

        TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(getContext());

        ArrayList<TravelDiary> travelDiaries = travelDiaryDataSource.getTravelDiaries(travelStatus);

        Log.e("TravelDiaryListFragment", travelDiaries.toString());

        ArrayList<TravelDiaryListItem> diaryItems = new ArrayList<>();

        if(TravelStatus.BEEN.getValue() == travelStatus) {
            for (TravelDiary travelDiary : travelDiaries) {
                diaryItems.add(new TravelDiaryListItem(travelDiary.getTitle(), travelDiary.getStartDate(), travelDiary.getEndDate(), null, travelDiary.getId()));
            }
        }

        if(TravelStatus.ONGOING.getValue() == travelStatus) {
            for (TravelDiary travelDiary : travelDiaries) {
                diaryItems.add(new TravelDiaryListItem(travelDiary.getTitle(), travelDiary.getStartDate(), null, null, travelDiary.getId()));
            }
        }

        if(TravelStatus.PLANNING.getValue() == travelStatus) {
            for (TravelDiary travelDiary : travelDiaries) {
                diaryItems.add(new TravelDiaryListItem(travelDiary.getTitle(), travelDiary.getStartDate(), null, null, travelDiary.getId()));
            }
        }

        if (diaryItems.size() == 0) {
            LinearLayout rootDiaryView = (LinearLayout) root.findViewById(R.id.root_diary_list_view);
            Drawable drawable = getResources().getDrawable(R.drawable.bg_start);
            rootDiaryView.setBackground(drawable);
        }

        ListView listView = (ListView) root.findViewById(R.id.diary_list_view);

        TravelDiaryListViewAdapter adapter = new TravelDiaryListViewAdapter(getActivity(), R.layout.travel_diary_item, diaryItems, travelStatus);
        listView.setAdapter(adapter);

        return root;
    }
}
