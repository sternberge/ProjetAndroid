package com.ece.aurelien.androidproject.Match;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ece.aurelien.androidproject.MainActivity;
import com.ece.aurelien.androidproject.R;
import com.ece.aurelien.androidproject.Team.TeamDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win8 on 01/04/2017.
 */

public class MatchView extends AppCompatActivity {
    Spinner teamA,teamB;
    EditText scoreA,scoreB;
    EditText location;
    EditText date;
    Button button;
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_informationtest);

        //DropDown Menu TEAM A
        Spinner dropmenu1;
        SQLiteDatabase sqLiteDatabase;
        TeamDAO teamDAO;
        dropmenu1 = (Spinner) findViewById(R.id.spinner);
        teamDAO = new TeamDAO(getApplicationContext());
        List<String> teamsName = new ArrayList<>();
        teamsName = teamDAO.getAllName();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, teamsName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropmenu1.setAdapter(dataAdapter);
        //
        //Dropdown menu team B
        Spinner dropmenu2;
        TeamDAO teamDAO2;
        dropmenu2 = (Spinner) findViewById(R.id.spinner2);
        teamDAO2 = new TeamDAO(getApplicationContext());
        List<String> teamsName2 = new ArrayList<>();
        teamsName2 = teamDAO2.getAllName();
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, teamsName2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropmenu2.setAdapter(dataAdapter2);
        //

        teamA = (Spinner) findViewById(R.id.spinner);
        teamB = (Spinner) findViewById(R.id.spinner2);
        scoreA = (EditText) findViewById(R.id.editText3);
        scoreB = (EditText) findViewById(R.id.editText4);
        location = (EditText) findViewById(R.id.editText8);
        date = (EditText) findViewById(R.id.editText7);
        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          String teamAString = teamA.getSelectedItem().toString();
                                          String teamBString = teamB.getSelectedItem().toString();
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

                                          scoreA.setText("0");
                                          scoreB.setText("0");
                                          location.setText("Location");
                                          date.setText("Date");
                                          Intent intent = new Intent(MatchView.this, MainActivity.class);
                                          startActivity(intent);
                                      }
                                  }

        );


    }
}
