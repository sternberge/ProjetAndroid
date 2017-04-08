package com.ece.aurelien.androidproject.Score;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ece.aurelien.androidproject.R;
import com.ece.aurelien.androidproject.Team.teamView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win8 on 08/04/2017.
 */

public class ScoreView extends AppCompatActivity {
    TextView matchView,playerView,dynamicView;
    TextView pointTV,decisiveTV,reboundTV,counterTV,interceptionTV,minPlayTV;
    ScoreDAO scoreDAO;
    String matchID,teamName,playerName,teamAName,teamBName;
    Button back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_informations);
        //firstpart
        matchView = (TextView) findViewById(R.id.Matchview);
        playerView = (TextView) findViewById(R.id.PlayerView);
        dynamicView = (TextView) findViewById(R.id.dynamicView);
        back = (Button) findViewById(R.id.button7);

        //scndpart
        pointTV = (TextView) findViewById(R.id.pointTV);;
        decisiveTV = (TextView) findViewById(R.id.decisiveTV);;
        reboundTV = (TextView) findViewById(R.id.reboundTV);;
        counterTV = (TextView) findViewById(R.id.counterTV);;
        interceptionTV = (TextView) findViewById(R.id.interceptionTV);;
        minPlayTV = (TextView) findViewById(R.id.minPlayTV);;

        Bundle data = getIntent().getExtras();
        if (data!=null) {
            matchID = data.getString("matchID");
            teamName = data.getString("teamName");
            teamAName = data.getString("teamAName");
            teamBName = data.getString("teamBName");
            playerName = data.getString("playerName");
            matchView.setText(teamName);
            playerView.setText(playerName);

        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreView.this,teamView.class);
                intent.putExtra("teamASend", teamAName);
                intent.putExtra("teamBSend",teamBName);
                intent.putExtra("matchID", matchID);
                startActivity(intent);
            }
        });
        scoreDAO = new ScoreDAO(getApplicationContext());
        List<Score> myScore = new ArrayList<>();
        myScore = scoreDAO.getScore(matchID.toString(),playerName.toString());
        if (myScore != null) {
            matchView.setTextSize(20);
            playerView.setTextSize(18);
            pointTV.setText(String.valueOf(myScore.get(0).getPoint()));
            decisiveTV.setText(String.valueOf(myScore.get(0).getDecisive()));
            reboundTV.setText(String.valueOf(myScore.get(0).getRebound()));
            counterTV.setText(String.valueOf(myScore.get(0).getCounter()));
            interceptionTV.setText(String.valueOf(myScore.get(0).getInterception()));
            minPlayTV.setText(String.valueOf(myScore.get(0).getMinuteplay()));
        }else{
            dynamicView.setText("cliquer ici pour ajouter des stats");
            dynamicView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ScoreView.this,ScoreActivity.class);
                    intent.putExtra("matchID",matchID);
                    intent.putExtra("playerName",playerName);
                    intent.putExtra("teamName",teamName);
                    startActivity(intent);
                }
            });
        }
    }
    }

