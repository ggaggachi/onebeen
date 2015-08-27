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
import com.example.hover.onebeen.db.UserDataSource;
import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.db.dto.User;
import com.example.hover.onebeen.diary.TravelDiaryActivity;

public class AddPuzzleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_puzzle);
        setActionbar();

        ((ImageButton) findViewById(R.id.register_diary)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String travelDiaryId = insertPuzzle();
                Intent intent = new Intent(AddPuzzleActivity.this, TravelDiaryActivity.class);
                intent.putExtra("travelDiaryId", travelDiaryId);
                startActivity(intent);
            }
        });
    }

    private String insertPuzzle() {
        Intent intent = getIntent();
        String travelDiaryId = intent.getExtras().getString("travelDiaryId");

        Puzzle puzzle = new Puzzle();
        puzzle.setTravelDiaryId(travelDiaryId);

        EditText place = (EditText) findViewById(R.id.add_puzzle_place);
        puzzle.setPlace(place.getText().toString());

        EditText todo = (EditText) findViewById(R.id.add_puzzle_todo);
        puzzle.setTodo(todo.getText().toString());

        PuzzleDataSource puzzleDataSource = new PuzzleDataSource(this);

        UserDataSource userDataSource = new UserDataSource(this);
        puzzle.setUserId(userDataSource.getUser().getId());
        puzzle.setStatus("WANT");
        Log.e("ekdxhrl", puzzle.toString());

        long puzzleId = puzzleDataSource.addPuzzle(puzzle);
        Log.e("ekdxhrl", String.valueOf(puzzleId));
        Puzzle puzzle1 = puzzleDataSource.getPuzzle(puzzleId);

        Log.e("ekdxhrl", puzzle1.toString());

        return travelDiaryId;
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
