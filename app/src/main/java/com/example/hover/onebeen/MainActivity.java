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
import android.view.MotionEvent;
import android.view.View;

import com.example.hover.onebeen.db.UserDataSource;
import com.example.hover.onebeen.db.dto.TravelStatus;
import com.example.hover.onebeen.db.dto.User;
import com.example.hover.onebeen.diary.MakeDiary;
import com.example.hover.onebeen.diary.TravelDiaryActivity;
import com.example.hover.onebeen.diarylist.TravelDiaryListFragment;
import com.example.hover.onebeen.utility.ActivityStatus;
import com.example.hover.onebeen.utility.BackPressHandler;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {
    String TITLES[] = {"여행 시작하기", "다녀온 여행지", "진행중 여행지", "계획중 여행지", "설정"};
    int ICONS[] = {R.drawable.logo_green, R.drawable.logo_green, R.drawable.logo_green, R.drawable.logo_green, R.drawable.logo_green};

    private Toolbar toolbar;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;

    ActionBarDrawerToggle mDrawerToggle;
    FragmentManager fragmentManager;

    private HomeFragment homeFragment = new HomeFragment();

    private BackPressHandler backPressHandler;
    private boolean isHomeFragment = true;

    public void setIsHomeFragment(boolean isHomeFragment) {
        this.isHomeFragment = isHomeFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.activity_main);

        backPressHandler = new BackPressHandler(this);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);

        //mAdapter = new NavigationAdapter(TITLES, ICONS, getUser());
        mAdapter = new NavigationAdapter(TITLES, ICONS, getUser(), getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        /*추가*/
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                TravelDiaryListFragment travelDiaryListFragment = null;
                Bundle args = null;
                if (child != null) {
                    Drawer.closeDrawers();
                    switch (rv.getChildPosition(child)) {
                        case 1: //여행 시작하기

                            Intent intent = new Intent(getApplicationContext(), MakeDiary.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivityForResult(intent, 1);

                            break;
                        case 2: //다녀온 여행지
                            travelDiaryListFragment = new TravelDiaryListFragment();

                            args = new Bundle();
                            args.putString("travelStatus", TravelStatus.ONGOING.getValue());

                            travelDiaryListFragment.setArguments(args);

                            fragmentManager.beginTransaction()
                                    .setCustomAnimations(R.anim.slide_in_light, R.anim.slide_out_left)
                                    .replace(R.id.container, travelDiaryListFragment)
                                    .commit();
                            break;
                        case 3: //진행중 여행지
                            travelDiaryListFragment = new TravelDiaryListFragment();

                            args = new Bundle();
                            args.putString("travelStatus", TravelStatus.BEEN.getValue());

                            travelDiaryListFragment.setArguments(args);

                            fragmentManager.beginTransaction()
                                    .setCustomAnimations(R.anim.slide_in_light, R.anim.slide_out_left)
                                    .replace(R.id.container, travelDiaryListFragment)
                                    .commit();
                            break;
                        case 4: //계획중 여행지
                            travelDiaryListFragment = new TravelDiaryListFragment();

                            args = new Bundle();
                            args.putString("travelStatus", TravelStatus.PLANNING.getValue());

                            travelDiaryListFragment.setArguments(args);

                            fragmentManager.beginTransaction()
                                    .setCustomAnimations(R.anim.slide_in_light, R.anim.slide_out_left)
                                    .replace(R.id.container, travelDiaryListFragment)
                                    .commit();
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
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

        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.container, homeFragment)
                .commit();
    }

    @NonNull
    private User getUser() {
        UserDataSource userDataSource = new UserDataSource(this);
        User user = userDataSource.getUser();

        if (user == null) {
            user = new User(null, "Guest");
        }
        return user;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("MainActivity", "requestCode:" + requestCode + "/resultCode:" + resultCode + "/data:" + data);

        if (resultCode == ActivityStatus.MAKE_DIARY.getValue()) {
            Intent intent = new Intent(MainActivity.this, TravelDiaryActivity.class);
            intent.putExtra("travelDiaryId", data.getStringExtra("travelDiaryId"));

            Log.e("MainActivityDiaryId", data.getStringExtra("travelDiaryId"));
            startActivityForResult(intent, ActivityStatus.MAIN_ACTIVITY.getValue());
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Drawer.closeDrawers();

        if (!isHomeFragment) {
            isHomeFragment = true;
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.container, homeFragment)
                    .commit();
            return;
        }

        backPressHandler.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);
    }
}
