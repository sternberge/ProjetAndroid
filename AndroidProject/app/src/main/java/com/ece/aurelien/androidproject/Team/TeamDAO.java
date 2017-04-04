package com.ece.aurelien.androidproject.Team;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ece.aurelien.androidproject.DatabaseHandler;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import static com.ece.aurelien.androidproject.DatabaseHandler.TEAM_CLASSEMENT;
import static com.ece.aurelien.androidproject.DatabaseHandler.TEAM_LOCATION;
import static com.ece.aurelien.androidproject.DatabaseHandler.TEAM_NAME;
import static com.ece.aurelien.androidproject.DatabaseHandler.TEAM_TABLE_NAME;

/**
 * Created by Aurélien on 01/04/2017.
 */

public class TeamDAO {

    private DatabaseHandler myDatabaseHandler;
    private SQLiteDatabase mydb;
    private Context mContext;

    public TeamDAO(Context context){
        this.mContext = context;
        myDatabaseHandler = DatabaseHandler.getHelper(mContext);
        open();
    }


    public void open() throws SQLException {
        if(myDatabaseHandler == null)
            myDatabaseHandler = DatabaseHandler.getHelper(mContext);
        mydb = myDatabaseHandler.getWritableDatabase();
    }


    /*
 * Creating a Team
 */
    public void createTeam(Team myTeam) {


        ContentValues values = new ContentValues();
        values.put(TEAM_NAME, myTeam.getName());
        values.put(TEAM_LOCATION, myTeam.getLocation());
        values.put(TEAM_CLASSEMENT, myTeam.getClassement());

        // insert row
        this.mydb.insert(TEAM_TABLE_NAME, null, values);

    }

    public int deleteTeam(Team myTeam) {
        return mydb.delete(DatabaseHandler.TEAM_TABLE_NAME,
                DatabaseHandler.TEAM_NAME, new String[] { myTeam.getName() + "" });
    }

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<Team>();
        Cursor cursor = mydb.query(DatabaseHandler.TEAM_TABLE_NAME,
                new String[] { DatabaseHandler.TEAM_NAME,
                        DatabaseHandler.TEAM_LOCATION}, DatabaseHandler.TEAM_CLASSEMENT, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Team myTeam = new Team();
            myTeam.setName(cursor.getString(0));
            myTeam.setLocation(cursor.getString(1));
            teams.add(myTeam);
        }
        return teams;
    }


}
