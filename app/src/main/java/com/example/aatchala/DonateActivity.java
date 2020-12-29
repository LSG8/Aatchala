package com.example.aatchala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class DonateActivity extends AppCompatActivity {

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_donate);
        mImageView = (ImageView) findViewById(R.id.donateView);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    public void onPause(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onPause();
    }

    public void onStop() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onStop();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            mImageView.setScaleX(mScaleFactor);
            mImageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}

