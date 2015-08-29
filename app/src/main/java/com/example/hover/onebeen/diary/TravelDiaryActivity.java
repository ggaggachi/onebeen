package com.example.hover.onebeen.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.example.hover.onebeen.diarylist.TravelDiaryListFragment;
import com.example.hover.onebeen.puzzle.AddPuzzleActivity;
import com.example.hover.onebeen.puzzle.SavePuzzleActivity;
import com.example.hover.onebeen.puzzle.ShowPuzzleActivity;
import com.example.hover.onebeen.utility.ActivityStatus;
import com.example.hover.onebeen.utility.Time;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.ProfilePictureView;
import java.util.ArrayList;

public class TravelDiaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        Log.e("TravelDiaryActivity", "onCreate");

        setContentView(R.layout.activity_diary);
        setSupportActionBar();

        Intent intent = getIntent();
        final String travelDiaryId = intent.getExtras().getString("travelDiaryId");
        final String travelStatus = intent.getExtras().getString("travelStatus");

        Log.e("TravelDiaryActivity", "travelDiaryId:" + travelDiaryId);
        Log.e("TravelDiaryActivity", "travelStatus:" + travelStatus);

        setTopMenu(travelDiaryId);
        setBottomMenu(travelDiaryId);

        PuzzleDataSource puzzleDataSource = new PuzzleDataSource(this);

        final ArrayList<Puzzle> puzzles = puzzleDataSource.getPuzzles(Long.valueOf(travelDiaryId));

        Log.e("TravelDiaryActivity", "puzzles:" + puzzles);

        convertToViewAccodingToTravelStatus(travelStatus, puzzles);

//        ArrayList<Puzzle> puzzles = new ArrayList<>();
//        puzzles.add(new Puzzle(1, null, PuzzleStatus.BEEN.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기1", null));
//        puzzles.add(new Puzzle(1, null, PuzzleStatus.BEEN.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기2", null));
//        puzzles.add(new Puzzle(1, null, PuzzleStatus.CURRENT.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기3", null));
//        puzzles.add(new Puzzle(1, null, PuzzleStatus.WANT.toString(), null, null, null, null, null, 1, "해운대", "바나나보트 타기4", null));
//
//        puzzles.add(null);

        progressBarEvent(puzzles);

        Log.e("TravelDiaryActivity", "converted puzzles:" + puzzles.toString());

        listViewEvent(travelDiaryId, travelStatus, puzzles);
    }

    private void setBottomMenu(String travelDiaryId) {
        TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(this);
        TravelDiary travelDiary = travelDiaryDataSource.getTravelDiary(Long.valueOf(travelDiaryId));

        if (TravelStatus.ONGOING.equals(travelDiary.getTravelStatus())) {
            Button button = (Button) findViewById(R.id.register_travel_diary);
            button.setText("여행 완료");
        } else if (TravelStatus.PLANNING.equals(travelDiary.getTravelStatus())){
            Button button = (Button) findViewById(R.id.register_travel_diary);
            button.setText("시작");
        }
    }

    private void listViewEvent(final String travelDiaryId, final String travelStatus, ArrayList<Puzzle> puzzles) {
        ListView listView = (ListView) findViewById(R.id.puzzle_list_view);
        listView.setAdapter(new PuzzleListViewAdapter(getLayoutInflater(), R.layout.puzzle_item, puzzles));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tag = String.valueOf(view.getTag());
                String puzzleId = String.valueOf(view.getTag("puzzleId".hashCode()));

                Log.e("TravelDiaryActivity", "puzzledId:" + puzzleId);
                Log.e("TravelDiaryActivity", "tag:"+tag);
                if(TravelStatus.PLANNING.getValue().equals(travelStatus)) {
                   return;
                }

                if ("CURRENT".equals(tag)) {
//                    startActivity(new Intent(TravelDiaryActivity.this, ShowPuzzleActivity.class));
                } else if ("BEEN".equals(tag)) {
                    if (TravelStatus.ONGOING.getValue().equals(travelStatus)) {
                        Intent addPuzzleIntent = new Intent(TravelDiaryActivity.this, SavePuzzleActivity.class);
                        addPuzzleIntent.putExtra("puzzleId", puzzleId);
//                        addPuzzleIntent.putExtra("travelStatus", travelStatus);
                        startActivity(addPuzzleIntent);
                    } else {
                        Intent intent = new Intent(TravelDiaryActivity.this, ShowPuzzleActivity.class);
                        intent.putExtra("id", puzzleId);
                        startActivity(intent);
                    }

                    finish();
                } else if ("WANT".equals(tag)) {
                    Intent addPuzzleIntent = new Intent(TravelDiaryActivity.this, SavePuzzleActivity.class);
                    addPuzzleIntent.putExtra("puzzleId", puzzleId);
                    startActivity(addPuzzleIntent);

                    finish();
                } else {
                    Intent addPuzzleIntent = new Intent(TravelDiaryActivity.this, AddPuzzleActivity.class);
                    addPuzzleIntent.putExtra("travelDiaryId", travelDiaryId);
                    startActivity(addPuzzleIntent);
                    finish();
                }
            }
        });

        ((Button) findViewById(R.id.register_travel_diary)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TravelDiaryActivity", "RegisterTravelDiary");

                if (TravelStatus.PLANNING.getValue().equals(travelStatus)) {
                    Log.e("TravelDiaryActivity", "PLANNING TravelDiaryListFragment Event");

                    TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(TravelDiaryActivity.this);
                    TravelDiary travelDiary = travelDiaryDataSource.getTravelDiary(Long.valueOf(travelDiaryId));
                    travelDiary.setTravelStatus(TravelStatus.ONGOING);
                    travelDiary.setStartDate(Time.now());

                    Log.e("SavePuzzleActivity", "업데이트할 TravelDiary 가져오기 : " + travelDiary.toString());

                    travelDiaryDataSource.updateTravelDiary(travelDiary);
                } else if (TravelStatus.ONGOING.getValue().equals(travelStatus)) {
                    Log.e("TravelDiaryActivity", "ONGOING TravelDiaryListFragment Event");

                    TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(TravelDiaryActivity.this);
                    TravelDiary travelDiary = travelDiaryDataSource.getTravelDiary(Long.valueOf(travelDiaryId));
                    travelDiary.setTravelStatus(TravelStatus.BEEN);
                    travelDiary.setEndDate(Time.now());

                    Log.e("SavePuzzleActivity", "업데이트할 TravelDiary 가져오기 : " + travelDiary.toString());

                    travelDiaryDataSource.updateTravelDiary(travelDiary);
                }

                Intent intent = new Intent(TravelDiaryActivity.this, MainActivity.class);
                startActivityForResult(intent, ActivityStatus.TravelDiaryActivity.getValue());
            }
        });
    }

    private void progressBarEvent(ArrayList<Puzzle> puzzles) {
        if (puzzles.size() == 0) {
            Log.e("TravelDiaryActivity", "puzzles가 없는 경우, 즉 처음에 여행 시작하기를 통하여 들어온 경");
            TextView progressText = (TextView) findViewById(R.id.progress_percent);
            progressText.setText("0%");
            return;
        }

        Log.e("TravelDiaryActivity", "progressBarEvent");
        Log.e("TravelDiaryActivity", "puzzles:" + puzzles.toString());

        int beenSize = 0;

        for (Puzzle puzzle : puzzles) {
            if(puzzle != null && "BEEN".equals(puzzle.getStatus())) {
                beenSize++;
            }
        }

        if (beenSize == 0) {
            TextView progressText = (TextView) findViewById(R.id.progress_percent);
            progressText.setText("0%");
            return;
        }

        Log.e("TravelDiaryActivity", "Percent 계산 시작");

        int percent = (int) ((beenSize * 100) / puzzles.size());

        TextView progressText = (TextView) findViewById(R.id.progress_percent);
        progressText.setText(String.valueOf(percent));

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(percent);

        Log.e("TravelDiaryActivity", "계산된 Percent:" + percent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Log.e("TravelDiaryActivity", "requestCode:"+requestCode+"/resultCode:"+resultCode+"/data:"+data);
    }

    private void convertToViewAccodingToTravelStatus(String travelStatus, ArrayList<Puzzle> puzzles) {
        if(travelStatus == null) {
            puzzles.add(null);
        }

        if(TravelStatus.BEEN.getValue() == travelStatus) {
            for (Puzzle puzzle : puzzles) {

            }
        }

        if(TravelStatus.ONGOING.getValue() == travelStatus) {
            for (Puzzle puzzle : puzzles) {

            }
        }

        if(TravelStatus.PLANNING.getValue() == travelStatus) {
            for (Puzzle puzzle : puzzles) {

            }
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

        ((ProfilePictureView) findViewById(R.id.profile)).setProfileId(user.getId());
        ((TextView) findViewById(R.id.user_name)).setText(user.getName());
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
