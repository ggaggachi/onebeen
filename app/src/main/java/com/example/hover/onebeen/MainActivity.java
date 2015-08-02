package com.example.hover.onebeen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.example.hover.onebeen.db.UserDataSource;
import com.example.hover.onebeen.db.dto.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class MainActivity extends FragmentActivity {

	CallbackManager callbackManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		FacebookSdk.sdkInitialize(this.getApplicationContext());

		callbackManager = CallbackManager.Factory.create();

		registerFacebookLoginButtonEvent();

		setContentView(R.layout.activity_main);
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
		callbackManager.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onResume() {
		super.onResume();

		AppEventsLogger.activateApp(this);
	}
}
