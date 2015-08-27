package com.example.hover.onebeen.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.diarylist.TravelDiaryListItem;
import com.example.hover.onebeen.diarylist.TravelDiaryListViewAdapter;
import com.example.hover.onebeen.puzzle.AddPuzzleActivity;
import com.example.hover.onebeen.puzzle.PuzzleStatus;

import java.util.ArrayList;

public class TravelDiaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_diary);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ListView listView = (ListView) findViewById(R.id.puzzle_list_view);

        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new Puzzle(1, null, PuzzleStatus.BEEN.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기1", null));
        puzzles.add(new Puzzle(1, null, PuzzleStatus.BEEN.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기2", null));
        puzzles.add(new Puzzle(1, null, PuzzleStatus.CURRENT.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기3", null));
        puzzles.add(new Puzzle(1, null, PuzzleStatus.WANT.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기4", null));
        puzzles.add(null);

        PuzzleListViewAdapter puzzleListViewAdapter = new PuzzleListViewAdapter(getLayoutInflater(), R.layout.puzzle_item, puzzles);
        listView.setAdapter(puzzleListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TravelDiaryActivity.this, AddPuzzleActivity.class);
                startActivityForResult(intent, 1);
            }
        });
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
