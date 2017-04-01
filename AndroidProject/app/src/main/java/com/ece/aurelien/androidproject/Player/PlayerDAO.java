package com.ece.aurelien.androidproject.Player;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ece.aurelien.androidproject.DatabaseHandler;

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


    public void open() throws SQLException {
        if(myDatabaseHandler == null)
            myDatabaseHandler = DatabaseHandler.getHelper(mContext);
        mydb = myDatabaseHandler.getWritableDatabase();
    }
}
