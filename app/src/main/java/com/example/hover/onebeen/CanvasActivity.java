package com.example.hover.onebeen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import com.example.hover.onebeen.utility.OneBeenColor;
import com.example.hover.onebeen.view.RelativeQuadrangleLayout;

public class CanvasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout a = new RelativeLayout(this);
        RelativeLayout parentLayout = new RelativeLayout(this);
//        parentLayout.setBackgroundColor(Color.BLACK);

//        relativeLayout.addView(new TT(this));
        int width = this.getResources().getDisplayMetrics().widthPixels;
        int height = this.getResources().getDisplayMetrics().heightPixels;
        int marginLeft = width / 2;
        int marginTop = height / 2;
        int marginRight = 0;
        int marginBottom = 0;

        //        childLayout1.addView(circle);
        parentLayout.addView(new RelativeQuadrangleLayout(this, 1));
        parentLayout.addView(new RelativeQuadrangleLayout(this, 3));
        parentLayout.addView(new RelativeQuadrangleLayout(this, 5));
        parentLayout.addView(new RelativeChildLayout(this, marginLeft, marginTop, marginRight, marginBottom));
        parentLayout.addView(new DashVerticalLine(this));


//        relativeLayout.addView(new HalfCircle(this));
//        relativeLayout.addView(new VerticalLine(this, 0, 160, width / 2, 160));
//        relativeLayout.addView(new VerticalLine(this, width / 2, 155, width / 2, 320));

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(parentLayout);
        a.addView(scrollView);
        setContentView(a);
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

    class RelativeChildLayout extends RelativeLayout {
        private int marginLeft;
        private int marginTop;
        private int marginRight;
        private int marginBottom;

        public RelativeChildLayout(Context context, int marginLeft, int marginTop, int marginRight, int marginBottom) {
            super(context);

            this.marginLeft = marginLeft;
            this.marginRight = marginRight;
            this.marginTop = marginTop;
            this.marginBottom = marginBottom;

            Circle circle = new Circle(context);
//        circle.setBackgroundColor(Color.BLACK);
            circle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CanvasActivity.this, "test", Toast.LENGTH_LONG).show();
                }
            });

            super.addView(circle);
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
            super.setBackgroundColor(Color.BLACK);
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
//
//        @Override
//        protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//            setMeasuredDimension(100, 100);
//        }
    }

    class HalfCircle extends View {

        public HalfCircle(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            RectF rect = new RectF();

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(8);
            paint.setStyle(Paint.Style.STROKE);

            // 부채꼴 그리기
            rect.set(30, 270, 210, 450);
            canvas.drawArc(rect, 0, 150, true, paint);
        }
    }

    class TT extends View {
        private Paint mPaints;
        private Paint mFramePaint;
        private boolean mUseCenters;
        private RectF mOvals;
        private RectF mFrameOvals;
        private float mStart;
        private float mSweep;
        public TT(Context context) {
            super(context);
            mPaints = new Paint();
            mPaints.setAntiAlias(true);
            mPaints.setStyle(Paint.Style.STROKE);
            mPaints.setStrokeWidth(20);
            mPaints.setColor(0x88B2EBF4);
            mUseCenters = false;

            mFramePaint = new Paint();
            mFramePaint.setAntiAlias(true);
            mFramePaint.setStyle(Paint.Style.STROKE);
            mFramePaint.setStrokeWidth(10);
            mFramePaint.setColor(0xFFEAEAEA);

            mOvals = new RectF();
            mFrameOvals = new RectF();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            mStart = 0.0f;        // Arc start 0.0f는 원점(3시 방향 기준) 시작
            mSweep = 180;        // Arc Sweep는 시계 방향으로 Angle 180 회전
            mOvals.set(50, 50, 250, 250);
            mFrameOvals.set(65, 65, 235, 235);

            canvas.drawOval(mFrameOvals, mFramePaint);
            canvas.drawArc(mOvals, mStart, mSweep,
                    mUseCenters, mPaints);

            mStart = 0.0f;
            mSweep = -180;        // Arc Sweep 반시계 방향으로  Angle 180도 회전
            mOvals.set(300, 50, 500, 250);
            mFrameOvals.set(315, 65, 485, 235);

            canvas.drawOval(mFrameOvals, mFramePaint);
            canvas.drawArc(mOvals, mStart, mSweep,
                    mUseCenters, mPaints);

            mStart = -90.0f;       // Arc start -90 회전된 방향(12시 방향)에서 시작
            mSweep = 270;        // Arc Sweep 시계 방향으로  Angle 270도 회전
            mOvals.set(50, 300, 250, 500);
            mFrameOvals.set(65, 315, 235, 485);

            canvas.drawOval(mFrameOvals, mFramePaint);
            canvas.drawArc(mOvals, mStart, mSweep,
                    mUseCenters, mPaints);

            mStart = -90.f;
            mSweep = -270;        // Arc Sweep 반시계 방향으로  Angle 270도 회전
            mOvals.set(300, 300, 500, 500);
            mFrameOvals.set(315, 315, 485, 485);

            canvas.drawOval(mFrameOvals, mFramePaint);
            canvas.drawArc(mOvals, mStart, mSweep,
                    mUseCenters, mPaints);

            mStart = -90.0f;
            mSweep = 270;
            mOvals.set(50, 550, 250, 750);
            mFrameOvals.set(65, 565, 235, 735);
            mUseCenters = true;       // boolean useCenter = true 시 Arc 중심을 잇는 선분이 생김

            canvas.drawOval(mFrameOvals, mFramePaint);
            canvas.drawArc(mOvals, mStart, mSweep,
                    mUseCenters, mPaints);
        }
    }
}
