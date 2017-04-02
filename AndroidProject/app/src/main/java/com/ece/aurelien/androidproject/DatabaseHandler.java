package com.ece.aurelien.androidproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aurélien on 01/04/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database";

    //Table equipe
    public static final String TEAM_NAME = "name";
    public static final String TEAM_LOCATION = "location";
    public static final String TEAM_CLASSEMENT = "classement";

    public static final String TEAM_TABLE_NAME = "Team";

    public static final String TEAM_TABLE_CREATE =
            "CREATE TABLE " + TEAM_TABLE_NAME + " (" +
                    TEAM_NAME + " TEXT PRIMARY KEY, " +
                    TEAM_CLASSEMENT + " INTEGER, " +
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
                    PLAYER_NUMBER + " INTEGER, " +
                    PLAYER_TEAM_NAME + " TEXT PRIMARY KEY); "; //+
                   // "FOREIGN KEY(" + PLAYER_TEAM_NAME + ") REFERENCES " + TEAM_TABLE_NAME + "(" + TEAM_NAME + ");";


    //Table Match
    public static final String MATCH_TEAM_A = "team_A";
    public static final String MATCH_TEAM_B = "team_B";
    public static final String MATCH_RESULT_A = "result_A";
    public static final String MATCH_RESULT_B = "result_B";
    public static final String MATCH_LOCATION_LATITUDE = "location_latitude";
    public static final String MATCH_LOCATION_LONGITUDE = "location_longitude";
    public static final String MATCH_DATE = "date_hour";
    public static final String MATCH_ID = "id";

    public static final String MATCH_TABLE_NAME = "Match";

    public static final String MATCH_TABLE_CREATE =
            "CREATE TABLE " + MATCH_TABLE_NAME + " (" +
                    MATCH_TEAM_A + " TEXT, " +
                    MATCH_TEAM_B + " TEXT, " +
                    MATCH_RESULT_A + " INTEGER NOT NULL, " +
                    MATCH_RESULT_B + " INTEGER NOT NULL, " +
                    MATCH_LOCATION_LATITUDE + " INTEGER, " +
                    MATCH_LOCATION_LONGITUDE + " INTEGER, " +
                    MATCH_ID + " INTEGER PRIMARY KEY AUTO INCREMENT, " +
                    MATCH_DATE + " TEXT, " +
                    "FOREIGN KEY(" + MATCH_TEAM_B + ") REFERENCES " + TEAM_TABLE_NAME + "(" + TEAM_NAME + ")," +
                    "FOREIGN KEY(" + MATCH_TEAM_A + ") REFERENCES " + TEAM_TABLE_NAME + "(" + TEAM_NAME + "));";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private static DatabaseHandler instance;

    public static synchronized DatabaseHandler getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHandler(context);
        return instance;
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


}