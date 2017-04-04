package com.ece.aurelien.androidproject.Team;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ece.aurelien.androidproject.R;

import java.util.ArrayList;
import java.util.List;

public class TeamsListActivity extends AppCompatActivity {
    ListView list;
    SQLiteDatabase sqLiteDatabase;
    TeamDAO teamDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams_list_activity);
        list = (ListView) findViewById(R.id.list_view);
        teamDAO = new TeamDAO(getApplicationContext());
        List <Team> myTeams = new ArrayList<>();
        myTeams = teamDAO.getTeams();

        TeamAdapter adapter = new TeamAdapter(this,myTeams);
        list.setAdapter(adapter);
    }
}
