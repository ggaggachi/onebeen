package com.example.hover.onebeen.puzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.PuzzleDataSource;
import com.example.hover.onebeen.db.dto.Puzzle;

public class PuzzleDummyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_dummy);

        View makePuzzleBtn = findViewById(R.id.makePuzzleBtn);
        makePuzzleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PuzzleDummyActivity.this, MakePuzzleActivity.class);
                startActivity(intent);
            }
        });

        View showPuzzleBtn = findViewById(R.id.showPuzzleBtn);
        showPuzzleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PuzzleDataSource puzzleDataSource = new PuzzleDataSource(getApplicationContext());
                EditText editText = (EditText) findViewById(R.id.editText);
                Puzzle puzzle = puzzleDataSource.getPuzzle(Long.parseLong(editText.getText().toString()));

                Intent intent = new Intent(PuzzleDummyActivity.this, MakePuzzleActivity.class);
                intent.putExtra("title", puzzle.getTitle());
                intent.putExtra("description", puzzle.getDescription());
//				intent.putExtra("mediaUri", puzzle.getMediaUri());
                startActivity(intent);
            }
        });
    }
}
