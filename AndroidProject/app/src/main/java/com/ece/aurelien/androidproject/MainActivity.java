package com.ece.aurelien.androidproject;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ece.aurelien.androidproject.Match.Match;
import com.ece.aurelien.androidproject.Match.MatchDAO;
import com.ece.aurelien.androidproject.Match.MatchView;
import com.ece.aurelien.androidproject.Team.teamView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView addMatchLogo;
    TextView matchAddInfo;
    TextView matchTwoPic,
            matchTwoTeamOne,
            matchTwoTeamTwo,
            matchTwoScoreA,
            matchTwoScoreB;
    TextView matchThreePic,
            matchThreeTeamOne,
            matchThreeTeamTwo,
            matchThreeScoreA,
            matchThreeScoreB;
    TextView matchFourPic,
            matchFourTeamOne,
            matchFourTeamTwo,
            matchFourScoreA,
            matchFourScoreB;
    TextView matchFivePic,
            matchFiveTeamOne,
            matchFiveTeamTwo,
            matchFiveScoreA,
            matchFiveScoreB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addMatchLogo = (ImageView) findViewById(R.id.addMatchLogo);
        matchAddInfo = (TextView) findViewById(R.id.matchAddInfo);
        // add a match
        addMatchLogo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMatch();
            }
        });
        matchAddInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addMatch();
            }
        });
        matchAddInfo.setText(" < Ajoutez un match");


       // matchTwoPic = (TextView) findViewById(R.id.matchTwoPic);
        matchTwoTeamOne = (TextView) findViewById(R.id.matchTwoTeamOne);
        matchTwoTeamTwo = (TextView) findViewById(R.id.matchTwoTeamTwo);
        matchTwoScoreA = (TextView) findViewById(R.id.matchTwoScoreA);
        matchTwoScoreB = (TextView) findViewById(R.id.matchTwoScoreB);



                //declaring webview here
        WebView imgloc = (WebView) findViewById(R.id.imgloc);
        imgloc.getSettings().setJavaScriptEnabled(true);
        imgloc.getSettings().setLoadWithOverviewMode(true);
        imgloc.getSettings().setUseWideViewPort(true);

        WebView imgloc2 = (WebView) findViewById(R.id.imgloc2);
        imgloc2.getSettings().setJavaScriptEnabled(true);
        imgloc2.getSettings().setLoadWithOverviewMode(true);
        imgloc2.getSettings().setUseWideViewPort(true);

        WebView imgloc3 = (WebView) findViewById(R.id.imgloc3);
        imgloc3.getSettings().setJavaScriptEnabled(true);
        imgloc3.getSettings().setLoadWithOverviewMode(true);
        imgloc3.getSettings().setUseWideViewPort(true);

        WebView imgloc4 = (WebView) findViewById(R.id.imgloc4);
        imgloc4.getSettings().setJavaScriptEnabled(true);
        imgloc4.getSettings().setLoadWithOverviewMode(true);
        imgloc4.getSettings().setUseWideViewPort(true);
        //authorizing;
        imgloc.setWebViewClient(new SSLTolerantWebViewClient());
        imgloc2.setWebViewClient(new SSLTolerantWebViewClient());
        imgloc3.setWebViewClient(new SSLTolerantWebViewClient());
        imgloc4.setWebViewClient(new SSLTolerantWebViewClient());




        //matchThreePic = (TextView) findViewById(R.id.matchThreePic);
        matchThreeTeamOne = (TextView) findViewById(R.id.matchThreeTeamOne);
        matchThreeTeamTwo = (TextView) findViewById(R.id.matchThreeTeamTwo);
        matchThreeScoreA = (TextView) findViewById(R.id.matchThreeScoreA);
        matchThreeScoreB = (TextView) findViewById(R.id.matchThreeScoreB);

        //matchFourPic = (TextView) findViewById(R.id.matchFourPic);
        matchFourTeamOne = (TextView) findViewById(R.id.matchFourTeamOne);
        matchFourTeamTwo = (TextView) findViewById(R.id.matchFourTeamTwo);
        matchFourScoreA = (TextView) findViewById(R.id.matchFourScoreA);
        matchFourScoreB = (TextView) findViewById(R.id.matchFourScoreB);

        //matchFivePic = (TextView) findViewById(R.id.matchFivePic);
        matchFiveTeamOne = (TextView) findViewById(R.id.matchFiveTeamOne);
        matchFiveTeamTwo = (TextView) findViewById(R.id.matchFiveTeamTwo);
        matchFiveScoreA = (TextView) findViewById(R.id.matchFiveScoreA);
        matchFiveScoreB = (TextView) findViewById(R.id.matchFiveScoreB);

        // show match from database ( last from the list first)
        MatchDAO matchDAO= new MatchDAO(getApplicationContext());
        List<Match> myMatch =  matchDAO.getAllMatch();
        int i=myMatch.size()-1;
        if (i>=0) {
            matchTwoTeamOne.setText(myMatch.get(i).getTeamA());
            matchTwoTeamTwo.setText(myMatch.get(i).getTeamB());
            matchTwoScoreA.setText(String.valueOf(myMatch.get(i).getResultA()));
            matchTwoScoreB.setText(String.valueOf(myMatch.get(i).getResultB()));
            final double latitude = myMatch.get(i).getLatitude();
            final double longitude = myMatch.get(i).getLongitude();
            imgloc.loadUrl("http://maps.googleapis.com/maps/api/staticmap?center="
                    +String.valueOf(myMatch.get(i).getLatitude())
                    +","
                    +String.valueOf(myMatch.get(i).getLongitude())
                    +"&zoom=3&size=80x80");
            imgloc.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MapsActivityforMain.class);
                    intent.putExtra("latitudeSee",String.valueOf(latitude));
                    intent.putExtra("longitudeSee",String.valueOf(longitude));
                    startActivity(intent);
                    return false;
                }
            });
            LinearLayout openteam1 = (LinearLayout) findViewById(R.id.openteam1);
            final int currentMatch = myMatch.get(i).getId();
            openteam1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, teamView.class);
                    intent.putExtra("teamASend",matchTwoTeamOne.getText());
                    intent.putExtra("teamBSend",matchTwoTeamTwo.getText());
                    intent.putExtra("matchID",String.valueOf(currentMatch));
                    startActivity(intent);
                }
            });
            i--;
        }else {
            matchTwoTeamOne.setText("Pas de match en BDD");
        }
        if (i>=0) {
            matchThreeTeamOne.setText(myMatch.get(i).getTeamA());
            matchThreeTeamTwo.setText(myMatch.get(i).getTeamB());
            matchThreeScoreA.setText(String.valueOf(myMatch.get(i).getResultA()));
            matchThreeScoreB.setText(String.valueOf(myMatch.get(i).getResultB()));
            final double latitude = myMatch.get(i).getLatitude();
            final double longitude = myMatch.get(i).getLongitude();
            imgloc2.loadUrl("http://maps.googleapis.com/maps/api/staticmap?center="
                    +String.valueOf(myMatch.get(i).getLatitude())
                    +","
                    +String.valueOf(myMatch.get(i).getLongitude())
                    +"&zoom=3&size=80x80");
            imgloc2.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MapsActivityforMain.class);
                    intent.putExtra("latitudeSee",String.valueOf(latitude));
                    intent.putExtra("longitudeSee",String.valueOf(longitude));
                    startActivity(intent);
                    return false;
                }
            });
            LinearLayout openteam2 = (LinearLayout) findViewById(R.id.openteam2);
            final int currentMatch = myMatch.get(i).getId();
            openteam2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, teamView.class);
                    intent.putExtra("teamASend",matchThreeTeamOne.getText());
                    intent.putExtra("teamBSend",matchThreeTeamTwo.getText());
                    intent.putExtra("matchID",String.valueOf(currentMatch));
                    startActivity(intent);
                }
            });
            i--;
        }else {
            matchThreeTeamOne.setText("Pas de match en BDD");
        }
        if (i>=0) {
            matchFourTeamOne.setText(myMatch.get(i).getTeamA());
            matchFourTeamTwo.setText(myMatch.get(i).getTeamB());
            matchFourScoreA.setText(String.valueOf(myMatch.get(i).getResultA()));
            matchFourScoreB.setText(String.valueOf(myMatch.get(i).getResultB()));
            final double latitude = myMatch.get(i).getLatitude();
            final double longitude = myMatch.get(i).getLongitude();
            imgloc3.loadUrl("http://maps.googleapis.com/maps/api/staticmap?center="
                    +String.valueOf(myMatch.get(i).getLatitude())
                    +","
                    +String.valueOf(myMatch.get(i).getLongitude())
                    +"&zoom=3&size=80x80");
            imgloc3.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MapsActivityforMain.class);
                    intent.putExtra("latitudeSee",String.valueOf(latitude));
                    intent.putExtra("longitudeSee",String.valueOf(longitude));
                    startActivity(intent);
                    return false;
                }
            });
            LinearLayout openteam3 = (LinearLayout) findViewById(R.id.openteam3);
            final int currentMatch = myMatch.get(i).getId();
            openteam3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, teamView.class);
                    intent.putExtra("teamASend",matchFourTeamOne.getText());
                    intent.putExtra("teamBSend",matchFourTeamTwo.getText());
                    intent.putExtra("matchID",String.valueOf(currentMatch));
                    startActivity(intent);
                }
            });
            i--;
        }else {
            matchFourTeamOne.setText("Pas de match en BDD");
        }
        if (i>=0) {
            matchFiveTeamOne.setText(myMatch.get(i).getTeamA());
            matchFiveTeamTwo.setText(myMatch.get(i).getTeamB());
            matchFiveScoreA.setText(String.valueOf(myMatch.get(i).getResultA()));
            matchFiveScoreB.setText(String.valueOf(myMatch.get(i).getResultB()));
            final double latitude = myMatch.get(i).getLatitude();
            final double longitude = myMatch.get(i).getLongitude();
            imgloc4.loadUrl("http://maps.googleapis.com/maps/api/staticmap?center="
                    +String.valueOf(myMatch.get(i).getLatitude())
                    +","
                    +String.valueOf(myMatch.get(i).getLongitude())
                    +"&zoom=3&size=80x80");
            imgloc4.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MapsActivityforMain.class);
                    intent.putExtra("latitudeSee",String.valueOf(latitude));
                    intent.putExtra("longitudeSee",String.valueOf(longitude));
                    startActivity(intent);
                    return false;
                }
            });
            LinearLayout openteam4 = (LinearLayout) findViewById(R.id.openteam4);
            final int currentMatch = myMatch.get(i).getId();
            openteam4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, teamView.class);
                    intent.putExtra("teamASend",matchFiveTeamOne.getText());
                    intent.putExtra("teamBSend",matchFiveTeamTwo.getText());
                    intent.putExtra("matchID",String.valueOf(currentMatch));
                    startActivity(intent);
                }
            });
        }else {
            matchFiveTeamOne.setText("Pas de match en BDD");
        }
        //

        }
        public void test(String latitude,String longitude){

        }
        public void addMatch(){
            Intent ret = new Intent(MainActivity.this, MatchView.class);
            startActivity(ret);
        }
        //handle ssl lock warning: not very safe but ok for this project
        private class SSLTolerantWebViewClient extends WebViewClient{
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                handler.proceed();
            }
        }

    }
