package com.example.hover.onebeen;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;

/**
 * Created by seojin on 2015-08-12.
 */
public class VisitedActivity  extends ActionBarActivity {
    private ListView listView;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitedlistview);

        // sqlite에서 저장된 값을 가지고옴
        // 여행 제목, 여행 날짜에 저장됨
        // 아이템에 띄워짐
        // 리스트 목록에 띄워짐
        VisitedlistItem visited_data[] = new VisitedlistItem[]{

                new VisitedlistItem("title1", "2015 08 01"),
                new VisitedlistItem("title2", "2015 08 02")};


        VisitedAdapter adapter = new VisitedAdapter(this,
                R.layout.activity_visitedlistitem, visited_data);


        listView = (ListView) findViewById(R.id.listView);

        View header = getLayoutInflater().inflate(R.layout.activity_visitedlistitem, null);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);

    }
}