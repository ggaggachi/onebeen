package com.example.hover.onebeen.puzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.PuzzleDataSource;
import com.example.hover.onebeen.db.TravelDiaryDataSource;
import com.example.hover.onebeen.db.UserDataSource;
import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.db.dto.TravelDiary;
import com.example.hover.onebeen.db.dto.TravelStatus;
import com.example.hover.onebeen.diary.TravelDiaryActivity;

public class AddPuzzleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_puzzle);
        setActionbar();

        Intent intent = getIntent();
        final String travelDiaryId = intent.getExtras().getString("travelDiaryId");

        TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(this);
        TravelDiary travelDiary = travelDiaryDataSource.getTravelDiary(Long.valueOf(travelDiaryId));

        Log.e("AddPuzzleActivity", "onCreate");

        if (TravelStatus.ONGOING.equals(travelDiary.getTravelStatus().getValue())) {
            onClickPuzzleStatus(travelDiaryId, String.valueOf(PuzzleStatus.BEEN));
        } else {
            onClickPuzzleStatus(travelDiaryId, String.valueOf(PuzzleStatus.WANT));
        }
    }

    private void onClickPuzzleStatus(final String travelDiaryId1, final String puzzleStatus) {
        ((ImageButton) findViewById(R.id.register_diary)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Puzzle puzzle = new Puzzle();
                puzzle.setTravelDiaryId(travelDiaryId1);

                EditText place = (EditText) findViewById(R.id.add_puzzle_place);
                puzzle.setPlace(place.getText().toString());

                EditText todo = (EditText) findViewById(R.id.add_puzzle_todo);
                puzzle.setTodo(todo.getText().toString());

                PuzzleDataSource puzzleDataSource = new PuzzleDataSource(AddPuzzleActivity.this);

                UserDataSource userDataSource = new UserDataSource(AddPuzzleActivity.this);
                puzzle.setUserId(userDataSource.getUser().getId());
                puzzle.setStatus(puzzleStatus);

                Log.e("AddPuzzleActivity", puzzle.toString());

                long puzzleId = puzzleDataSource.addPuzzle(puzzle);
                Log.e("AddPuzzleActivity", String.valueOf(puzzleId));
                Puzzle puzzle1 = puzzleDataSource.getPuzzle(puzzleId);

                Log.e("AddPuzzleActivity", puzzle1.toString());

                String travelDiaryId = travelDiaryId1;
                Intent intent = new Intent(AddPuzzleActivity.this, TravelDiaryActivity.class);
                intent.putExtra("travelDiaryId", travelDiaryId);

                startActivity(intent);
            }
        });
    }

    private void setActionbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("퍼즐 추가하기");
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
