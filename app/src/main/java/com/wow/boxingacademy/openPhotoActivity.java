package com.wow.boxingacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class openPhotoActivity extends AppCompatActivity {
    private AdView mAdView;
    int photoFlags[] = {0, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    int flag, num;
    float x1,x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_photo);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.photoAd);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        num = intent.getIntExtra("numePhoto", 0);
        flag = intent.getIntExtra("flag", 0);
        LoadImage(num, flag);
    }

    @SuppressLint("SourceLockedOrientationActivity")
    public void LoadImage(int n, int f){
        if(f == 1){
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mAdView.setVisibility(View.VISIBLE);
        }else{
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mAdView.setVisibility(View.INVISIBLE);
        }
        ImageView imageView = (ImageView) findViewById(R.id.imageView45);
        switch(n){
            case 1:
                imageView.setImageResource(R.drawable.whole1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.whole2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.whole3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.whole4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.whole5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.whole6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.whole7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.whole8);
                break;
            case 9:
                imageView.setImageResource(R.drawable.whole9);
                break;
            case 10:
                imageView.setImageResource(R.drawable.whole10);
                break;
            case 11:
                imageView.setImageResource(R.drawable.whole11);
                break;
            case 12:
                imageView.setImageResource(R.drawable.whole12);
                break;
            case 13:
                imageView.setImageResource(R.drawable.whole13);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > 70) {
                    if (x2 > x1) {
                        /// +
                        if(num != 1){
                            num--;
                            LoadImage(num, photoFlags[num]);
                        }
                    }
                    else
                    {
                        /// -
                        if(num != 13){
                            num++;
                            LoadImage(num, photoFlags[num]);
                        }
                    }

                }
                else { }
                break;
        }
        return super.onTouchEvent(event);
    }

}
