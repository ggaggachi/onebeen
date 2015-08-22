package com.example.hover.onebeen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.hover.onebeen.utility.OneBeenColor;
import com.example.hover.onebeen.utility.Ratio;

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

    class Quadrangle extends View {
        private final float height10;
        private final float leftTopX;
        private final float leftTopY;
        private final float rightBottomX;
        private final float rightBottomY;

        public Quadrangle(Context context, int ratio) {
            super(context);

            int width10 = Ratio.getWidth(context);

            this.leftTopX = width10;
            this.rightBottomX = width10 * 5;

            this.height10 = Ratio.getHeight(context);

            this.leftTopY = this.height10 * (Ratio.STANDARD_HEIGHT_RATIO + ratio);
            this.rightBottomY = this.height10 * (Ratio.STANDARD_HEIGHT_RATIO + ratio + 1);
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
            float weightY = rightBottomY - leftTopY + this.height10;

            int halfWidth = this.getResources().getDisplayMetrics().widthPixels / 2;

            canvas.drawLine(halfWidth + weightX, leftTopY + weightY, halfWidth + weightX, leftTopY + weightY * 2, paint);
            canvas.drawLine(halfWidth, leftTopY + weightY, halfWidth + weightX, leftTopY + weightY, paint);
            canvas.drawLine(halfWidth, leftTopY, halfWidth, leftTopY + weightY, paint);
        }
    }
}