package com.example.hover.onebeen;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hover.onebeen.db.TravelDataSource;
import com.example.hover.onebeen.db.dto.Travel;
import com.example.hover.onebeen.db.dto.TravelRequestParam;
import com.ssomai.android.scalablelayout.ScalableLayout;

public class TravelActivity extends FragmentActivity {

    TravelDataSource travelDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScalableLayout scalableLayout = new ScalableLayout(this, 400, 200);

//        oneTestRow(scalableLayout);
        oneFirstRow(scalableLayout, 0);
        oneSecondRow(scalableLayout, 30);
        oneSecondRow(scalableLayout, 60);
        oneSecondRow(scalableLayout, 90);
        oneSecondRow(scalableLayout, 120);
        oneSecondRow(scalableLayout, 150);
        oneSecondRow(scalableLayout, 180);
        oneSecondRow(scalableLayout, 30);

        setContentView(scalableLayout);

        travelDataSource = new TravelDataSource(this);

        Travel travel = travelDataSource.getTravel(null, null, null);
        Log.e("ekdxhrl", travel.toString());

//        setScalableLayout();
//        registerDeleteEvent("1");

//        testInsertAndDelete();
//        registerCancelCheckInEvent();
    }

    private void oneFirstRow(ScalableLayout scalableLayout, int top) {
        addImageView(scalableLayout, TravelArray.X0, 50, 30, top, R.drawable.travel_empty_grid);
        addImageView(scalableLayout, TravelArray.X1, 60, 30, top, R.drawable.test);
        addImageView(scalableLayout, TravelArray.X2, 60, 30, top, R.drawable.test);
        addImageView(scalableLayout, TravelArray.X3, 60, 30, top, R.drawable.test);
        addImageView(scalableLayout, TravelArray.X4, 60, 30, top, R.drawable.test);
        addImageView(scalableLayout, TravelArray.X5, 60, 30, top, R.drawable.test);
        addImageView(scalableLayout, TravelArray.X6, 50, 30, top, R.drawable.travel_empty_grid);
    }

    private void oneSecondRow(ScalableLayout scalableLayout, int top) {
        addImageView(scalableLayout, 0, 50, 30, top, R.drawable.travel_empty_grid);
        addImageView(scalableLayout, 50, 60, 30, top, R.drawable.test);
    }

    class TravelArray {
        final static int STANDARD_X = 60;

        final static int X0 = 0;
        final static int X1 = 50;
        final static int X2 = X1 + STANDARD_X;
        final static int X3 = X2 + STANDARD_X;
        final static int X4 = X3 + STANDARD_X;
        final static int X5 = X4 + STANDARD_X;
        final static int X6 = X5 + STANDARD_X;
        final static int X7 = X6 + STANDARD_X;
    }

    private void addImageView(ScalableLayout scalableLayout, int marginLeft, int width, int height, int top, int imageResource) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageResource);
        scalableLayout.addView(imageView, marginLeft, top, width, height);
    }

    private void oneTestRow(ScalableLayout scalableLayout) {
        int width = 60;
        int height = 30;
        int marginLeft = 50;
        int left = width;

        int startIndex = 0;
        int lastIndex = 6;

        for (int count = startIndex; count <= lastIndex; count++) {
            if (count == startIndex) {
                leftPadding(scalableLayout, height, marginLeft);

                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.test);
                scalableLayout.addView(imageView, marginLeft, 0, width, height);
            } else {
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.test);
                scalableLayout.addView(imageView, left * count, 0, width, height);

                if(count == lastIndex) {
                    Log.e("ekdxhrl", Integer.toString(left * count));
                    rightPadding(scalableLayout, width, height, left, count);
                }
            }
        }
    }

    private void rightPadding(ScalableLayout scalableLayout, int width, int height, int left, int count) {
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.drawable.travel_empty_grid);
        iv2.setBackgroundColor(Color.BLACK);
        scalableLayout.addView(iv2, left * count, 0, width, height);
    }

    private void leftPadding(ScalableLayout scalableLayout, int height, int marginLeft) {
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.travel_empty_grid);
        iv.setBackgroundColor(Color.BLACK);
        scalableLayout.addView(iv, 0, 0, marginLeft, height);
    }

    private void testInsertAndDelete() {
        TravelRequestParam travelRequestParam = new TravelRequestParam(null, "ekdxhrl", "1", "1");
        travelDataSource.insertTravel(travelRequestParam);

        travelDataSource.deletePuzzleInTravel("1");
    }

    private void registerDeleteEvent(String puzzleId) {
        travelDataSource.deletePuzzleInTravel(puzzleId);
    }

    private void setScalableLayout() {
        // Initiate ScalableLayout instance with 400 width and 200 height.
        // It's a relative unit, not pixels or dip.
        ScalableLayout sl = new ScalableLayout(this, 400, 200);

        // Place a TextView instance inside ScalableLayout instance.
        TextView tv = new TextView(this);

        // Placing a TextView with following parameters. left: 20, top: 40, width: 100, height: 30.
        // It will place and scale automatically according to the size of its parent ScalableLayout.
        sl.addView(tv, 20f, 40f, 100f, 30f);

        // Set the text size of TextView as 20. It will scale automatically.
        sl.setScale_TextSize(tv, 20f);

        // All of the original methods of TextView work properly.
        tv.setText("test");
        tv.setBackgroundColor(Color.YELLOW);


        // Place an ImageView instance inside a ScalableLayout instance.
        ImageView iv = new ImageView(this);

        // Placing an ImageView with following parameters. left: 200, top: 30, width: 50, height: 50.
        // It will place and scale automatically according to the size of its parent ScalableLayout.
        sl.addView(iv, 200f, 30f, 50f, 50f);

        // All of the original methods of ImageView work properly, of course.
        iv.setImageResource(R.drawable.first);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
