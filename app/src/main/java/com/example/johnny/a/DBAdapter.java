package com.example.johnny.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static android.provider.Contacts.SettingsColumns.KEY;

public class DBAdapter {

    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_PH = "phone";
    static final String ID = "Sid";
    static final String EMAIL = "email";
    static final String DEPT = "dept";
    static final String PASS = "pass";
    static final String DATABASE_NAME = "Leave_mmt";
    static final String DATABASE_TABLE = "signup";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE =
            "create table signup(_id integer primary key autoincrement," +
                    " name text not null, Sid text not null,email text not null,dept  text not null,phone text not null,pass int not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS signup");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        DBHelper.close();
    }

    //---insert a contact into the database---
    public long insertContact(String name, String id, String email, String dept, String ph, String p) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_PH, ph);
        initialValues.put(ID, id);
        initialValues.put(DEPT, dept);
        initialValues.put(EMAIL, email);
        initialValues.put(PASS, p);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    //---deletes a particular contact---

    public Cursor getnamepass() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, ID, EMAIL, DEPT, KEY_PH,
                PASS}, null, null, null, null, null,null);
    }


    //---retrieves a particular contact---
    public Cursor getContact(long rowId) throws SQLException {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, ID, EMAIL, DEPT,
                KEY_PH, PASS}, KEY + "=" + rowId, null, null, null, null, null,null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /*public void updateContact(long rowId,int b)
    {
        ContentValues args = new ContentValues();
        args.put(BAL,b);
        db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null);
    }
}*/

//---updates a contact---
}