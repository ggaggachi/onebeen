package com.example.hover.onebeen.puzzle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.dto.Puzzle;

public class PuzzleDummyActivity extends AppCompatActivity {

    private final MakePuzzleFragment fragment = new MakePuzzleFragment();
    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_dummy);

        setTitle("");
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        dlDrawer.closeDrawers();

        setSupportActionBar(toolbar);

        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();


        View makePuzzleBtn = findViewById(R.id.makePuzzleBtn);
        makePuzzleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PuzzleDummyActivity.this, MakePuzzleFragment.class);

                Puzzle puzzle = new Puzzle();
                puzzle.setPlace("해운대");
                puzzle.setTodo("해수욕장에서 수영하기");
                intent.putExtra("puzzle", puzzle);
                startActivity(intent);
            }
        });

        View showPuzzleBtn = findViewById(R.id.showPuzzleBtn);
        showPuzzleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PuzzleDataSource puzzleDataSource = new PuzzleDataSource(getApplicationContext());
                EditText editText = (EditText) findViewById(R.id.editText);
//                Puzzle puzzle = puzzleDataSource.getPuzzle(Long.parseLong(editText.getText().toString()));

                Intent intent = new Intent(PuzzleDummyActivity.this, ShowPuzzleActivity.class);
                intent.putExtra("insertedRow", Long.parseLong(editText.getText().toString()));
                startActivity(intent);
            }
        });
    }

    public void closeDrawers() {
        dlDrawer.closeDrawers();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

// Sync the toggle state after onRestoreInstanceState has occurred.
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
