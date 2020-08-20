package com.wow.boxingacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class NewReviewActivity extends AppCompatActivity {

    private Button backButton;
    private Button writeButton;
    private EditText numeView;
    private EditText masajView;
    private Button plusButton;
    private Button minusButton;
    private Float rat = 0f;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        backButton  = (Button)   findViewById(R.id.backButton);
        writeButton = (Button)   findViewById(R.id.scrieButton);
        numeView    = (EditText) findViewById(R.id.nume);
        masajView   = (EditText) findViewById(R.id.mesaj);
        plusButton  = (Button)   findViewById(R.id.buttonPlus);
        minusButton = (Button)   findViewById(R.id.buttonMinus);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ohShitGoBaaack(); finish();
            }
        });
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeReview();
            }
        });
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rat < 5f){
                    rat += 0.5f;
                    showRating(rat);
                }
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rat > 0f){
                    rat -= 0.5f;
                    showRating(rat);
                }
            }
        });

        numeView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        masajView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view != null && view instanceof EditText) {
                Rect r = new Rect();
                view.getGlobalVisibleRect(r);
                int rawX = (int)ev.getRawX();
                int rawY = (int)ev.getRawY();
                if (!r.contains(rawX, rawY)) {
                    view.clearFocus();
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public int cateSunt(String str, char a){
        int rez = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == a){
                rez++;
            }
        }
        return rez;
    }

    public void showRating(Float rating){
        ImageView[] starsView = {((ImageView) findViewById(R.id.valstar5)),
                ((ImageView) findViewById(R.id.valstar4)),
                ((ImageView) findViewById(R.id.valstar3)),
                ((ImageView) findViewById(R.id.valstar2)),
                ((ImageView) findViewById(R.id.valstar1))};
        for(int i = 0; i < 5; i++){
            starsView[i].setImageResource(R.drawable.no_star);
        }
        if(rating == 0f){
            return;
        }
        int intPartRating = rating.intValue();
        int contineVirgula = 0;
        if((int)((rating*10)%10) != 0){
            contineVirgula = 1;
        }
        for(int i = 0; i < 5; i++){
            starsView[i].setImageResource(R.drawable.no_star);
        }

        for(int i = 0; i < intPartRating; i++){
            starsView[i].setImageResource(R.drawable.whole);
        }
        if(contineVirgula == 1){
            starsView[intPartRating].setImageResource(R.drawable.half);
        }
    }

    public String filter(String str){
        if(cateSunt(str, ' ') == str.length()){
            return  "";
        }
        String rez = "";
        int first = 0,last = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != ' '){
                first = i;
                break;
            }
        }
        for(int i = str.length()-1; i >= 0; i--){
            if(str.charAt(i) != ' '){
                last = i;
                break;
            }
        }
        for(int i = first; i <= last; i++){
            rez += str.charAt(i);
        }
        return rez;
    }

    public boolean isValidName(String str){
        String newStr = filter(str);
        if(str.equals("") || newStr.equals("")){
            Toast.makeText(getApplicationContext(),"nume invalid",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(newStr.length() > 21){
            Toast.makeText(getApplicationContext(),"nume prea lung",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public String randomString(){
        String rez = "";

        for(int i = 0; i < 11; i++){
            Random rand = new Random();
            int val = rand.nextInt(52);
            if(val <= 25){
                rez += (char)(((int)'a')+val);
            }else{
                val = val-26;
                rez += (char)(((int)'A')+val);
            }
        }

        return rez;
    }

    public void writeReview(){
        String nume  = numeView.getText().toString();
        String mesaj = masajView.getText().toString();
        if(isValidName(nume)){
            if(isValidMessage(mesaj)){
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String rndId = randomString();
                DatabaseReference mRef = database.getReference().child("recenzii").child(rndId);
                mRef.child("nume").setValue(nume);
                mRef.child("mesaj").setValue(mesaj);
                mRef.child("rating").setValue(rat);
                ohShitGoBaaack();
                finish();
            }
        }
    }

    public boolean isValidMessage(String str){
        String newStr = filter(str);
        if(str.equals("") || newStr.equals("")){
            Toast.makeText(getApplicationContext(),"mesaj invalid",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(newStr.length() > 171){
            Toast.makeText(getApplicationContext(),"mesaj prea lung", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void ohShitGoBaaack(){
        Intent intent = new Intent(this, RecenziiActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        //do nothing
    }

}
