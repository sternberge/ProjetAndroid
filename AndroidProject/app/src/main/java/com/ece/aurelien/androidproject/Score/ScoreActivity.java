package com.ece.aurelien.androidproject.Score;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ece.aurelien.androidproject.R;

/**
 * Created by win8 on 08/04/2017.
 */

public class ScoreActivity extends AppCompatActivity {

    EditText pointET,decisiveET, reboundET, counterET, interceptionET, minPlayET;
    Button save;
    String matchID;
    String playerName;
    String teamName,teamA,teamB;
    int pointint,decisiveint,reboundint,counterint,interceptionint,minuteplayint;
    Context context =  this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_add_layout);

        Bundle data = getIntent().getExtras();
        if (data!=null) {
            matchID = data.getString("matchID");
            playerName = data.getString("playerName");
            teamName = data.getString("teamName");
            teamA = data.getString("teamASend");
            teamB = data.getString("teamBSend");
        }

        pointET = (EditText) findViewById(R.id.pointET);
        decisiveET = (EditText) findViewById(R.id.decisiveET);
        reboundET = (EditText) findViewById(R.id.reboundET);
        counterET = (EditText) findViewById(R.id.counterET);
        interceptionET = (EditText) findViewById(R.id.interceptionET);
        minPlayET = (EditText) findViewById(R.id.minPlayET);
        save = (Button) findViewById(R.id.button6);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointint = Integer.valueOf(pointET.getText().toString());
                decisiveint = Integer.valueOf(decisiveET.getText().toString());
                reboundint = Integer.valueOf(reboundET.getText().toString());
                counterint = Integer.valueOf(counterET.getText().toString());
                interceptionint = Integer.valueOf(interceptionET.getText().toString());
                minuteplayint = Integer.valueOf(minPlayET.getText().toString());
                Score score = new Score(matchID,playerName,pointint,decisiveint,reboundint,counterint,interceptionint,minuteplayint);
                ScoreDAO scoreDAO = new ScoreDAO(context);
                scoreDAO.open();
                scoreDAO.createScore(score);
                Toast.makeText(getBaseContext(), R.string.scoreSuccess, Toast.LENGTH_LONG).show();


                pointET.setText("");
                decisiveET.setText("");
                reboundET.setText("");
                counterET.setText("");
                interceptionET.setText("");
                minPlayET.setText("");
                Intent intent = new Intent(ScoreActivity.this, ScoreView.class);
                intent.putExtra("matchID",matchID);
                intent.putExtra("playerName",playerName);
                intent.putExtra("teamName",teamName);
                intent.putExtra("teamAName", teamA);
                intent.putExtra("teamBName",teamB);
                startActivity(intent);
            }
        });
    }
}
