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

public class AddPuzzleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_puzzle);
        setActionbar();

        ((ImageButton) findViewById(R.id.register_diary)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPuzzle();
            }
        });
    }

    private void insertPuzzle() {
        Intent intent = getIntent();

        Puzzle puzzle = new Puzzle();
        puzzle.setTravelDiaryId(intent.getExtras().getString("travelDiaryId"));

        EditText place = (EditText) findViewById(R.id.add_puzzle_place);
        puzzle.setPlace(place.getText().toString());

        EditText todo = (EditText) findViewById(R.id.add_puzzle_todo);
        puzzle.setTodo(todo.getText().toString());

        PuzzleDataSource puzzleDataSource = new PuzzleDataSource(this);

        UserDataSource userDataSource = new UserDataSource(this);
        puzzle.setUserId(userDataSource.getUser().getId());

        Log.e("ekdxhrl", puzzle.toString());

        long puzzle_id = puzzleDataSource.addPuzzle(puzzle);
        Log.e("ekdxhrl", String.valueOf(puzzle_id));
        Puzzle puzzle1 = puzzleDataSource.getPuzzle(puzzle_id);

        Log.e("ekdxhrl", puzzle1.toString());
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
