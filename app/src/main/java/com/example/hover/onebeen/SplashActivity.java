package com.example.hover.onebeen;

/**
 * Created by seojin on 2015-07-28.
 */

    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.os.Handler;
    import android.util.Log;
    import android.view.Window;
    import android.view.WindowManager;
    import android.view.animation.Animation;
    import android.view.animation.LinearInterpolator;
    import android.view.animation.RotateAnimation;
    import android.widget.ImageView;

    import com.example.hover.onebeen.puzzle.PuzzleDummyActivity;

public class SplashActivity extends Activity {

    private static String TAG = SplashActivity.class.getName();
    private static long SLEEP_TIME = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ImageView viewById = (ImageView) findViewById(R.id.white_logo);

        RotateAnimation anim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1200);

        viewById.startAnimation(anim);


        IntentLauncher launcher = new IntentLauncher();
        launcher.start();

    }

    private class IntentLauncher extends Thread {
        public void run() {

            try {
                Thread.sleep(SLEEP_TIME*1000);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}