package com.example.hover.onebeen;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.hover.onebeen.db.UserDataSource;
import com.example.hover.onebeen.db.dto.User;
import com.example.hover.onebeen.diarylist.TravelDiaryListFragment;
import com.example.hover.onebeen.utility.ActivityStatus;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager = null;

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
    private TravelDiaryListFragment travelDiaryListFragment = new TravelDiaryListFragment();

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

//		FacebookSdk.sdkInitialize(this.getApplicationContext());
//		callbackManager = CallbackManager.Factory.create();
//		registerFacebookLoginButtonEvent();
    }

    private void registerFacebookLoginButtonEvent() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        insertUserInformation();

                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "Cancel!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.e("ekdxhrl", exception.getMessage());
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void insertUserInformation() {
        UserDataSource userDataSource = new UserDataSource(getApplicationContext());

        Profile profile = Profile.getCurrentProfile();

        String fullName = profile.getLastName() + profile.getFirstName();

        userDataSource.insertUser(new User(profile.getId(), fullName));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ActivityStatus.MAKE_DIARY.getActivityStatus()) {
            fragmentManager.beginTransaction().replace(R.id.container, travelDiaryListFragment).commit();
        }
    }

//	@Override
//	protected void onResume() {
//		super.onResume();
//
//		AppEventsLogger.activateApp(this);
//	}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Drawer.closeDrawers();
    }
}
