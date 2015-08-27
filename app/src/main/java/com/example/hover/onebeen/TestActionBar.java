package com.example.hover.onebeen;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hover.onebeen.diary.TravelDiaryFragment;

public class TestActionBar extends AppCompatActivity {
    String TITLES[] = {"여행 시작하기", "다녀온 여행지", "진행중 여행지", "계획중 여행지", "설정"};
    int ICONS[] = {R.drawable.logo_green, R.drawable.logo_green, R.drawable.logo_green, R.drawable.logo_green, R.drawable.logo_green};

    String NAME = "이름";
    String EMAIL = "이메일";
    int PROFILE = R.drawable.logo_green;

    private Toolbar toolbar;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;

    ActionBarDrawerToggle mDrawerToggle;
    FragmentManager fragmentManager;

    private final HomeFragment homeFragment = new HomeFragment();
    private TravelDiaryFragment travelDiaryFragment = new TravelDiaryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbar_activity);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Drawer.closeDrawers();
    }
}
