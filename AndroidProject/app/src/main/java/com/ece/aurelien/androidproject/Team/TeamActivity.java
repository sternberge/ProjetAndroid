package com.ece.aurelien.androidproject.Team;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ece.aurelien.androidproject.DatabaseHandler;
import com.ece.aurelien.androidproject.R;

public class TeamActivity extends AppCompatActivity {
    EditText teamName, teamLocation;
    String teamStringName, teamStringLocation;
    Button myButton;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_informations);
        teamName = (EditText) findViewById(R.id.editText5);
        teamLocation = (EditText) findViewById(R.id.editText6);
        myButton = (Button) findViewById(R.id.button2);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamStringName = teamName.getText().toString();
                teamStringLocation = teamLocation.getText().toString();
                Team newTeam = new Team(teamStringName,teamStringLocation,0);
                TeamDAO teamDAO = new TeamDAO(context);
                teamDAO.open();
                teamDAO.createTeam(newTeam);
                Toast.makeText(getBaseContext(),"Registration of the team success",Toast.LENGTH_LONG).show();
                teamName.setText("");
                teamLocation.setText("");
            }
        });
    }
}
