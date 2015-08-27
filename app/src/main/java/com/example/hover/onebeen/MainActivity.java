package com.example.hover.onebeen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.hover.onebeen.db.TravelDiaryDataSource;
import com.example.hover.onebeen.db.UserDataSource;
import com.example.hover.onebeen.db.dto.TravelDiary;
import com.example.hover.onebeen.db.dto.TravelStatus;
import com.example.hover.onebeen.db.dto.User;
import com.example.hover.onebeen.diarylist.TravelDiaryListFragment;
import com.example.hover.onebeen.utility.ActivityStatus;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class MainActivity extends AppCompatActivity {
    String TITLES[] = {"여행 시작하기", "다녀온 여행지", "진행중 여행지", "계획중 여행지", "설정"};
    int ICONS[] = {R.drawable.logo_green, R.drawable.logo_green, R.drawable.logo_green, R.drawable.logo_green, R.drawable.logo_green};

    String NAME = "이름";
    String EMAIL = "이메일";
    int PROFILE = R.drawable.default_profile;

    private Toolbar toolbar;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;

    ActionBarDrawerToggle mDrawerToggle;
    FragmentManager fragmentManager;

    private HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.actionbar_activity);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyAdapter(TITLES, ICONS, getUser());
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }
        };

        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, homeFragment).commit();

        insertMockData();
    }

    @NonNull
    private User getUser() {
        UserDataSource userDataSource = new UserDataSource(this);
        User user = userDataSource.getUser();

        if(user == null) {
            user = new User(null, "Guest");
        }
        return user;
    }

    private void insertMockData() {
        TravelDiaryDataSource travelDiaryDataSource = new TravelDiaryDataSource(this);

        try {
            TravelDiary travelDiary1 = travelDiaryDataSource.getTravelDiary(1L);
        } catch(Exception e) {
            for (int i = 0; i < 10; i++) {
                TravelDiary travelDiary = new TravelDiary(null, "title" + i, "2015.08.25", "2015.08.30", TravelStatus.BEEN, "");
                travelDiaryDataSource.insertTravelDiary(travelDiary);
            }

            for (int i = 0; i < 10; i++) {
                TravelDiary travelDiary = new TravelDiary(null, "title" + i, "2015.08.25", "2015.08.30", TravelStatus.ONGOING, "");
                travelDiaryDataSource.insertTravelDiary(travelDiary);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ActivityStatus.MAKE_DIARY.getActivityStatus()) {
            moveToTravelDiaryListFragment(data);
        }
    }

    private void moveToTravelDiaryListFragment(Intent data) {
        TravelDiaryListFragment travelDiaryListFragment = new TravelDiaryListFragment();
        Bundle args = new Bundle();
        args.putString("travelDiaryId", data.getStringExtra("travelDiaryId"));
        travelDiaryListFragment.setArguments(args);
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, travelDiaryListFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Drawer.closeDrawers();
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);
    }
}
