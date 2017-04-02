package com.ece.aurelien.androidproject.Team;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ece.aurelien.androidproject.R;

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
        list = (ListView) teamDAO.getTeams();
    }
}
