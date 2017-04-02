package com.ece.aurelien.androidproject.Match;

import android.content.Context;
import android.icu.math.MathContext;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.ece.aurelien.androidproject.R;

/**
 * Created by win8 on 01/04/2017.
 */

public class MatchView extends AppCompatActivity {
    EditText teamA,teamB;
    EditText scoreA,scoreB;
    EditText location;
    EditText date;
    Button button;
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_informationcorr);
        teamA = (EditText) findViewById(R.id.editText);
        teamB = (EditText) findViewById(R.id.editText2);
        scoreA = (EditText) findViewById(R.id.editText3);
        scoreB = (EditText) findViewById(R.id.editText4);
        location = (EditText) findViewById(R.id.editText8);
        date = (EditText) findViewById(R.id.editText7);
        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          String teamAString = teamA.getText().toString();
                                          String teamBString = teamB.getText().toString();
                                          int scoreAString = Integer.parseInt(scoreA.getText().toString());
                                          int scoreBString = Integer.parseInt(scoreB.getText().toString());
                                          String locationString = location.getText().toString();
                                          String dateString = date.getText().toString();

                                          // a modifier avec la latitude et longitude pour ggMaps et la date au bon format
                                          Match match =  new Match(teamAString,teamBString,scoreAString,scoreBString,0,0,dateString);
                                          MatchDAO matchDao = new MatchDAO(context);
                                          matchDao.open();
                                          matchDao.createMatch(match);
                                          Toast.makeText(getBaseContext(),"Registration of the match success",Toast.LENGTH_LONG).show();
                                          teamA.setText("Team A");
                                          teamB.setText("Team B");
                                          scoreA.setText("0");
                                          scoreB.setText("0");
                                          location.setText("Location");
                                          date.setText("Date");
                                      }
                                  }

        );


    }
}
