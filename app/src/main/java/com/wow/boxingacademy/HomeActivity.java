package com.wow.boxingacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private ImageView buttonView;
    private TextView status;
    private TextView dayView;
    private int state = 1; // 1 - HIDDEN | 2 - VISIBLE

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        status  = (TextView) findViewById(R.id.clockText);
        dayView = (TextView) findViewById(R.id.clockHour);
        Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        SimpleDateFormat Dateformat = new SimpleDateFormat("HH:mm");

        String strDate = Dateformat.format(now);
        int hour = ((int) strDate.charAt(0) - '0') * 10 + ((int) strDate.charAt(1) - '0');
        String day = simpleDateformat.format(now);
        if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday") || day.equals("Friday")) {
            dayView.setText("09:00 - 19:00");
            if (hour >= 9 && hour < 19) {
                status.setText("Deschis acum");
                status.setTextColor(Color.parseColor("#1CBF17"));
            } else {
                status.setText("Inchis acum");
                status.setTextColor(Color.parseColor("#BF1717"));
            }
        } else if (day.equals("Saturday")) {
            dayView.setText("09:00 - 16:00");
            if (hour >= 9 && hour < 16) {
                status.setText("Deschis acum");
                status.setTextColor(Color.parseColor("#1CBF17"));
            } else {
                status.setText("Inchis acum");
                status.setTextColor(Color.parseColor("#BF1717"));
            }
        } else {
            dayView.setText("INCHIS");
            status.setText("Inchis acum");
            status.setTextColor(Color.parseColor("#BF1717"));
        }


        buttonView = (ImageView) findViewById(R.id.imgButton);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == 1){
                    state = 2;
                    buttonView.setImageResource(R.drawable.down);
                    makeVisible();
                }else{
                    state = 1;
                    buttonView.setImageResource(R.drawable.up);
                    makeHidden();
                }
            }
        });
    }

    public void makeVisible(){
        TextView view;
        view = (TextView) findViewById(R.id.luniText);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.martiText);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.mieruriText);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.joiText);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.vineriText);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.sambataText);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.duminicaText);
        view.setVisibility(View.VISIBLE);

        view = (TextView) findViewById(R.id.luniHour);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.martiHour);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.miercuriHour);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.joiHour);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.vineriHour);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.sambataHour);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.duminicaHour);
        view.setVisibility(View.VISIBLE);
    }

    public void makeHidden(){
        TextView view;
        view = (TextView) findViewById(R.id.luniText);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.martiText);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.mieruriText);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.joiText);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.vineriText);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.sambataText);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.duminicaText);
        view.setVisibility(View.GONE);

        view = (TextView) findViewById(R.id.luniHour);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.martiHour);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.miercuriHour);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.joiHour);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.vineriHour);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.sambataHour);
        view.setVisibility(View.GONE);
        view = (TextView) findViewById(R.id.duminicaHour);
        view.setVisibility(View.GONE);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
