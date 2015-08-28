package com.example.hover.onebeen;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hover.onebeen.db.UserDataSource;
import com.example.hover.onebeen.db.dto.User;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.ProfilePictureView;

public class ProfileActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.activity_profile);

        User user = new UserDataSource(getApplicationContext()).getUser();

        ((ProfilePictureView) findViewById(R.id.facebook_profile_image)).setProfileId(user.getId());
        ((TextView) findViewById(R.id.user_name)).setText(user.getName());
    }
}
