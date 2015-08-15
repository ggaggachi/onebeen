package com.example.hover.onebeen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class CanvasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout = new RelativeLayout(this);


        setContentView(new MyView(this));
    }

    class Circle extends View {

        final int GREEN = Color.argb(255, 0, 204, 204);

        public Circle(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int x = getWidth();
            int y = getHeight();
            int radius;
            radius = 100;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(GREEN);
            canvas.drawCircle(x / 2, y / 2, radius, paint);
        }
    }

    class MyView extends View {

        final int GREEN = Color.argb(255, 0, 204, 204);

        public MyView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            invalidate();
            super.onDraw(canvas);


            float width = (float) getWidth();

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(8);
            paint.setStyle(Paint.Style.STROKE);

            paint.setColor(GREEN);

            canvas.drawLine(0, 160, width/2, 160, paint);
            drawCircle(canvas);
        }

        private void drawCircle(Canvas canvas) {
            int x = getWidth();
            int y = getHeight();
            int radius;
            radius = 100;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(GREEN);
            canvas.drawCircle(x / 2, y / 2, radius, paint);
        }

    }
}
