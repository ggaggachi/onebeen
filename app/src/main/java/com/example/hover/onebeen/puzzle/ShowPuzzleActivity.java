package com.example.hover.onebeen.puzzle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.PuzzleDataSource;
import com.example.hover.onebeen.db.dto.Puzzle;

import java.util.ArrayList;
import java.util.List;

public class ShowPuzzleActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    private Puzzle puzzle;
    private int imageSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_puzzle);

        setActionbar();

        Intent intent = getIntent();

        final String puzzleId = intent.getExtras().getString("id");

        Log.e("ShowPuzzleActivity", intent.getStringExtra("id"));
        Log.e("ShowPuzzleActivity", intent.getExtras().getString("id"));

        PuzzleDataSource puzzleDataSource = new PuzzleDataSource(this);
        puzzle = puzzleDataSource.getPuzzle(Long.valueOf(puzzleId));

        Log.e("ShowPuzzleActivity", puzzle.toString());

        if (puzzle.getImagePath1() == null) {
            imageSize = 0;
        } else if (puzzle.getImagePath2() == null) {
            imageSize = 1;
        } else if (puzzle.getImagePath3() == null) {
            imageSize = 2;
        } else {
            imageSize = 3;
        }

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ShowPuzzleFragment.create(puzzle, position);
        }

        @Override
        public int getCount() {
            return imageSize;
        }

    }

    private void setActionbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("추억 꺼내보기");
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
