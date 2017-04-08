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
                    PLAYER_FIRST_NAME + " TEXT PRIMARY KEY, " +
                    PLAYER_NUMBER + " INTEGER, " +
                    PLAYER_TEAM_NAME + " TEXT NOT NULL ); ";// +
                    //"FOREIGN KEY(" + PLAYER_TEAM_NAME + ") REFERENCES " + TEAM_TABLE_NAME + "(" + TEAM_NAME +  ");";


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
                    MATCH_LOCATION_LATITUDE + " DOUBLE, " +
                    MATCH_LOCATION_LONGITUDE + " DOUBLE, " +
                    MATCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MATCH_DATE + " TEXT) " ;//+
    //"FOREIGN KEY(" + MATCH_TEAM_B + ") REFERENCES " + TEAM_TABLE_NAME + "(" + TEAM_NAME + ")," +
    //"FOREIGN KEY(" + MATCH_TEAM_A + ") REFERENCES " + TEAM_TABLE_NAME + "(" + TEAM_NAME + "));";

    //Table Score
    public static final String SCORE_MATCH = "match";
    public static final String SCORE_PLAYER = "player";
    public static final String SCORE_POINT = "point";
    public static final String SCORE_DECISIVE = "decisive";
    public static final String SCORE_REBOUND = "rebound";
    public static final String SCORE_COUNTER = "counter";
    public static final String SCORE_MINPLAY = "minutePlay";
    public static final String SCORE_ID = "id";

    public static final String SCORE_TABLE_NAME = "Match";

    public static final String SCORE_TABLE_CREATE =
            "CREATE TABLE " + SCORE_TABLE_NAME + " (" +
                    SCORE_MATCH + " TEXT, " +
                    SCORE_PLAYER + " TEXT, " +
                    SCORE_POINT + " INTEGER NOT NULL, " +
                    SCORE_DECISIVE + " INTEGER NOT NULL, " +
                    SCORE_REBOUND + " INTEGER NOT NULL, " +
                    SCORE_COUNTER + " INTEGER NOT NULL, " +
                    SCORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SCORE_MINPLAY + " INTEGER NOT NULL) " ;//+
    //"FOREIGN KEY(" + SCORE_PLAYER + ") REFERENCES " + TEAM_TABLE_NAME + "(" + TEAM_NAME + ")," +
    //"FOREIGN KEY(" + SCORE_MATCH + ") REFERENCES " + TEAM_TABLE_NAME + "(" + TEAM_NAME + "));";

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
        db.execSQL(SCORE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TEAM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MATCH_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SCORE_TABLE_NAME);

        // create new tables
        onCreate(db);
    }


}