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
        TextView teamAview,teamBview,player1Aview,player2Aview,player3Aview,player4Aview,player5Aview,player1Bview,player2Bview,player3Bview,player4Bview,player5Bview;
        PlayerDAO playerDAO;
        Bundle data = getIntent().getExtras();
        if (data!=null) {
            teamA = data.getString("teamASend");
            teamB = data.getString("teamBSend");
        }

        teamAview = (TextView) findViewById(R.id.teamATag);
        teamBview = (TextView) findViewById(R.id.teamBTag);
        //all player view team A
        player1Aview = (TextView) findViewById(R.id.player1A);
        player2Aview = (TextView) findViewById(R.id.player2A);
        player3Aview = (TextView) findViewById(R.id.player3A);
        player4Aview = (TextView) findViewById(R.id.player4A);
        player5Aview = (TextView) findViewById(R.id.player5A);
        //all player view team B
        player1Bview =(TextView) findViewById(R.id.player1B);
        player2Bview =(TextView) findViewById(R.id.player2B);
        player3Bview =(TextView) findViewById(R.id.player3B);
        player4Bview =(TextView) findViewById(R.id.player4B);
        player5Bview =(TextView) findViewById(R.id.player5B);
        teamAview.setText(teamA);
        teamBview.setText(teamB);

        playerDAO = new PlayerDAO(getApplicationContext());
        ////
        // TEAM A HANDLING
        /////
        List<Player> myPlayersA = new ArrayList<>();
        myPlayersA = playerDAO.getPlayerByTeam(teamA);
        if (myPlayersA != null) {//pour team A
            if (myPlayersA.size()-1==0) {
                player1Aview.setText(myPlayersA.get(0).getName());
            }else { // if only one player
                 player1Aview.setText("ajouter joueur");
            }
            if (myPlayersA.size()-1==1) {
                player2Aview.setText(myPlayersA.get(1).getName());
            }else{ // 2 player.. etc
                player2Aview.setText("ajouter joueur");
            }
            if (myPlayersA.size()-1==2) {
                player3Aview.setText(myPlayersA.get(2).getName());
            }else{
                player3Aview.setText("ajouter joueur");
            }
            if (myPlayersA.size()-1==3) {
                player4Aview.setText(myPlayersA.get(3).getName());
            }else{
                player4Aview.setText("ajouter joueur");
            }
            if (myPlayersA.size()-1==4) {
                player5Aview.setText(myPlayersA.get(4).getName());
            }else{
                player5Aview.setText("ajouter joueur");
            }
        }else{ // if no player at all
            player1Aview.setText("ajouter joueur");
            player2Aview.setText("ajouter joueur");
            player3Aview.setText("ajouter joueur");
            player4Aview.setText("ajouter joueur");
            player5Aview.setText("ajouter joueur");
        }
        ////
        ///TEAM B HANDLING
        ///
        List<Player> myPlayersB = new ArrayList<>();
        myPlayersB = playerDAO.getPlayerByTeam(teamA);
        if (myPlayersB != null) {//pour team A
            if (myPlayersB.size()-1==0) {
                player1Bview.setText(myPlayersB.get(0).getName());
            }else { // if only one player
                player1Bview.setText("ajouter joueur");
            }
            if (myPlayersB.size()-1==1) {
                player2Bview.setText(myPlayersB.get(1).getName());
            }else{ // 2 player.. etc
                player2Bview.setText("ajouter joueur");
            }
            if (myPlayersB.size()-1==2) {
                player3Bview.setText(myPlayersB.get(2).getName());
            }else{
                player3Bview.setText("ajouter joueur");
            }
            if (myPlayersB.size()-1==3) {
                player4Bview.setText(myPlayersB.get(3).getName());
            }else{
                player4Bview.setText("ajouter joueur");
            }
            if (myPlayersB.size()-1==4) {
                player5Bview.setText(myPlayersB.get(4).getName());
            }else{
                player5Bview.setText("ajouter joueur");
            }
        }else{ // if no player at all
            player1Bview.setText("ajouter joueur");
            player2Bview.setText("ajouter joueur");
            player3Bview.setText("ajouter joueur");
            player4Bview.setText("ajouter joueur");
            player5Bview.setText("ajouter joueur");
        }
    }
    
}
