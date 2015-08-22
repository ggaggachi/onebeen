package com.example.hover.onebeen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.hover.onebeen.utility.CircleDirection;
import com.example.hover.onebeen.utility.CircleSize;
import com.example.hover.onebeen.utility.OneBeenColor;
import com.example.hover.onebeen.utility.Ratio;

public class RelativeCircleLayout extends RelativeLayout {
    private int marginLeft;
    private int marginTop;
    private int marginRight;
    private int marginBottom;

    public RelativeCircleLayout(Context context, int ratio, CircleDirection direction) {
        super(context);

        initialize(context, ratio, direction);

        super.addView(new Circle(context));
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

    class Circle extends View {
        public Circle(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int radius = 50;

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(OneBeenColor.GREEN);

            canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
        }
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        int width = this.getResources().getDisplayMetrics().widthPixels;
        int height = this.getResources().getDisplayMetrics().heightPixels;

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(width, height);
        marginLayoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        marginLayoutParams.width = CircleSize.getWidth();
        marginLayoutParams.height = CircleSize.getHeight();

        super.setLayoutParams(new RelativeLayout.LayoutParams(marginLayoutParams));
    }
}
