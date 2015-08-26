package com.example.hover.onebeen.diary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hover.onebeen.R;

import java.util.ArrayList;

public class TravelDiaryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.travel_diary_list_view, null);

        ListView listView = (ListView) root.findViewById(R.id.diary_list_view);

        ArrayList<TravelDiaryItem> diaryItems = new ArrayList<>();
        diaryItems.add(new TravelDiaryItem("부산여행", "2015.8.26", "2015.8.27", null));
        diaryItems.add(new TravelDiaryItem("부산여행", null, null, "여행을 시작해 보세요."));
        diaryItems.add(new TravelDiaryItem("부산여행", "2015.8.26", "2015.8.27", null));
        diaryItems.add(new TravelDiaryItem("부산여행", null, null, "여행을 시작해 보세요."));
        diaryItems.add(new TravelDiaryItem("부산여행", "2015.8.26", "2015.8.27", null));
        diaryItems.add(new TravelDiaryItem("부산여행", null, null, "여행을 시작해 보세요."));

        TravelDiaryListViewAdapter adapter = new TravelDiaryListViewAdapter(getActivity(), R.layout.travel_diary_item, diaryItems);
        listView.setAdapter(adapter);

        return root;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.travel_diary_list_view);
//
//        ListView listView = (ListView) findViewById(R.id.diary_list_view);
//
//        ArrayList<TravelDiaryItem> diaryItems = new ArrayList<>();
//        diaryItems.add(new TravelDiaryItem("부산여행", "2015.8.26", "2015.8.27", null));
//        diaryItems.add(new TravelDiaryItem("부산여행", null, null, "여행을 시작해 보세요."));
//        diaryItems.add(new TravelDiaryItem("부산여행", "2015.8.26", "2015.8.27", null));
//        diaryItems.add(new TravelDiaryItem("부산여행", null, null, "여행을 시작해 보세요."));
//        diaryItems.add(new TravelDiaryItem("부산여행", "2015.8.26", "2015.8.27", null));
//        diaryItems.add(new TravelDiaryItem("부산여행", null, null, "여행을 시작해 보세요."));
//
//        TravelDiaryListViewAdapter adapter = new TravelDiaryListViewAdapter(this, R.layout.travel_diary_item, diaryItems);
//        listView.setAdapter(adapter);
//    }
}
