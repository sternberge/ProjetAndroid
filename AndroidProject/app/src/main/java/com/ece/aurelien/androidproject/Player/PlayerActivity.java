package com.ece.aurelien.androidproject.Player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ece.aurelien.androidproject.R;
import com.ece.aurelien.androidproject.Team.teamView;

/**
 * Created by win8 on 07/04/2017.
 */

public class PlayerActivity extends AppCompatActivity  {

    EditText editNameview, editFnameview, editNumberview;
    String NameStr,FnameStr;
    int NumberInt;
    Button myButton;
    String team,teamA,teamB,matchID;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_informations);
        // get team from previous teamview
        Bundle data = getIntent().getExtras();
        if (data!=null) {
            team = data.getString("teamName");
            teamA = data.getString("teamNameA");
            teamB = data.getString("teamNameB");
            matchID = data.getString("matchID");
        }
        editNameview = (EditText) findViewById(R.id.editName);
        editFnameview = (EditText) findViewById(R.id.editFname);
        editNumberview = (EditText) findViewById(R.id.editNumber);
        myButton = (Button) findViewById(R.id.button3);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameStr = editNameview.getText().toString();
                FnameStr = editFnameview.getText().toString();
                NumberInt = Integer.parseInt(editNumberview.getText().toString());
                Player player = new Player(NameStr,FnameStr,NumberInt,team);
                PlayerDAO playerDAO = new PlayerDAO(context);
                playerDAO.open();
                if (playerDAO.createPlayer(player)) {
                    //playerDAO.createPlayer(player);
                    Toast.makeText(getBaseContext(), "Registration of the player success", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getBaseContext(),"Registration of the player error",Toast.LENGTH_LONG).show();
                }


                editNameview.setText("");
                editFnameview.setText("");
                editNumberview.setText("");
                Intent intent = new Intent(PlayerActivity.this, teamView.class);
                intent.putExtra("teamASend",teamA);
                intent.putExtra("teamBSend",teamB);
                intent.putExtra("matchID",matchID);
                startActivity(intent);
            }
        });


    }
}
