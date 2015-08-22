package com.example.hover.onebeen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import com.example.hover.onebeen.utility.CircleDirection;
import com.example.hover.onebeen.view.RelativeCircleDescriptionLayout;
import com.example.hover.onebeen.view.RelativeCircleLayout;
import com.example.hover.onebeen.view.RelativeQuadrangleLayout;
import com.example.hover.onebeen.view.RelativeStartQuadrangleLayout;
import com.facebook.login.widget.ProfilePictureView;

public class CanvasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout rootLayout = new RelativeLayout(this);
        RelativeLayout parentLayout = new RelativeLayout(this);
        parentLayout.setMinimumHeight(2500);

        ProfilePictureView profilePictureView = new ProfilePictureView(this);
        parentLayout.addView(profilePictureView);

        setViewRelatedToLine(parentLayout);
        setViewRelatedToCircle(parentLayout);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(parentLayout);
        rootLayout.addView(scrollView);

        setContentView(rootLayout);
    }

    private void setViewRelatedToCircle(RelativeLayout parentLayout) {
        parentLayout.addView(new RelativeCircleLayout(this, 1, CircleDirection.CENTER));
        parentLayout.addView(new RelativeCircleLayout(this, 3, CircleDirection.RIGHT));
        parentLayout.addView(new RelativeCircleDescriptionLayout(this, 3, CircleDirection.RIGHT, "광안리 해수욕.."));

        RelativeCircleLayout circle = new RelativeCircleLayout(this, 5, CircleDirection.LEFT);

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CanvasActivity.this, "test", Toast.LENGTH_LONG).show();
            }
        });
        parentLayout.addView(circle);
        parentLayout.addView(new RelativeCircleLayout(this, 5, CircleDirection.RIGHT));
        parentLayout.addView(new RelativeCircleDescriptionLayout(this, 5, CircleDirection.RIGHT, "광안리 해수욕.."));
        parentLayout.addView(new RelativeCircleLayout(this, 7, CircleDirection.LEFT));
        parentLayout.addView(new RelativeCircleLayout(this, 7, CircleDirection.RIGHT));
        parentLayout.addView(new RelativeCircleLayout(this, 9, CircleDirection.LEFT));
        parentLayout.addView(new RelativeCircleLayout(this, 13, CircleDirection.LEFT));
    }

    private void setViewRelatedToLine(RelativeLayout parentLayout) {
        parentLayout.addView(new RelativeStartQuadrangleLayout(this, 1));
        parentLayout.addView(new RelativeQuadrangleLayout(this, 5));
        parentLayout.addView(new RelativeQuadrangleLayout(this, 9));
        parentLayout.addView(new RelativeQuadrangleLayout(this, 13));
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
