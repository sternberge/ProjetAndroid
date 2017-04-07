package com.ece.aurelien.androidproject.Match;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ece.aurelien.androidproject.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import static com.ece.aurelien.androidproject.DatabaseHandler.MATCH_DATE;
import static com.ece.aurelien.androidproject.DatabaseHandler.MATCH_LOCATION_LATITUDE;
import static com.ece.aurelien.androidproject.DatabaseHandler.MATCH_LOCATION_LONGITUDE;
import static com.ece.aurelien.androidproject.DatabaseHandler.MATCH_RESULT_A;
import static com.ece.aurelien.androidproject.DatabaseHandler.MATCH_RESULT_B;
import static com.ece.aurelien.androidproject.DatabaseHandler.MATCH_TABLE_NAME;
import static com.ece.aurelien.androidproject.DatabaseHandler.MATCH_TEAM_A;
import static com.ece.aurelien.androidproject.DatabaseHandler.MATCH_TEAM_B;

/**
 * Created by Aurélien on 01/04/2017.
 */

public class MatchDAO {

    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;
    Context context;
    private String[] allColumns = {DatabaseHandler.MATCH_TEAM_A,
                                   DatabaseHandler.MATCH_TEAM_B,
                                    DatabaseHandler.MATCH_RESULT_A,
                                    DatabaseHandler.MATCH_RESULT_B,
                                    DatabaseHandler.MATCH_LOCATION_LATITUDE,
                                    DatabaseHandler.MATCH_LOCATION_LONGITUDE,
                                    DatabaseHandler.MATCH_DATE,
                                    DatabaseHandler.MATCH_ID};
    private String[] teamAColumn = {DatabaseHandler.MATCH_TEAM_A};

    public MatchDAO(Context context){
        this.context = context;
        dbHelper = DatabaseHandler.getHelper(context);
        open();
    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DatabaseHandler.getHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // getting a list of all match to show in mainactivity
    public List<Match> getAllMatch() {
        List<Match> match = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHandler.MATCH_TABLE_NAME,
                new String[] { DatabaseHandler.MATCH_TEAM_A,
                        DatabaseHandler.MATCH_TEAM_B,
                        DatabaseHandler.MATCH_RESULT_A,
                        DatabaseHandler.MATCH_RESULT_B,
                        DatabaseHandler.MATCH_LOCATION_LATITUDE,
                        DatabaseHandler.MATCH_LOCATION_LONGITUDE}, DatabaseHandler.MATCH_ID, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Match myMatch = new Match();
            myMatch.setTeamA(cursor.getString(0));
            myMatch.setTeamB(cursor.getString(1));
            myMatch.setResultA(cursor.getInt(2));
            myMatch.setResultB(cursor.getInt(3));
            myMatch.setLatitude(cursor.getDouble(4));
            myMatch.setLongitude(cursor.getDouble(5));
            match.add(myMatch);
        }
        return match;
    }
    //
    /*
* Creating a Match
*/
    public void createMatch(Match myMatch) {


        ContentValues values = new ContentValues();
        values.put(MATCH_TEAM_A, myMatch.getTeamA());
        values.put(MATCH_TEAM_B, myMatch.getTeamB());
        values.put(MATCH_RESULT_A, myMatch.getResultA());
        values.put(MATCH_RESULT_B, myMatch.getResultB());
        values.put(MATCH_LOCATION_LATITUDE,myMatch.getLatitude());
        values.put(MATCH_LOCATION_LONGITUDE,myMatch.getLongitude());
        // a modifier
        values.put(MATCH_DATE, myMatch.getDateTime());

        // insert row
        this.database.insert(MATCH_TABLE_NAME, null, values);

    }

}
