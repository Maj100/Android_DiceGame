package uk.ac.ljmu.asstwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB_DICE";
    private static final String TABLE_DICE = "DICE";
    private static final String KEY_ID = "id";
    private static final String totalTurnRem = "totalTurnRem";
    private static final String player1Turn = "player1Turn";
    private static final String player2Turn = "player2Turn";
    private static final String player1Value = "player1Value";
    private static final String player2Value = "player2Value";
    private static final String playerState = "playerState";

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DICE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + totalTurnRem + " INTEGER,"+ player1Turn + " INTEGER,"+ player2Turn + " INTEGER,"
                + player1Value + " INTEGER,"+ player2Value + " INTEGER, "+ playerState + " INTEGER)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICE);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void saveGameState(GameState game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, game.getId()); // id
        values.put(totalTurnRem, game.getTotalTurnRem());
        values.put(player1Turn, game.getPlayer1Turn());
        values.put(player2Turn, game.getPlayer2Turn());
        values.put(player1Value, game.getPlayer1Value());
        values.put(player2Value, game.getPlayer2Value());
        values.put(playerState, game.getPlayerState());

        // Inserting Row
        db.insert(TABLE_DICE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the saved data
    GameState getGameState(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DICE, new String[] { KEY_ID,
                        totalTurnRem, player1Turn,player2Turn,player1Value,player2Value,playerState }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        GameState gmaeState=new GameState();
        gmaeState.setId(Integer.parseInt(cursor.getString(0)));
        gmaeState.setTotalTurnRem(Integer.parseInt(cursor.getString(1)));
        gmaeState.setPlayer1Turn(Integer.parseInt(cursor.getString(2)));
        gmaeState.setPlayer2Turn(Integer.parseInt(cursor.getString(3)));
        gmaeState.setPlayer1Value(Integer.parseInt(cursor.getString(4)));
        gmaeState.setPlayer2Value(Integer.parseInt(cursor.getString(5)));
        gmaeState.setPlayerState(Integer.parseInt(cursor.getString(6)));
        return gmaeState;
    }

    // code to update the single contact
    public int updateContact(GameState game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, game.getId()); // id
        values.put(totalTurnRem, game.getTotalTurnRem());
        values.put(player1Turn, game.getPlayer1Turn());
        values.put(player2Turn, game.getPlayer2Turn());
        values.put(player1Value, game.getPlayer1Value());
        values.put(player2Value, game.getPlayer2Value());
        values.put(playerState, game.getPlayerState());
        // updating row
        return db.update(TABLE_DICE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(game.getId()) });
    }
    // Getting contacts Count
    public int isExist() {
        String countQuery = "SELECT  * FROM " + TABLE_DICE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int res=cursor.getCount();
        cursor.close();

        // return count
        return res;
    }
}

