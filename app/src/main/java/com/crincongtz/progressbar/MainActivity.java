package com.crincongtz.progressbar;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    long down,up;

    private int counter = 5;
    private int timeCircleProgress = 5;
    private int timeLinearProgress = 0;
    private CounterClass timer;

    private ProgressBar circularProgress;
    private TextView tvProcess;
    private ProgressBar progressBar;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Progress Bar
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circle_progress);

        circularProgress = findViewById(R.id.circularProgressbar);
        circularProgress.setProgress(5);
        circularProgress.setMax(5);
        circularProgress.setProgressDrawable(drawable);

        tvProcess = (TextView) findViewById(R.id.tvProcess);

        timer = new CounterClass(counter * 1000, 900);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setMax(100);

//        final ObjectAnimator objectAnimator = ObjectAnimator.ofInt(progressBar, "progress",
//                progressBar.getProgress(), 100).setDuration(5000);

//        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int progress = (int) valueAnimator.getAnimatedValue();
//                progressBar.setProgress(progress);
//            }
//        });

        TextView btn = (TextView) findViewById(R.id.text_view_button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                objectAnimator.start();
//            }
//        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN :
//                        Log.e("EVENT", "DOWN");
                        timer.start();
                        break;
                    case MotionEvent.ACTION_UP :
//                        Log.e("EVENT", "UP");
                        if (timeLinearProgress != 5) {
                            timer.cancel();
                            resetAllValues();
                            progressBar.setProgress(timeLinearProgress);

                            circularProgress.setProgress(5);
                            circularProgress.setMax(5);
                            tvProcess.setText("5");
                        }

                        break;
                }
                return true;
            }
        });


    }


    public class CounterClass extends CountDownTimer {
        CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millis) {
            timeCircleProgress--;
            Log.d(TAG, "seg = " + timeCircleProgress);
            circularProgress.setProgress(timeCircleProgress);
            tvProcess.setText(String.valueOf(timeCircleProgress));

            timeLinearProgress ++;
            Log.w(TAG, "time = " + timeLinearProgress);
            int progress = timeLinearProgress * 20;
            progressBar.setProgress(progress);
        }

        @Override
        public void onFinish() {
            Log.w(TAG, "onFinish()");
            resetAllValues();
        }
    }

    private void resetAllValues() {
        timeLinearProgress = 0;
        timeCircleProgress = 5;
    }

}
