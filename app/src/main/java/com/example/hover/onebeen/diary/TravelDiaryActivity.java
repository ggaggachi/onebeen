package com.example.hover.onebeen.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.PuzzleDataSource;
import com.example.hover.onebeen.db.TravelDiaryDataSource;
import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.db.dto.TravelDiary;
import com.example.hover.onebeen.puzzle.AddPuzzleActivity;
import com.example.hover.onebeen.puzzle.PuzzleStatus;
import com.example.hover.onebeen.puzzle.ShowPuzzleActivity;
import java.util.ArrayList;
import java.util.List;

public class TravelDiaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_diary);
        setSupportActionBar();

        Intent intent = getIntent();
        String travelDiaryId = intent.getExtras().getString("travelDiaryId");

        Log.e("gg", travelDiaryId);

        TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(this);
        TravelDiary travelDiary = travelDiaryDataSource.getTravelDiary(Long.valueOf(travelDiaryId));
        Log.e("ekdxhrl travel:", travelDiary.toString());
//
//        travelDiary.getTitle();
//        travelDiary.getBackgroundImagePath();
//
//        PuzzleDataSource puzzleDataSource = new PuzzleDataSource(this);
//
//        List<Puzzle> puzzles1 = puzzleDataSource.getPuzzles(travelId);

        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new Puzzle(1, null, PuzzleStatus.BEEN.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기1", null));
        puzzles.add(new Puzzle(1, null, PuzzleStatus.BEEN.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기2", null));
        puzzles.add(new Puzzle(1, null, PuzzleStatus.CURRENT.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기3", null));
        puzzles.add(new Puzzle(1, null, PuzzleStatus.WANT.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기4", null));

        puzzles.add(null);

        ListView listView = (ListView) findViewById(R.id.puzzle_list_view);
        listView.setAdapter(new PuzzleListViewAdapter(getLayoutInflater(), R.layout.puzzle_item, puzzles));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tag = String.valueOf(view.getTag());

                Log.e("ekdxhrl", tag);

                if (tag == "CURRENT") {
//                    startActivity(new Intent(TravelDiaryActivity.this, ShowPuzzleActivity.class));
                } else if (tag == "BEEN") {
                    startActivity(new Intent(TravelDiaryActivity.this, ShowPuzzleActivity.class));
                } else {
                    startActivity(new Intent(TravelDiaryActivity.this, AddPuzzleActivity.class));
                }
            }
        });
    }

    private void setSupportActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
