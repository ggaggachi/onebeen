package com.example.hover.onebeen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import com.example.hover.onebeen.puzzle.MakePuzzleActivity;
import com.example.hover.onebeen.utility.CircleDirection;
import com.example.hover.onebeen.view.RelativeCircleDescriptionLayout;
import com.example.hover.onebeen.view.RelativeCircleLayout;
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
        parentLayout.addView(new RelativeCircleLayout(this, "1", 1, CircleDirection.CENTER));
        parentLayout.addView(new RelativeCircleLayout(this, "2", 3, CircleDirection.RIGHT));
        parentLayout.addView(new RelativeCircleDescriptionLayout(this, 3, CircleDirection.RIGHT, "광안리 해수욕.."));
        parentLayout.addView(new RelativeCircleLayout(this, "3", 5, CircleDirection.LEFT));
        parentLayout.addView(new RelativeCircleLayout(this, "4", 5, CircleDirection.RIGHT));
        parentLayout.addView(new RelativeCircleDescriptionLayout(this, 5, CircleDirection.RIGHT, "광안리 해수욕.."));
        parentLayout.addView(new RelativeCircleLayout(this, "5", 7, CircleDirection.LEFT));
        parentLayout.addView(new RelativeCircleLayout(this, "6", 7, CircleDirection.RIGHT));
        parentLayout.addView(new RelativeCircleLayout(this, "7", 9, CircleDirection.LEFT));
        parentLayout.addView(new RelativeCircleLayout(this, "8", 13, CircleDirection.LEFT));
    }

    private void setViewRelatedToLine(RelativeLayout parentLayout) {
        parentLayout.addView(new RelativeStartQuadrangleLayout(this, 1));
        parentLayout.addView(new RelativeQuadrangleLayout(this, 5));
        parentLayout.addView(new RelativeQuadrangleLayout(this, 9));
        parentLayout.addView(new RelativeQuadrangleLayout(this, 13));
    }
}
