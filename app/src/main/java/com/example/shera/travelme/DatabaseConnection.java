//package com.example.shera.travelme;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//
//public class DatabaseConnection extends SQLiteOpenHelper {
//
//    // Logcat tag
//    private static final String LOG = "DatabaseHelper";
//
//    // Database Version
//    private static final int DATABASE_VERSION = 1;
//
//    // Database Name
//    private static final String DATABASE_NAME = "TravelMeDB";
//
//    // Table Names
//    private static final String TABLE_ACCOUNT = "Account";
//    private static final String TABLE_JOURNEY = "Journey";
//    private static final String TABLE_LOCATION = "Location";
//    private static final String TABLE_USER = "User";
//
//
//    // Table Create Statements
//    // Todo table create statement
//    private static final String CREATE_TABLE_USER = "CREATE TABLE "
//            + TABLE_USER + "(id varchar(10) PRIMARY KEY, userName";
//
//    // Tag table create statement
//    private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
//            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME + " TEXT,"
//            + KEY_CREATED_AT + " DATETIME" + ")";
//
//    // todo_tag table create statement
//    private static final String CREATE_TABLE_TODO_TAG = "CREATE TABLE "
//            + TABLE_TODO_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
//            + KEY_TODO_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
//            + KEY_CREATED_AT + " DATETIME" + ")";
//
//    public DatabaseConnection(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        // creating required tables
//        db.execSQL(CREATE_TABLE_TODO);
//        db.execSQL(CREATE_TABLE_TAG);
//        db.execSQL(CREATE_TABLE_TODO_TAG);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // on upgrade drop older tables
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO_TAG);
//
//        // create new tables
//        onCreate(db);
//    }
//}
