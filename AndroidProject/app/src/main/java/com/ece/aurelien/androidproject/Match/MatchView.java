package com.ece.aurelien.androidproject.Match;

import android.app.DialogFragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.ece.aurelien.androidproject.Camera.CameraActivity;
import com.ece.aurelien.androidproject.DatePickerFragment;
import com.ece.aurelien.androidproject.MainActivity;
import com.ece.aurelien.androidproject.MapsActivity;
import com.ece.aurelien.androidproject.R;
import com.ece.aurelien.androidproject.Team.TeamActivity;
import com.ece.aurelien.androidproject.Team.TeamDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win8 on 01/04/2017.
 */

public class MatchView extends AppCompatActivity {
    Spinner teamA,teamB;
    EditText scoreA,scoreB;
    Button location;
    TextView latitude, longitude;
    Button button;
    Button changeDate;
    Button picture;
    Context context;
    TextView addTeamview;
    TextView dateOfTheMatch;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_informationtest);

        addTeamview = (TextView) findViewById(R.id.AddTeam);
        addTeamview.setText(R.string.addTeam);
        addTeamview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatchView.this,TeamActivity.class);
                startActivity(intent);
            }
        });


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
        latitude= (TextView) findViewById(R.id.textView3);
        longitude= (TextView) findViewById(R.id.textView);
        dateOfTheMatch = (TextView) findViewById(R.id.textView6);
        button = (Button) findViewById(R.id.button5);
        picture = (Button) findViewById(R.id.button8);
        changeDate = (Button) findViewById(R.id.button4);



        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v){
            DialogFragment picker = new DatePickerFragment();
            picker.show(getFragmentManager(), "datePicker");}
        });





        location = (Button) findViewById(R.id.textviewlocation);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatchView.this, MapsActivity.class);
                intent.putExtra("teamA",String.valueOf(teamA.getSelectedItemPosition()));
                intent.putExtra("teamB",String.valueOf(teamB.getSelectedItemPosition()));
                intent.putExtra("scoreA",scoreA.getText().toString());
                intent.putExtra("scoreB",scoreB.getText().toString());
                startActivity(intent);
            }
        });

        // handle here if we get back from map activity
        Bundle data = getIntent().getExtras();
        if (data!=null) {
            String maplatitude = data.getString("maplatitude");
            String maplongitude = data.getString("maplongitude");
            String teamABack = data.getString("teamABack");
            String teamBBack = data.getString("teamBBack");
            String scoreABack = data.getString("scoreABack");
            String scoreBBack = data.getString("scoreBBack");
            latitude.setText(maplatitude);
            longitude.setText(maplongitude);
            teamA.setSelection(Integer.parseInt(teamABack));
            teamB.setSelection(Integer.parseInt(teamBBack));
            scoreA.setText(scoreABack);
            scoreB.setText(scoreBBack);
        }

        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          String teamAString = teamA.getSelectedItem().toString();
                                          String teamBString = teamB.getSelectedItem().toString();
                                          int scoreAString = Integer.parseInt(scoreA.getText().toString());
                                          int scoreBString = Integer.parseInt(scoreB.getText().toString());
                                          String locationString = location.getText().toString();
                                          double latitudeString= Double.valueOf(latitude.getText().toString());
                                          double longitudeString= Double.valueOf(longitude.getText().toString());
                                          String dateString = dateOfTheMatch.getText().toString();

                                          // a modifier avec la latitude et longitude pour ggMaps et la date au bon format
                                          Match match =  new Match(teamAString,teamBString,scoreAString,scoreBString,latitudeString,longitudeString,dateString);
                                          MatchDAO matchDao = new MatchDAO(context);
                                          matchDao.open();
                                          matchDao.createMatch(match);
                                          Toast.makeText(getBaseContext(),R.string.matchSuccess,Toast.LENGTH_LONG).show();

                                          scoreA.setText("0");
                                          scoreB.setText("0");
                                          latitude.setText("");
                                          longitude.setText("");

                                          Intent intent = new Intent(MatchView.this, MainActivity.class);
                                          startActivity(intent);
                                      }
                                  }

        );

        picture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MatchView.this, CameraActivity.class);
                startActivity(intent);
            }
        });


    }

}
