package com.ece.aurelien.androidproject.Score;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ece.aurelien.androidproject.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import static com.ece.aurelien.androidproject.DatabaseHandler.SCORE_COUNTER;
import static com.ece.aurelien.androidproject.DatabaseHandler.SCORE_DECISIVE;
import static com.ece.aurelien.androidproject.DatabaseHandler.SCORE_INTERCEPTION;
import static com.ece.aurelien.androidproject.DatabaseHandler.SCORE_MATCH;
import static com.ece.aurelien.androidproject.DatabaseHandler.SCORE_MINPLAY;
import static com.ece.aurelien.androidproject.DatabaseHandler.SCORE_PLAYER;
import static com.ece.aurelien.androidproject.DatabaseHandler.SCORE_POINT;
import static com.ece.aurelien.androidproject.DatabaseHandler.SCORE_REBOUND;
import static com.ece.aurelien.androidproject.DatabaseHandler.SCORE_TABLE_NAME;

/**
 * Created by win8 on 08/04/2017.
 */

public class ScoreDAO {
    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;
    Context context;

    public ScoreDAO(Context context){
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

    public List<Score> getScore(String datMatch,String datPlayer) {
        List<Score> scores = new ArrayList<>();
        String where = DatabaseHandler.SCORE_MATCH + " = ? AND " + DatabaseHandler.SCORE_PLAYER + " = ?" ;
        String[] args = new String[] {datMatch,datPlayer};
        Cursor cursor = database.query(DatabaseHandler.SCORE_TABLE_NAME,
                new String[] { DatabaseHandler.SCORE_POINT,
                        DatabaseHandler.SCORE_DECISIVE,
                        DatabaseHandler.SCORE_REBOUND,
                        DatabaseHandler.SCORE_COUNTER,
                        DatabaseHandler.SCORE_INTERCEPTION,
                        DatabaseHandler.SCORE_MINPLAY}, where, args, null, null, null,
                null);
        if (cursor.getCount() !=0) {
            while (cursor.moveToNext()) {
                Score myScore = new Score();
                myScore.setPoint(Integer.valueOf(cursor.getString(0)));
                myScore.setDecisive(Integer.valueOf(cursor.getString(1)));
                myScore.setRebound(Integer.valueOf(cursor.getString(2)));
                myScore.setCounter(Integer.valueOf(cursor.getString(3)));
                myScore.setInterception(Integer.valueOf(cursor.getString(4)));
                myScore.setMinuteplay(Integer.valueOf(cursor.getString(5)));
                scores.add(myScore);
            }
        }else{
            return null;
        }
        return scores;
    }
    /*
* Creating a Score
*/
    public void createScore(Score myScore) {


        ContentValues values = new ContentValues();
        values.put(SCORE_MATCH, myScore.getMatch());
        values.put(SCORE_PLAYER, myScore.getPlayer());
        values.put(SCORE_POINT, myScore.getPoint());
        values.put(SCORE_DECISIVE, myScore.getDecisive());
        values.put(SCORE_REBOUND,myScore.getRebound());
        values.put(SCORE_COUNTER,myScore.getCounter());
        values.put(SCORE_INTERCEPTION,myScore.getInterception());
        // a modifier
        values.put(SCORE_MINPLAY, myScore.getMinuteplay());

        // insert row
        this.database.insert(SCORE_TABLE_NAME, null, values);
    }
}
