package com.wow.boxingacademy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecenziiActivity extends AppCompatActivity {

    private Button prevButton;
    private Button nextButton;
    private Button newRecButton;

    String[] idRecnezii = new String[1002];
    String[] numeRecenzii = new String[1002];
    String[] mesaje = new String[1002];
    Float[] stele = new Float[1002];
    int nrRecenzii = 0,pageNumber = 1;

    public interface OnDataLoadedListener{
        public void onFinishLoading();
        public void onCancelled(DatabaseError firebaseError);
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recenzii);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        prevButton = (Button) findViewById(R.id.prevButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        newRecButton = (Button) findViewById(R.id.writeButton);

        getData(new OnDataLoadedListener(){
            public void onFinishLoading(){
                LoadPage(pageNumber);
            }
            public void onCancelled(DatabaseError firebaseError){ }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageNumber > 1){
                    pageNumber--;
                    LoadPage(pageNumber);
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageNumber != numberOfPages(nrRecenzii)){
                    pageNumber++;
                    LoadPage(pageNumber);
                }
            }
        });
        newRecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewReview();
            }
        });
    }

    public int numberOfPages(int numRec){
        if(numRec%2 == 0){
            return numRec/2;
        }else{
            return (numRec-1)/2+1;
        }
    }

    public void LoadPage(int num){
        DisableReview1();
        DisableReview2();
        if(num == 1){
            prevButton.setVisibility(View.INVISIBLE);
        }else{
            prevButton.setVisibility(View.VISIBLE);
        }
        if(num == numberOfPages(nrRecenzii)){
            nextButton.setVisibility(View.INVISIBLE);
        }else{
            nextButton.setVisibility(View.VISIBLE);
        }

        if(num != numberOfPages(nrRecenzii)){
            EnableReview1();
            EnableReview2();

            MakeReview1(numeRecenzii[num*2-1], mesaje[num*2-1], stele[num*2-1]);
            MakeReview2(numeRecenzii[num*2], mesaje[num*2], stele[num*2]);
        }else{
            if(nrRecenzii%2 == 0){
                EnableReview1();
                EnableReview2();
                MakeReview1(numeRecenzii[num*2-1], mesaje[num*2-1], stele[num*2-1]);
                MakeReview2(numeRecenzii[num*2], mesaje[num*2], stele[num*2]);
            }else{
                EnableReview1();
                MakeReview1(numeRecenzii[nrRecenzii], mesaje[nrRecenzii], stele[nrRecenzii]);
            }
        }
        TextView pagesView = (TextView) findViewById(R.id.pageView);
        pagesView.setText("Pagina " + String.valueOf(num) + " din " + numberOfPages(nrRecenzii));
    }

    public void EnableReview1(){
        CardView cView = (CardView) findViewById(R.id.cardView1);
        cView.setVisibility(View.VISIBLE);
    }

    public void EnableReview2(){
        CardView cView = (CardView) findViewById(R.id.cardView2);
        cView.setVisibility(View.VISIBLE);
    }

    public void DisableReview1(){
        CardView cView = (CardView) findViewById(R.id.cardView1);
        cView.setVisibility(View.INVISIBLE);
    }

    public void DisableReview2(){
        CardView cView = (CardView) findViewById(R.id.cardView2);
        cView.setVisibility(View.INVISIBLE);
    }

    public void MakeReview2(String nume, String mesaj, Float rating){
        TextView numeView = (TextView) findViewById(R.id.nume2);
        TextView mesajView = (TextView) findViewById(R.id.mesaj2);
        ImageView[] starsView = {((ImageView) findViewById(R.id.star25)),
                ((ImageView) findViewById(R.id.star24)),
                ((ImageView) findViewById(R.id.star23)),
                ((ImageView) findViewById(R.id.star22)),
                ((ImageView) findViewById(R.id.star21))};

        int intPartRating = rating.intValue();
        int contineVirgula = 0;
        TextView pageView = (TextView) findViewById(R.id.pageView);
        if((int)((rating*10)%10) != 0){
            contineVirgula = 1;
        }
        if(rating != 0) {
            for(int i = 0; i < 5; i++){
                starsView[i].setImageResource(R.drawable.no_star);
            }
            for (int i = (intPartRating - 1) + contineVirgula; i >= 0; i--) {
                starsView[4 - i].setImageResource(R.drawable.whole);
            }
            if (contineVirgula == 1) {
                starsView[4].setImageResource(R.drawable.half);
            }
        }

        numeView.setText(nume);
        mesajView.setText(mesaj);
    }

    public void MakeReview1(String nume, String mesaj, Float rating){
        TextView numeView = (TextView) findViewById(R.id.nume1);
        TextView mesajView = (TextView) findViewById(R.id.mesaj1);
        ImageView[] starsView = {((ImageView) findViewById(R.id.star15)),
                ((ImageView) findViewById(R.id.star14)),
                ((ImageView) findViewById(R.id.star13)),
                ((ImageView) findViewById(R.id.star12)),
                ((ImageView) findViewById(R.id.star11))};

        int intPartRating = rating.intValue();
        int contineVirgula = 0;
        TextView pageView = (TextView) findViewById(R.id.pageView);
        if((int)((rating*10)%10) != 0){
            contineVirgula = 1;
        }
        if(rating != 0) {
            for(int i = 0; i < 5; i++){
                starsView[i].setImageResource(R.drawable.no_star);
            }
            for (int i = (intPartRating - 1) + contineVirgula; i >= 0; i--) {
                starsView[4 - i].setImageResource(R.drawable.whole);
            }
            if (contineVirgula == 1) {
                starsView[4].setImageResource(R.drawable.half);
            }
        }

        numeView.setText(nume);
        mesajView.setText(mesaj);
    }

    public void getData(final OnDataLoadedListener listener) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/recenzii/");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    nrRecenzii++;
                    idRecnezii[nrRecenzii] = child.getKey();
                    numeRecenzii[nrRecenzii] = child.child("nume").getValue(String.class);
                    mesaje[nrRecenzii] = child.child("mesaj").getValue(String.class);
                    stele[nrRecenzii] = child.child("rating").getValue(Float.class);
                }
                listener.onFinishLoading();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onCancelled(databaseError);
            }
        });
    }

    public void openNewReview(){
        Intent intent = new Intent(this, NewReviewActivity.class);
        startActivity(intent);
        finish();
    }

    /*public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }*/

}
