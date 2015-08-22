package com.example.hover.onebeen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.hover.onebeen.utility.OneBeenColor;
import com.example.hover.onebeen.utility.Ratio;

public class RelativeDashQuadrangleLayout extends RelativeLayout {
    public RelativeDashQuadrangleLayout(Context context, int ratio) {
        super(context);
        super.addView(new Quadrangle(context, ratio));
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        int width = this.getResources().getDisplayMetrics().widthPixels;
        int height = this.getResources().getDisplayMetrics().heightPixels;

        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(width, height);
        marginLayoutParams.width = width;
        marginLayoutParams.height = 2500;

        super.setLayoutParams(new LayoutParams(marginLayoutParams));
    }

    class Quadrangle extends View {
        private final float leftTopX;
        private final float leftTopY;
        private final float height10;
        private final float rightBottomX;
        private final float rightBottomY;

        public Quadrangle(Context context, int ratio) {
            super(context);

            int width10 = Ratio.getWidth(context);
            this.leftTopX = width10;
            this.rightBottomX = width10 * 9;

            this.height10 = Ratio.getHeight(context);

            this.leftTopY = this.height10 * (Ratio.STANDARD_HEIGHT_RATIO + ratio);
            this.rightBottomY = this.height10 * (Ratio.STANDARD_HEIGHT_RATIO + ratio + 1);
        }

        // 위 오른쪽 아래 왼쪽
        // 가중치(float weightY = rightBottomY - leftTopY + this.height10;)를
        // 이용하여 각각의 Height값을 조정한다.
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(8);
            paint.setStyle(Paint.Style.STROKE);

            paint.setColor(OneBeenColor.GREEN);

            float weightX = rightBottomX - leftTopX;
            float weightY = rightBottomY - leftTopY + this.height10;

            canvas.drawLine(leftTopX, leftTopY, leftTopX + weightX, leftTopY, paint);
            canvas.drawLine(leftTopX + weightX, leftTopY + weightY, leftTopX + weightX, leftTopY + weightY * 2, paint);
            canvas.drawLine(leftTopX, leftTopY + weightY, leftTopX + weightX, leftTopY + weightY, paint);
            canvas.drawLine(leftTopX, leftTopY, leftTopX, leftTopY + weightY, paint);
        }
    }
}

class DashVerticalLine extends View {
    private final int GREEN = Color.argb(255, 0, 204, 204);

    public DashVerticalLine(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(GREEN);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));

        Path path = new Path();
        path.moveTo(0, 250);
        path.quadTo(250, 250, 500, 250);
        canvas.drawPath(path, paint);
    }
}