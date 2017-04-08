package com.ece.aurelien.androidproject.Team;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ece.aurelien.androidproject.MainActivity;
import com.ece.aurelien.androidproject.Player.Player;
import com.ece.aurelien.androidproject.Player.PlayerActivity;
import com.ece.aurelien.androidproject.Player.PlayerDAO;
import com.ece.aurelien.androidproject.R;
import com.ece.aurelien.androidproject.Score.ScoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win8 on 07/04/2017.
 */

public class teamView extends AppCompatActivity {
    String matchID;
    Button myButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams_composition);
        String teamA = null;
        String teamB = null;
        //String matchID = null;
        final TextView teamAview,teamBview,player1Aview,player2Aview,player3Aview,player4Aview,player5Aview,player1Bview,player2Bview,player3Bview,player4Bview,player5Bview;
        PlayerDAO playerDAO;
        Bundle data = getIntent().getExtras();
        if (data!= null) {
            teamA = data.getString("teamASend");
            teamB = data.getString("teamBSend");
            matchID = data.getString("matchID");
        }
        myButton = (Button) findViewById(R.id.button9);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teamView.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //Toast.makeText(this, teamB, Toast.LENGTH_SHORT).show();
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
            if (myPlayersA.size()-1>-1) {
                player1Aview.setText(myPlayersA.get(0).getName());
                player2Aview.setText(R.string.addPlayer);
                player3Aview.setText("");
                player4Aview.setText("");
                player5Aview.setText("");
                //final String finalMatchID = matchID;
                player1Aview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,ScoreView.class);
                        intent.putExtra("teamName", teamAview.getText());
                        intent.putExtra("teamAName", teamAview.getText());
                        intent.putExtra("teamBName", teamBview.getText());
                        intent.putExtra("playerName",player1Aview.getText() );
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }else { // if only one player
                 player1Aview.setText(R.string.addPlayer);
            }
            if (myPlayersA.size()-1>0) {
                player2Aview.setText(myPlayersA.get(1).getName());
                player3Aview.setText(R.string.addPlayer);
                player4Aview.setText("");
                player5Aview.setText("");
                //
                player2Aview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,ScoreView.class);
                        intent.putExtra("teamName", teamAview.getText());
                        intent.putExtra("teamAName", teamAview.getText());
                        intent.putExtra("teamBName", teamBview.getText());
                        intent.putExtra("playerName",player2Aview.getText() );
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }else{ // 2 player.. etc

                final String finalTeamA = teamA;
                final String finalTeamAname = teamA;
                final String finalTeamBname = teamB;
                player2Aview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,PlayerActivity.class);
                        intent.putExtra("teamName", finalTeamA);
                        intent.putExtra("teamNameA", finalTeamAname);
                        intent.putExtra("teamNameB", finalTeamBname);
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }
            if (myPlayersA.size()-1>1) {
                player3Aview.setText(myPlayersA.get(2).getName());
                player4Aview.setText(R.string.addPlayer);
                player5Aview.setText("");
                //
                player3Aview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,ScoreView.class);
                        intent.putExtra("teamName", teamAview.getText());
                        intent.putExtra("teamAName", teamAview.getText());
                        intent.putExtra("teamBName", teamBview.getText());
                        intent.putExtra("playerName",player3Aview.getText() );
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }else{
                final String finalTeamA = teamA;
                final String finalTeamAname = teamA;
                final String finalTeamBname = teamB;
                player3Aview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,PlayerActivity.class);
                        intent.putExtra("teamName", finalTeamA);
                        intent.putExtra("teamNameA", finalTeamAname);
                        intent.putExtra("teamNameB", finalTeamBname);
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
                //player3Aview.setText(R.string.addPlayer); // send to if part //// TODO: 08/04/2017
            }
            if (myPlayersA.size()-1>2) {
                player4Aview.setText(myPlayersA.get(3).getName());
                player5Aview.setText(R.string.addPlayer);
                //
                player4Aview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,ScoreView.class);
                        intent.putExtra("teamName", teamAview.getText());
                        intent.putExtra("teamAName", teamAview.getText());
                        intent.putExtra("teamBName", teamBview.getText());
                        intent.putExtra("playerName",player4Aview.getText() );
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }else{
                final String finalTeamA = teamA;
                final String finalTeamAname = teamA;
                final String finalTeamBname = teamB;
                player4Aview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,PlayerActivity.class);
                        intent.putExtra("teamName", finalTeamA);
                        intent.putExtra("teamNameA", finalTeamAname);
                        intent.putExtra("teamNameB", finalTeamBname);
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
                //player4Aview.setText(R.string.addPlayer);
            }
            if (myPlayersA.size()-1>3) {
                player5Aview.setText(myPlayersA.get(4).getName());
                //
                player5Aview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,ScoreView.class);
                        intent.putExtra("teamName", teamAview.getText());
                        intent.putExtra("teamAName", teamAview.getText());
                        intent.putExtra("teamBName", teamBview.getText());
                        intent.putExtra("playerName",player5Aview.getText() );
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }else{
                final String finalTeamA = teamA;
                final String finalTeamAname = teamA;
                final String finalTeamBname = teamB;
                player5Aview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,PlayerActivity.class);
                        intent.putExtra("teamName", finalTeamA);
                        intent.putExtra("teamNameA", finalTeamAname);
                        intent.putExtra("teamNameB", finalTeamBname);
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
                //player5Aview.setText(R.string.addPlayer);
            }
        }else{ // if no player at all
            player1Aview.setText(R.string.addPlayer);
            final String finalTeamA = teamA;
            final String finalTeamAname = teamA;
            final String finalTeamBname = teamB;
            player1Aview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(teamView.this,PlayerActivity.class);
                    intent.putExtra("teamName", finalTeamA);
                    intent.putExtra("teamNameA", finalTeamAname);
                    intent.putExtra("teamNameB", finalTeamBname);
                    intent.putExtra("matchID", matchID);
                    startActivity(intent);
                }
            });
            player2Aview.setText("");
            player3Aview.setText("");
            player4Aview.setText("");
            player5Aview.setText("");
        }
        ////
        ///TEAM B HANDLING
        ///
        List<Player> myPlayersB = new ArrayList<>();
        myPlayersB = playerDAO.getPlayerByTeam(teamB);
        if (myPlayersB != null) {//pour team B
            if (myPlayersB.size()-1>-1) {
                player1Bview.setText(myPlayersB.get(0).getName());
                player2Bview.setText(R.string.addPlayer);
                player3Bview.setText("");
                player4Bview.setText("");
                player5Bview.setText("");
                final String finalTeamB1 = teamB;
                //final String finalMatchID = matchID;
                player1Bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,ScoreView.class);
                        intent.putExtra("teamName", teamBview.getText());
                        intent.putExtra("teamAName", teamAview.getText());
                        intent.putExtra("teamBName", teamBview.getText());
                        intent.putExtra("playerName",player1Bview.getText() );
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }else { // if only one player
                player1Bview.setText(R.string.addPlayer);
            }
            if (myPlayersB.size()-1>0) {
                player2Bview.setText(myPlayersB.get(1).getName());
                player3Bview.setText(R.string.addPlayer);
                player4Bview.setText("");
                player5Bview.setText("");
                player2Bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,ScoreView.class);
                        intent.putExtra("teamName", teamBview.getText());
                        intent.putExtra("teamAName", teamAview.getText());
                        intent.putExtra("teamBName", teamBview.getText());
                        intent.putExtra("playerName",player2Bview.getText() );
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }else{ // 2 player.. etc

                final String finalTeamB = teamB;
                final String finalTeamAname = teamA;
                final String finalTeamBname = teamB;
                player2Bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,PlayerActivity.class);
                        intent.putExtra("teamName", finalTeamB);
                        intent.putExtra("teamNameA", finalTeamAname);
                        intent.putExtra("teamNameB", finalTeamBname);
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }
            if (myPlayersB.size()-1>1) {
                player3Bview.setText(myPlayersB.get(2).getName());
                player4Bview.setText(R.string.addPlayer);
                player5Bview.setText("");
                //
                player3Bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,ScoreView.class);
                        intent.putExtra("teamName", teamBview.getText());
                        intent.putExtra("teamAName", teamAview.getText());
                        intent.putExtra("teamBName", teamBview.getText());
                        intent.putExtra("playerName",player3Bview.getText() );
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }else{
                final String finalTeamB = teamB;
                final String finalTeamAname = teamA;
                final String finalTeamBname = teamB;
                player3Bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,PlayerActivity.class);
                        intent.putExtra("teamName", finalTeamB);
                        intent.putExtra("teamNameA", finalTeamAname);
                        intent.putExtra("teamNameB", finalTeamBname);
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
                //player3Bview.setText(R.string.addPlayer); // send to if part //// TODO: 08/04/2017
            }
            if (myPlayersB.size()-1>2) {
                player4Bview.setText(myPlayersB.get(3).getName());
                player5Bview.setText(R.string.addPlayer);
                //
                player4Bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,ScoreView.class);
                        intent.putExtra("teamName", teamBview.getText());
                        intent.putExtra("teamAName", teamAview.getText());
                        intent.putExtra("teamBName", teamBview.getText());
                        intent.putExtra("playerName",player4Bview.getText() );
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }else{
                final String finalTeamB = teamB;
                final String finalTeamAname = teamA;
                final String finalTeamBname = teamB;
                player4Bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,PlayerActivity.class);
                        intent.putExtra("teamName", finalTeamB);
                        intent.putExtra("teamNameA", finalTeamAname);
                        intent.putExtra("teamNameB", finalTeamBname);
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
                //player4Bview.setText(R.string.addPlayer);
            }
            if (myPlayersB.size()-1>3) {
                player5Bview.setText(myPlayersB.get(4).getName());
                //
                player5Bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,ScoreView.class);
                        intent.putExtra("teamName", teamBview.getText());
                        intent.putExtra("teamAName", teamAview.getText());
                        intent.putExtra("teamBName", teamBview.getText());
                        intent.putExtra("playerName",player5Bview.getText() );
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
            }else{
                final String finalTeamB = teamB;
                final String finalTeamAname = teamA;
                final String finalTeamBname = teamB;
                player5Bview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(teamView.this,PlayerActivity.class);
                        intent.putExtra("teamName", finalTeamB);
                        intent.putExtra("teamNameA", finalTeamAname);
                        intent.putExtra("teamNameB", finalTeamBname);
                        intent.putExtra("matchID", matchID);
                        startActivity(intent);
                    }
                });
                //player5Bview.setText(R.string.addPlayer);
            }
        }else{ // if no player at all
            player1Bview.setText(R.string.addPlayer);
            final String finalTeamB = teamB;
            final String finalTeamAname = teamA;
            final String finalTeamBname = teamB;
            player1Bview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(teamView.this,PlayerActivity.class);
                    intent.putExtra("teamName", finalTeamB);
                    intent.putExtra("teamNameA", finalTeamAname);
                    intent.putExtra("teamNameB", finalTeamBname);
                    intent.putExtra("matchID", matchID);
                    startActivity(intent);
                }
            });
            player2Bview.setText("");
            player3Bview.setText("");
            player4Bview.setText("");
            player5Bview.setText("");
        }
    }
    
}
