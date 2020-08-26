package com.wow.boxingacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private LinearLayout label1;
    private LinearLayout label2;
    private LinearLayout label3;
    private LinearLayout label4;
    private ImageView shortImage;
    private AdView mAdView;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.mainAd);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        shortImage = (ImageView) findViewById(R.id.shortImage);
        shortImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShortActivity();
            }
        });

        label1 = (LinearLayout) findViewById(R.id.label1);
        label2 = (LinearLayout) findViewById(R.id.label2);
        label3 = (LinearLayout) findViewById(R.id.label3);
        label4 = (LinearLayout) findViewById(R.id.label4);
        label1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });
        label2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecenziiActivity();
            }
        });
        label3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocationActivity();
            }
        });
        label4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalleryActivity();
            }
        });
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openRecenziiActivity(){
        Intent intent = new Intent(this, RecenziiActivity.class);
        startActivity(intent);
    }

    public void openLocationActivity(){
        String urlString = "https://www.google.com/maps/place/44%C2%B018'22.4%22N+23%C2%B048'09.6%22E/@44.3062222,23.800478,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d44.3062099!4d23.8026678";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            intent.setPackage(null);
            startActivity(intent);
        }
    }

    public void openGalleryActivity(){
        Intent intent = new Intent(this, GallleryActivity.class);
        startActivity(intent);
    }

    public void openShortActivity(){
        Intent intent = new Intent(this, ShortActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {

    }

}
