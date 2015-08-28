package com.example.hover.onebeen.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hover.onebeen.MainActivity;
import com.example.hover.onebeen.R;
import com.example.hover.onebeen.db.PuzzleDataSource;
import com.example.hover.onebeen.db.TravelDiaryDataSource;
import com.example.hover.onebeen.db.UserDataSource;
import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.db.dto.TravelDiary;
import com.example.hover.onebeen.db.dto.TravelStatus;
import com.example.hover.onebeen.db.dto.User;
import com.example.hover.onebeen.diarylist.TravelDiaryListItem;
import com.example.hover.onebeen.puzzle.AddPuzzleActivity;
import com.example.hover.onebeen.puzzle.SavePuzzleActivity;
import com.example.hover.onebeen.puzzle.ShowPuzzleActivity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.ProfilePictureView;
import java.util.ArrayList;

public class TravelDiaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.activity_diary);
        setSupportActionBar();

        Intent intent = getIntent();
        final String travelDiaryId = intent.getExtras().getString("travelDiaryId");
        String travelStatus = intent.getExtras().getString("travelStatus");

        setTopMenu(travelDiaryId);

        PuzzleDataSource puzzleDataSource = new PuzzleDataSource(this);

        final ArrayList<Puzzle> puzzles = puzzleDataSource.getPuzzles(Long.valueOf(travelDiaryId));

        convertToViewAccodingToTravelStatus(travelStatus, puzzles);

//        ArrayList<Puzzle> puzzles = new ArrayList<>();
//        puzzles.add(new Puzzle(1, null, PuzzleStatus.BEEN.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기1", null));
//        puzzles.add(new Puzzle(1, null, PuzzleStatus.BEEN.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기2", null));
//        puzzles.add(new Puzzle(1, null, PuzzleStatus.CURRENT.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기3", null));
//        puzzles.add(new Puzzle(1, null, PuzzleStatus.WANT.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기4", null));
//
//        puzzles.add(null);

        ListView listView = (ListView) findViewById(R.id.puzzle_list_view);
        listView.setAdapter(new PuzzleListViewAdapter(getLayoutInflater(), R.layout.puzzle_item, puzzles));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tag = String.valueOf(view.getTag());
                String puzzleId = String.valueOf(view.getTag("puzzleId".hashCode()));

                if ("CURRENT".equals(tag)) {
//                    startActivity(new Intent(TravelDiaryActivity.this, ShowPuzzleActivity.class));
                } else if ("BEEN".equals(tag)) {
//                    startActivity(new Intent(TravelDiaryActivity.this, ShowPuzzleActivity.class));
//                    addPuzzleIntent.putExtra("puzzleId", puzzledId);
//                    startActivity(addPuzzleIntent);
                } else if ("WANT".equals(tag)) {
                    Intent addPuzzleIntent = new Intent(TravelDiaryActivity.this, SavePuzzleActivity.class);
                    addPuzzleIntent.putExtra("puzzleId", puzzleId);
                    startActivity(addPuzzleIntent);
                } else {
                    Intent addPuzzleIntent = new Intent(TravelDiaryActivity.this, AddPuzzleActivity.class);
                    addPuzzleIntent.putExtra("travelDiaryId", travelDiaryId);
                    startActivity(addPuzzleIntent);
                }
            }
        });

        ((Button) findViewById(R.id.register_travel_diary)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TravelDiaryActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void convertToViewAccodingToTravelStatus(String travelStatus, ArrayList<Puzzle> puzzles) {
        if(travelStatus == null) {
            puzzles.add(null);
        }

        if(TravelStatus.BEEN.getValue() == travelStatus) {

        }

        if(TravelStatus.ONGOING.getValue() == travelStatus) {

        }

        if(TravelStatus.PLANNING.getValue() == travelStatus) {

        }
    }

    private void setTopMenu(String travelDiaryId) {
        TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(this);
        TravelDiary travelDiary = travelDiaryDataSource.getTravelDiary(Long.valueOf(travelDiaryId));

        ((TextView) findViewById(R.id.diary_info_title)).setText(travelDiary.getTitle());
//        ImageView viewById = (ImageView) findViewById(R.id.diary_background);
//        viewById.setImageURI();
        travelDiary.getBackgroundImagePath();

        UserDataSource userDataSource = new UserDataSource(this);
        User user = userDataSource.getUser();

        if( user == null ) {
            user = new User(null, "Guest");
        }

        ProfilePictureView profilePictureView = (ProfilePictureView) findViewById(R.id.profile);
        profilePictureView.setProfileId(user.getId());

        TextView userName = (TextView) findViewById(R.id.user_name);
        userName.setText(user.getName());
    }

    private void setSupportActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);
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
