package com.example.hover.onebeen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.hover.onebeen.utility.OneBeenColor;

public class RelativeStartQuadrangleLayout extends RelativeLayout {
    public RelativeStartQuadrangleLayout(Context context, int ratio) {
        super(context);
        super.addView(new Quadrangle(context, ratio));
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        int width = this.getResources().getDisplayMetrics().widthPixels;
        int height = this.getResources().getDisplayMetrics().heightPixels;

        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(width, height);
        marginLayoutParams.width = width;
        marginLayoutParams.height = height;

        super.setLayoutParams(new LayoutParams(marginLayoutParams));
    }

    class RatioCalculator {

        public float getLeftTopY(Context context, int ratio) {
            int height = context.getResources().getDisplayMetrics().heightPixels;
            int height10 = height / 10;
            return height10 * (Ratio.STANDARD_HEIGHT_RATIO + ratio);
        }

        public float getRightBottomY(Context context, int ratio) {
            int height = context.getResources().getDisplayMetrics().heightPixels;
            int height10 = height / 10;
            return height10 * (Ratio.STANDARD_HEIGHT_RATIO + ratio + 1);
        }
    }

    class Ratio {
        public static final int STANDARD_HEIGHT_RATIO = 2;
    }

    class Quadrangle extends View {
        private final float leftTopX;
        private final float leftTopY;
        private final float rightBottomX;
        private final float rightBottomY;

        public Quadrangle(Context context, int ratio) {
            super(context);

            int width = this.getResources().getDisplayMetrics().widthPixels;
            int width10 = width / 10;
            this.leftTopX = width10;
            this.rightBottomX = width10 * 9;

            int height = this.getResources().getDisplayMetrics().heightPixels;
            int height10 = height / 10;

            this.leftTopY = height10 * (Ratio.STANDARD_HEIGHT_RATIO + ratio);
            this.rightBottomY = height10 * (Ratio.STANDARD_HEIGHT_RATIO + ratio + 1);
        }

        // 위 오른쪽 아래 왼쪽
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(8);
            paint.setStyle(Paint.Style.STROKE);

            paint.setColor(OneBeenColor.GREEN);

            float weightX = rightBottomX - leftTopX;
            float weightY = rightBottomY - leftTopY;
            float underY = 250;

            int width = this.getResources().getDisplayMetrics().widthPixels / 2;

            canvas.drawLine(width, leftTopY, width + weightX, leftTopY, paint);
            canvas.drawLine(width + weightX, leftTopY + weightY, width + weightX, leftTopY + weightY + underY, paint);
            canvas.drawLine(width, leftTopY + weightY, width + weightX, leftTopY + weightY, paint);
            canvas.drawLine(width, leftTopY, width, leftTopY + weightY, paint);
        }
    }
}