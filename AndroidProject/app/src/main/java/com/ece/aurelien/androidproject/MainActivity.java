package com.ece.aurelien.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ece.aurelien.androidproject.Match.Match;
import com.ece.aurelien.androidproject.Match.MatchDAO;
import com.ece.aurelien.androidproject.Match.MatchView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView addMatchLogo;
    TextView matchAddInfo;
    TextView matchTwoPic,
            matchTwoTeamOne,
            matchTwoTeamTwo,
            matchTwoScoreA,
            matchTwoScoreB;
    TextView matchThreePic,
            matchThreeTeamOne,
            matchThreeTeamTwo,
            matchThreeScoreA,
            matchThreeScoreB;
    TextView matchFourPic,
            matchFourTeamOne,
            matchFourTeamTwo,
            matchFourScoreA,
            matchFourScoreB;
    TextView matchFivePic,
            matchFiveTeamOne,
            matchFiveTeamTwo,
            matchFiveScoreA,
            matchFiveScoreB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addMatchLogo = (ImageView) findViewById(R.id.addMatchLogo);
        matchAddInfo = (TextView) findViewById(R.id.matchAddInfo);
        // add a match
        addMatchLogo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMatch();
            }
        });
        matchAddInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMatch();
            }
        });
        matchAddInfo.setText(" < Ajoutez un match");


        matchTwoPic = (TextView) findViewById(R.id.matchTwoPic);
        matchTwoTeamOne = (TextView) findViewById(R.id.matchTwoTeamOne);
        matchTwoTeamTwo = (TextView) findViewById(R.id.matchTwoTeamTwo);
        matchTwoScoreA = (TextView) findViewById(R.id.matchTwoScoreA);
        matchTwoScoreB = (TextView) findViewById(R.id.matchTwoScoreB);

        matchThreePic = (TextView) findViewById(R.id.matchThreePic);
        matchThreeTeamOne = (TextView) findViewById(R.id.matchThreeTeamOne);
        matchThreeTeamTwo = (TextView) findViewById(R.id.matchThreeTeamTwo);
        matchThreeScoreA = (TextView) findViewById(R.id.matchThreeScoreA);
        matchThreeScoreB = (TextView) findViewById(R.id.matchThreeScoreB);

        matchFourPic = (TextView) findViewById(R.id.matchFourPic);
        matchFourTeamOne = (TextView) findViewById(R.id.matchFourTeamOne);
        matchFourTeamTwo = (TextView) findViewById(R.id.matchFourTeamTwo);
        matchFourScoreA = (TextView) findViewById(R.id.matchFourScoreA);
        matchFourScoreB = (TextView) findViewById(R.id.matchFourScoreB);

        matchFivePic = (TextView) findViewById(R.id.matchFivePic);
        matchFiveTeamOne = (TextView) findViewById(R.id.matchFiveTeamOne);
        matchFiveTeamTwo = (TextView) findViewById(R.id.matchFiveTeamTwo);
        matchFiveScoreA = (TextView) findViewById(R.id.matchFiveScoreA);
        matchFiveScoreB = (TextView) findViewById(R.id.matchFiveScoreB);

        // show match from database ( last from the list first)
        MatchDAO matchDAO= new MatchDAO(getApplicationContext());
        List<Match> myMatch =  matchDAO.getAllMatch();
        int i=myMatch.size()-1;
        if (i>=0) {
            matchTwoTeamOne.setText(myMatch.get(i).getTeamA());
            matchTwoTeamTwo.setText(myMatch.get(i).getTeamB());
            matchTwoScoreA.setText(String.valueOf(myMatch.get(i).getResultA()));
            matchTwoScoreB.setText(String.valueOf(myMatch.get(i).getResultB()));
            i--;
        }else {
            matchTwoTeamOne.setText("Pas de match en BDD");
        }
        if (i>=0) {
            matchThreeTeamOne.setText(myMatch.get(i).getTeamA());
            matchThreeTeamTwo.setText(myMatch.get(i).getTeamB());
            matchThreeScoreA.setText(String.valueOf(myMatch.get(i).getResultA()));
            matchThreeScoreB.setText(String.valueOf(myMatch.get(i).getResultB()));
            i--;
        }else {
            matchThreeTeamOne.setText("Pas de match en BDD");
        }
        if (i>=0) {
            matchFourTeamOne.setText(myMatch.get(i).getTeamA());
            matchFourTeamTwo.setText(myMatch.get(i).getTeamB());
            matchFourScoreA.setText(String.valueOf(myMatch.get(i).getResultA()));
            matchFourScoreB.setText(String.valueOf(myMatch.get(i).getResultB()));
            i--;
        }else {
            matchFourTeamOne.setText("Pas de match en BDD");
        }
        if (i>=0) {
            matchFiveTeamOne.setText(myMatch.get(i).getTeamA());
            matchFiveTeamTwo.setText(myMatch.get(i).getTeamB());
            matchFiveScoreA.setText(String.valueOf(myMatch.get(i).getResultA()));
            matchFiveScoreB.setText(String.valueOf(myMatch.get(i).getResultB()));
        }else {
            matchFiveTeamOne.setText("Pas de match en BDD");
        }
        //

        }

        public void addMatch(){
            Intent ret = new Intent(MainActivity.this, MatchView.class);
            startActivity(ret);
        }



    }
