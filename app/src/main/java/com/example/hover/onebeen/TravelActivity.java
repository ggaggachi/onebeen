package com.example.hover.onebeen;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.hover.onebeen.utility.TravelListViewAdapter;
import com.example.hover.onebeen.utility.TravelItem;

import java.util.ArrayList;

public class TravelActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_list);

        ListView listView = (ListView) findViewById(R.id.travel_list);

        ArrayList<TravelItem> data = new ArrayList<>();
        TravelItem puzzle1 = new TravelItem(R.drawable.aa, R.drawable.aa, "title", "2015-05-23 13:00:00");
        TravelItem puzzle2 = new TravelItem(R.drawable.aa, R.drawable.aa, "title", "2015-05-23 13:00:00");

        data.add(puzzle1);
        data.add(puzzle2);

        TravelListViewAdapter adapter = new TravelListViewAdapter(this, R.layout.travel_item, data);
        listView.setAdapter(adapter);
    }
}
