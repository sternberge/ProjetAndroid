package com.ece.aurelien.androidproject.Match;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ece.aurelien.androidproject.DatabaseHandler;

/**
 * Created by Aurélien on 01/04/2017.
 */

public class MatchDAO {

    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;
    private String[] allColumns = {DatabaseHandler.MATCH_TEAM_A,
                                   DatabaseHandler.MATCH_TEAM_B,
                                    DatabaseHandler.MATCH_RESULT,
                                    DatabaseHandler.MATCH_LOCATION_LATITUDE,
                                    DatabaseHandler.MATCH_LOCATION_LONGITUDE,
                                    DatabaseHandler.MATCH_DATE,
                                    DatabaseHandler.MATCH_ID};
    private String[] teamAColumn = {DatabaseHandler.MATCH_TEAM_A};
    public MatchDAO(Context context){
        dbHelper = new DatabaseHandler(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public Match getTeamA(){

        Cursor cursor = database.query(DatabaseHandler.MATCH_TABLE_NAME,allColumns,null,null,null,null,null,null);
        cursor.moveToFirst();
        Match match = new Match();
        match.getTeamA();
        cursor.close();
        return match;
    }

}
