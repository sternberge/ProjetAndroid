package com.ece.aurelien.androidproject.Player;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ece.aurelien.androidproject.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import static com.ece.aurelien.androidproject.DatabaseHandler.PLAYER_FIRST_NAME;
import static com.ece.aurelien.androidproject.DatabaseHandler.PLAYER_NAME;
import static com.ece.aurelien.androidproject.DatabaseHandler.PLAYER_NUMBER;
import static com.ece.aurelien.androidproject.DatabaseHandler.PLAYER_TABLE_NAME;
import static com.ece.aurelien.androidproject.DatabaseHandler.PLAYER_TEAM_NAME;

/**
 * Created by Aurélien on 01/04/2017.
 */

public class PlayerDAO {
    private DatabaseHandler myDatabaseHandler;
    private SQLiteDatabase mydb;
    private Context mContext;

    public PlayerDAO(Context context){
        this.mContext = context;
        myDatabaseHandler = DatabaseHandler.getHelper(mContext);
        open();
    }


    public  void open() throws SQLException {
        if(myDatabaseHandler == null)
            myDatabaseHandler = DatabaseHandler.getHelper(mContext);
        mydb = myDatabaseHandler.getWritableDatabase();
    }

    /*
* Creating a Team
*/
    public  Boolean createPlayer(Player myPlayer){

        ContentValues values = new ContentValues();
        values.put(PLAYER_NAME, myPlayer.getName());
        values.put(PLAYER_FIRST_NAME, myPlayer.getFirstName());
        values.put(PLAYER_NUMBER, myPlayer.getNumber());
        values.put(PLAYER_TEAM_NAME, myPlayer.getTeamName());

        // insert row
        long test = this.mydb.insert(PLAYER_TABLE_NAME, null, values);
            if (test==-1){
                return false;
            }else {
                return true;
            }


    }
    public int deletePlayer(Player myPlayer) {
        return mydb.delete(DatabaseHandler.PLAYER_TABLE_NAME,
                DatabaseHandler.PLAYER_NUMBER, new String[] { myPlayer.getNumber() + "" });
    }

    public List<Player> getPlayerByTeam(String datTeam) {
        List<Player> players = new ArrayList<>();
        String where = DatabaseHandler.PLAYER_TEAM_NAME + " = ?" ;
        String[] args = new String[] {datTeam};
        Cursor cursor = mydb.query(DatabaseHandler.PLAYER_TABLE_NAME,
                new String[] { DatabaseHandler.PLAYER_NAME,
                        DatabaseHandler.PLAYER_FIRST_NAME,
                        DatabaseHandler.PLAYER_NUMBER}, where, args, null, null, null,
                null);
        if (cursor.getCount() !=0) {
            while (cursor.moveToNext()) {
                Player myPlayer = new Player();
                myPlayer.setName(cursor.getString(0));
                myPlayer.setFirstName(cursor.getString(1));
                myPlayer.setNumber(Integer.valueOf(cursor.getString(2)));
                players.add(myPlayer);
            }
        }else{
            return null;
        }
        return players;
    }

}
