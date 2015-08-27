package com.example.hover.onebeen.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.hover.onebeen.db.TravelDiaryDataSource;
import com.example.hover.onebeen.db.dto.TravelDiary;
import com.example.hover.onebeen.db.dto.TravelStatus;
import com.example.hover.onebeen.utility.ActivityStatus;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.utility.Time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MakeDiary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("여행 일정 만들기");
        setContentView(R.layout.activity_make_diary);

        setToolBar();

        onRegisterTheTravelDiary();
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void onRegisterTheTravelDiary() {
        (findViewById(R.id.register_diary)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText diaryTitle = (EditText) findViewById(R.id.make_diary_title);

                TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(MakeDiary.this);

                Long travelDiaryId = travelDiaryDataSource.insertTravelDiary(new TravelDiary(diaryTitle.getText().toString(), TravelStatus.PLANNING));

                Intent intent = new Intent();
                intent.putExtra("travelDiaryId", String.valueOf(travelDiaryId));

                setResult(ActivityStatus.MAKE_DIARY.getActivityStatus(), intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
