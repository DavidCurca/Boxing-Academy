package com.wow.boxingacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class GallleryActivity extends AppCompatActivity {

    int videoFlags[] = {1, 2, 2, 2, 2, 1, 1, 1, 1, 2};
    int photoFlags[] = {2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    private AdView mAdView;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galllery);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.galleryAd);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ImageView photoButtons[] = {((ImageView) findViewById(R.id.fotografie1)),
                ((ImageView) findViewById(R.id.fotografie2)),
                ((ImageView) findViewById(R.id.fotografie3)),
                ((ImageView) findViewById(R.id.fotografie4)),
                ((ImageView) findViewById(R.id.fotografie5)),
                ((ImageView) findViewById(R.id.fotografie6)),
                ((ImageView) findViewById(R.id.fotografie7)),
                ((ImageView) findViewById(R.id.fotografie8)),
                ((ImageView) findViewById(R.id.fotografie9)),
                ((ImageView) findViewById(R.id.fotografie10)),
                ((ImageView) findViewById(R.id.fotografie11)),
                ((ImageView) findViewById(R.id.fotografie12)),
                ((ImageView) findViewById(R.id.fotografie13))};
        ImageView videoButtons[] = {((ImageView) findViewById(R.id.fotografie14)),
                ((ImageView) findViewById(R.id.fotografie15)),
                ((ImageView) findViewById(R.id.fotografie16)),
                ((ImageView) findViewById(R.id.fotografie17)),
                ((ImageView) findViewById(R.id.fotografie18)),
                ((ImageView) findViewById(R.id.fotografie19)),
                ((ImageView) findViewById(R.id.fotografie20)),
                ((ImageView) findViewById(R.id.fotografie21)),
                ((ImageView) findViewById(R.id.fotografie22)),
                ((ImageView) findViewById(R.id.fotografie23))};

        for(int i = 0; i < 13; i++){
            final int index = i;
            photoButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), openPhotoActivity.class);
                    intent.putExtra("numePhoto", index+1);
                    intent.putExtra("flag", photoFlags[index]);
                    startActivity(intent);
                }
            });
        }

        for(int i = 0; i < 10; i++){
            final int index = i;
            videoButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), openVideoActivity.class);
                    intent.putExtra("numeVideo", index+1);
                    intent.putExtra("flag", videoFlags[index]);
                    startActivity(intent);
                }
            });
        }

    }

    /*public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }*/

}
