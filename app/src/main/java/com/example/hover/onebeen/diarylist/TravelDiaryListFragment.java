package com.example.hover.onebeen.diarylist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.TravelDiaryDataSource;
import com.example.hover.onebeen.db.dto.TravelDiary;
import com.example.hover.onebeen.db.dto.TravelStatus;
import java.util.ArrayList;

public class TravelDiaryListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.travel_diary_list_view, null);

        Bundle bundle = getArguments();

        String travelStatus = bundle.getString("travelStatus");

        TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(getContext());

        ArrayList<TravelDiary> travelDiaries = travelDiaryDataSource.getTravelDiaries(travelStatus);

        ArrayList<TravelDiaryListItem> diaryItems = new ArrayList<>();

        if(TravelStatus.BEEN.getValue() == travelStatus) {
            for (TravelDiary travelDiary : travelDiaries) {
                diaryItems.add(new TravelDiaryListItem(travelDiary.getTitle(), travelDiary.getStartDate(), travelDiary.getEndDate(), null));
            }
        }

        if(TravelStatus.ONGOING.getValue() == travelStatus) {
            for (TravelDiary travelDiary : travelDiaries) {
                diaryItems.add(new TravelDiaryListItem(travelDiary.getTitle(), travelDiary.getStartDate(), null, "여행 중"));
            }
        }

        if(TravelStatus.PLANNING.getValue() == travelStatus) {
            for (TravelDiary travelDiary : travelDiaries) {
                diaryItems.add(new TravelDiaryListItem(travelDiary.getTitle(), travelDiary.getStartDate(), null, "계획 중"));
            }
        }

        ListView listView = (ListView) root.findViewById(R.id.diary_list_view);

        TravelDiaryListViewAdapter adapter = new TravelDiaryListViewAdapter(getActivity(), R.layout.travel_diary_item, diaryItems);
        listView.setAdapter(adapter);

        return root;
    }
}
