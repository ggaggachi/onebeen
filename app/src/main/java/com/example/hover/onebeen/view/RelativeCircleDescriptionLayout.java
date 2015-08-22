package com.example.hover.onebeen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.hover.onebeen.utility.CircleDirection;
import com.example.hover.onebeen.utility.CircleSize;
import com.example.hover.onebeen.utility.Ratio;

public class RelativeCircleDescriptionLayout extends RelativeLayout {
    private int marginLeft;
    private int marginTop;
    private int marginRight;
    private int marginBottom;

    public RelativeCircleDescriptionLayout(Context context, int ratio, CircleDirection direction, String description) {
        super(context);

        initialize(context, ratio, direction);

        super.addView(new CircleDescription(context, description));
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
        int leftWeight = marginLeft / 10;
        int topWeight = marginTop / 10;
        marginLayoutParams.setMargins(marginLeft - leftWeight, marginTop + (topWeight * 2), marginRight, marginBottom);
        marginLayoutParams.width = 200;
        marginLayoutParams.height = 150;

        super.setLayoutParams(new RelativeLayout.LayoutParams(marginLayoutParams));
    }
}

class CircleDescription extends View {

    private final String description;

    public CircleDescription(Context context, String description) {
        super(context);
        this.description = description;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint textPaint = new Paint();
        textPaint.setTextSize(30);
        textPaint.setColor(Color.BLACK);

        canvas.drawText(description, 0, 30, textPaint);

        super.onDraw(canvas);
    }
}
