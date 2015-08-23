package com.example.hover.onebeen;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

        RelativeLayout rootLayout = new RelativeLayout(this);
        RelativeLayout parentLayout = new RelativeLayout(this);
        parentLayout.setMinimumHeight(2500);

        ProfilePictureView profilePictureView = new ProfilePictureView(this);
        parentLayout.addView(profilePictureView);

        setViewRelatedToLine(parentLayout);
        setViewRelatedToCircle(parentLayout);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(parentLayout);
        rootLayout.addView(scrollView);

        setContentView(rootLayout);
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
