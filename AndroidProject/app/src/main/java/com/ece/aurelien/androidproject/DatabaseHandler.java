package com.ece.aurelien.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ece.aurelien.androidproject.Team.Team;

/**
 * Created by Aurélien on 01/04/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    //Table equipe
    public static final String TEAM_NAME = "name";
    public static final String TEAM_LOCATION = "location";

    public static final String TEAM_TABLE_NAME = "Team";

    public static final String TEAM_TABLE_CREATE =
            "CREATE TABLE " + TEAM_TABLE_NAME + " (" +
                    TEAM_NAME + " TEXT PRIMARY KEY, " +
                    TEAM_LOCATION + " TEXT NOT NULL);";

    //Table Player
    public static final String PLAYER_NAME = "name";
    public static final String PLAYER_FIRST_NAME = "first_name";
    public static final String PLAYER_NUMBER = "number";
    public static final String PLAYER_TEAM_NAME = "team_name";

    public static final String PLAYER_TABLE_NAME = "Player";

    public static final String PLAYER_TABLE_CREATE =
            "CREATE TABLE " + PLAYER_TABLE_NAME + " (" +
                    PLAYER_NAME + " TEXT NOT NULL, " +
                    PLAYER_FIRST_NAME + " TEXT NOT NULL, " +
                    PLAYER_NUMBER + " INTEGER PRIMARY KEY, " +
                    PLAYER_TEAM_NAME + " TEXT PRIMARY KEY), " +
                    "FOREIGN KEY(" + PLAYER_TEAM_NAME + ") REFERENCES" + TEAM_TABLE_NAME + "(" + TEAM_NAME + ");";


    //Table Match
    public static final String MATCH_TEAM_A = "team_A";
    public static final String MATCH_TEAM_B = "team_B";
    public static final String MATCH_RESULT = "result";
    public static final String MATCH_LOCATION_LATITUDE = "location_latitude";
    public static final String MATCH_LOCATION_LONGITUDE = "location_longitude";
    public static final String MATCH_DATE = "date_hour";
    public static final String MATCH_ID = "id";

    public static final String MATCH_TABLE_NAME = "Match";

    public static final String MATCH_TABLE_CREATE =
            "CREATE TABLE " + MATCH_TABLE_NAME + " (" +
                    MATCH_TEAM_A + " TEXT, " +
                    MATCH_TEAM_B + " TEXT, " +
                    MATCH_RESULT + " INTEGER NOT NULL, " +
                    MATCH_LOCATION_LATITUDE + " INTEGER, " +
                    MATCH_LOCATION_LONGITUDE + " INTEGER, " +
                    MATCH_ID + " INTEGER PRIMARY KEY, " +
                    MATCH_DATE + " TEXT), " +
                    "FOREIGN KEY(" + MATCH_TEAM_B + ") REFERENCES" + TEAM_TABLE_NAME + "(" + TEAM_NAME + ")," +
                    "FOREIGN KEY(" + MATCH_TEAM_A + ") REFERENCES" + TEAM_TABLE_NAME + "(" + TEAM_NAME + "));";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TEAM_TABLE_CREATE);
        db.execSQL(PLAYER_TABLE_CREATE);
        db.execSQL(MATCH_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TEAM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MATCH_TABLE_NAME);

        // create new tables
        onCreate(db);
    }


    /*
 * Creating a Team
 */
    public void createToDo(Team myTeam) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEAM_NAME, myTeam.getName());
        values.put(TEAM_LOCATION, myTeam);

        // insert row
        db.insert(TEAM_TABLE_NAME, null, values);

    }
}