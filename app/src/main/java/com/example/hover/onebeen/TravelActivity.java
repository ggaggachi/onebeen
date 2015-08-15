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
import com.ssomai.android.scalablelayout.ScalableLayout;

public class TravelActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setScalableLayout();
        registerDeleteEvent();

        TravelDataSource travelDataSource = new TravelDataSource(this);
        Travel travel = travelDataSource.getTravel(null, null, null);
        Log.e("ekdxhrl", travel.toString());
//        registerAddPuzzleEvent();
//        registerCancelCheckInEvent();
    }

    private void registerDeleteEvent() {

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
