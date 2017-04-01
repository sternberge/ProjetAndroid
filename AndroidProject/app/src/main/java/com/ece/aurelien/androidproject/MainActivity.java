package com.ece.aurelien.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView addMatchLogo;
    TextView matchAddInfo;
    TextView matchTwoPic,
            matchTwoTeamOne,
            matchTwoTeamTwo;
    TextView matchThreePic,
            matchThreeTeamOne,
            matchThreeTeamTwo;
    TextView matchFourPic,
            matchFourTeamOne,
            matchFourTeamTwo;
    TextView matchFivePic,
            matchFiveTeamOne,
            matchFiveTeamTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // VARIABLES
        addMatchLogo = (ImageView) findViewById(R.id.addMatchLogo);
        matchAddInfo = (TextView) findViewById(R.id.matchAddInfo);
        addMatchLogo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMatch();
            }
        });
        matchAddInfo.setText(" < Ajoutez un match");

        matchTwoPic = (TextView) findViewById(R.id.matchTwoPic);
        matchTwoTeamOne = (TextView) findViewById(R.id.matchTwoTeamOne);
        matchTwoTeamTwo = (TextView) findViewById(R.id.matchTwoTeamTwo);

        matchThreePic = (TextView) findViewById(R.id.matchThreePic);
        matchThreeTeamOne = (TextView) findViewById(R.id.matchThreeTeamOne);
        matchThreeTeamTwo = (TextView) findViewById(R.id.matchThreeTeamTwo);

        matchFourPic = (TextView) findViewById(R.id.matchFourPic);
        matchFourTeamOne = (TextView) findViewById(R.id.matchFourTeamOne);
        matchFourTeamTwo = (TextView) findViewById(R.id.matchFourTeamTwo);

        matchFivePic = (TextView) findViewById(R.id.matchFivePic);
        matchFiveTeamOne = (TextView) findViewById(R.id.matchFiveTeamOne);
        matchFiveTeamTwo = (TextView) findViewById(R.id.matchFiveTeamTwo);

        }

        public void addMatch(){
            Intent ret = new Intent(MainActivity.this, MainActivity.class);
            startActivity(ret);
        }



    }
