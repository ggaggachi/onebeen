package com.example.hover.onebeen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.hover.onebeen.db.TravelDataSource;
import com.example.hover.onebeen.db.dto.Travel;
import com.example.hover.onebeen.utility.CircleDirection;
import com.example.hover.onebeen.view.RelativeCircleDescriptionLayout;
import com.example.hover.onebeen.view.RelativeGrayCircleLayout;
import com.example.hover.onebeen.view.RelativeGreenCircleLayout;
import com.example.hover.onebeen.view.RelativeQuadrangleLayout;
import com.example.hover.onebeen.view.RelativeStartQuadrangleLayout;
import com.facebook.login.widget.ProfilePictureView;

public class CanvasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TravelDataSource travelDataSource = new TravelDataSource(this);
//        Travel travel = travelDataSource.getTravel("1");

        RelativeLayout rootLayout = new RelativeLayout(this);
        RelativeLayout parentLayout = new RelativeLayout(this);
        parentLayout.setMinimumHeight(2500);

        setViewRelatedToProfile(parentLayout);
        setViewRelatedToLine(parentLayout);
        setViewRelatedToCircle(parentLayout);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(parentLayout);
        rootLayout.addView(scrollView);

        setContentView(rootLayout);
    }

    private void setViewRelatedToProfile(RelativeLayout parentLayout) {
        ProfilePictureView profilePictureView = new ProfilePictureView(this);
        LinearLayout profileLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        profileLayout.setLayoutParams(params);
        profileLayout.setGravity(Gravity.CENTER);
        profileLayout.addView(profilePictureView);
        parentLayout.addView(profileLayout);
    }

    private void setViewRelatedToCircle(RelativeLayout parentLayout) {
        parentLayout.addView(new RelativeGreenCircleLayout(this, "1", 1, CircleDirection.CENTER));
        parentLayout.addView(new RelativeGreenCircleLayout(this, "2", 3, CircleDirection.RIGHT));
        parentLayout.addView(new RelativeCircleDescriptionLayout(this, 3, CircleDirection.RIGHT, "광안리 해수욕.."));

        int ratio = 5;
        int startCount = 3;
        int endCount = 7;

        while(true) {
            parentLayout.addView(new RelativeGrayCircleLayout(this, "" + startCount, ratio, CircleDirection.LEFT));
            parentLayout.addView(new RelativeGrayCircleLayout(this, "" + startCount, ratio, CircleDirection.RIGHT));
            parentLayout.addView(new RelativeCircleDescriptionLayout(this, ratio, CircleDirection.RIGHT, "광안리 해수욕.."));

            startCount++;
            ratio += 2;

            if(startCount == endCount) {
                break;
            }
        }
    }

    private void setViewRelatedToLine(RelativeLayout parentLayout) {
        parentLayout.addView(new RelativeStartQuadrangleLayout(this, 1));
        parentLayout.addView(new RelativeQuadrangleLayout(this, 5));
        parentLayout.addView(new RelativeQuadrangleLayout(this, 9));
    }
}
