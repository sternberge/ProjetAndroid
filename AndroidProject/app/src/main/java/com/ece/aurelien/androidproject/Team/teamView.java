package com.ece.aurelien.androidproject.Team;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ece.aurelien.androidproject.Player.Player;
import com.ece.aurelien.androidproject.Player.PlayerDAO;
import com.ece.aurelien.androidproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win8 on 07/04/2017.
 */

public class teamView extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams_composition);
        String teamA = null;
        String teamB = null;
        TextView teamAview,teamBview,player1view,player2view;
        PlayerDAO playerDAO;
        Bundle data = getIntent().getExtras();
        if (data!=null) {
            teamA = data.getString("teamASend");
            teamB = data.getString("teamBSend");
        }
        
        teamAview = (TextView) findViewById(R.id.teamATag);
        teamBview = (TextView) findViewById(R.id.teamBTag);
        player1view = (TextView) findViewById(R.id.player1);
        player2view =(TextView) findViewById(R.id.player2);
        teamAview.setText(teamA);
        teamBview.setText(teamB);

        playerDAO = new PlayerDAO(getApplicationContext());
        List<Player> myPlayers = new ArrayList<>();
        myPlayers = playerDAO.getPlayerByTeam(teamA);
        if (myPlayers != null) {
            if (myPlayers.size()-1==0) {
                player1view.setText(myPlayers.get(0).getName());
            }else { // if only one player
                 player1view.setText("ajouter joueur");
            }
            if (myPlayers.size()-1==1) {
                player2view.setText(myPlayers.get(1).getName());
            }else{ // 2 player.. etc
                player2view.setText("ajouter joueur");
            }
        }else{ // if no player at all
            player1view.setText("ajouter joueur");
            player2view.setText("ajouter joueur");
        }
    }
}
