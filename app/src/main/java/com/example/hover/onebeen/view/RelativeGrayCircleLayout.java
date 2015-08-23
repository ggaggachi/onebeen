package com.example.hover.onebeen.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.hover.onebeen.R;
import com.example.hover.onebeen.puzzle.MakePuzzleActivity;
import com.example.hover.onebeen.utility.CircleDirection;
import com.example.hover.onebeen.utility.CircleSize;
import com.example.hover.onebeen.utility.OneBeenColor;
import com.example.hover.onebeen.utility.Ratio;

/**
 * Created by Dark on 2015. 8. 23..
 */
public class RelativeGrayCircleLayout extends RelativeLayout {
    private final Context context;
    private int marginLeft;
    private int marginTop;
    private int marginRight;
    private int marginBottom;

    public RelativeGrayCircleLayout(final Context context, String order, int ratio, CircleDirection direction) {
        super(context);
        this.context = context;

        initialize(context, ratio, direction);

        super.addView(new GrayCircle(context));
        super.setTag(order);
        super.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowPopupMenu(v);
            }
        });
    }

    private void initialize(Context context, int ratio, CircleDirection direction) {
        int width = Ratio.getWidth(context);
        int height = Ratio.getHeight(context);

        if(direction == CircleDirection.LEFT) {
            this.marginLeft = width * 2;
            this.marginTop = height * (Ratio.STANDARD_HEIGHT_RATIO + ratio) - CircleSize.getHalfWidth();
            this.marginRight = 0;
            this.marginBottom = 0;
        } else if(direction == CircleDirection.CENTER){
            this.marginLeft = width * 5 - CircleSize.getHalfWidth();
            this.marginTop = height * (Ratio.STANDARD_HEIGHT_RATIO + ratio);
            this.marginRight = 0;
            this.marginBottom = 0;
        } else {
            this.marginLeft = width * 7;
            this.marginTop = height * (Ratio.STANDARD_HEIGHT_RATIO + ratio) - CircleSize.getHalfWidth();
            this.marginRight = 0;
            this.marginBottom = 0;
        }
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        int width = this.getResources().getDisplayMetrics().widthPixels;
        int height = this.getResources().getDisplayMetrics().heightPixels;

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(width, height);
        marginLayoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        marginLayoutParams.width = 100;
        marginLayoutParams.height = 100;

        super.setLayoutParams(new RelativeLayout.LayoutParams(marginLayoutParams));
    }

    private void onShowPopupMenu(final View v) {
        PopupMenu popup = new PopupMenu(context, v);
        Toast.makeText(context, (String) v.getTag(), Toast.LENGTH_LONG).show();

        popup.getMenuInflater().inflate(R.menu.menu_canvas, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_puzzle_menu:
                        Intent intent = new Intent(context, MakePuzzleActivity.class);
                        intent.putExtra("order", (String) v.getTag());
                        context.startActivity(intent);
                    case R.id.cancel_checkin_menu:
                        Toast.makeText(context, "Hide!", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.delete_menu:
                        Toast.makeText(context, "Hide!", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popup.show();
    }
}

class GrayCircle extends View {

    public GrayCircle(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int radius = 50;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(OneBeenColor.GRAY);

        canvas.drawCircle(CircleSize.getWidth() / 2, CircleSize.getHeight() / 2, radius, paint);

        super.onDraw(canvas);
    }
}