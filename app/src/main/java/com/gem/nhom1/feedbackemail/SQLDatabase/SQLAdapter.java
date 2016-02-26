package com.gem.nhom1.feedbackemail.SQLDatabase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gem.nhom1.feedbackemail.commom.Constant;

/**
 * Created by phuongtd on 19/02/2016.
 */
public class SQLAdapter {
     /* User Table*/
     static final String USER_ID = "userId";

     static final  String USERNAME = "username";

     static final String PASSWORD = "password";

     static final String STAFF_ID =  "staffId";

     static final String DEALER_ID =  "dealerId";

     static final String CUSTOMER_ID =  "customerId";

     static final String USER_ACCESS_TOKEN = "access_token";

     static final String NAME_TABLE_USER = "users";

     static final  int DATABASE_VERSION = 2;

     static final String QUERY_CREATE_TABLE_USER = "create table " + NAME_TABLE_USER + " ( "
            + USER_ID + " integer primary key not null , "
            + USERNAME + " text null , "
            + PASSWORD + " text null , "
            + STAFF_ID + " integer null , "
            + DEALER_ID + " integer null , "
            + CUSTOMER_ID + " integer null" + " ) ";

    /* CUSTOMER Table */

    static final String NAME_TABLE_CUSTOMER= "customers";

    static final String CUSTOMER_NAME = "name";

    static final String CUSTOMER_PHONE = "phone";

    static final String CUSTOMER_ADDRESS = "address";

    static final String QUERY_CREATE_TABLE_CUSTOMER = "create table " + NAME_TABLE_CUSTOMER + " ( "
            + CUSTOMER_ID + " integer primary key not null , "
            + CUSTOMER_NAME + " text not null ,"
            + CUSTOMER_PHONE + " text not null,"
            + CUSTOMER_ADDRESS + " text not null" + " )";


    /* DEALER Table*/

    static final String NAME_TABLE_DEALER = "dealers";

    static final String DEALER_NAME  =  "name";

    static final String DEALER_ADDRESS = "address";

    static final String QUERY_CREATE_TABLE_DEALER = "create table " + NAME_TABLE_DEALER + " ( "
            + DEALER_ID + " integer primary key not null , "
            + DEALER_NAME + " text not null , "
            + DEALER_ADDRESS + " text not null" + " ) ";

    /* Staff table */

    static final String NAME_TABLE_STAFF = "staffs";

    static final  String STAFF_NAME = "name";

    static final String  STAFF_PHONE = "phone";

    static final String  STAFF_ADDRESS = "address";

    static final String QUERY_CREATE_TABLE_STAFF = " create table " + NAME_TABLE_STAFF + " ( "
            + STAFF_ID + " integer primary key not null , "
            + STAFF_NAME + " text not null , "
            + STAFF_PHONE + " text not null , "
            + STAFF_ADDRESS + " text not null " + " ) ";

     /*  Unit table */

    static final String NAME_TABLE_UNIT = "units";
    static final String UNIT_ID = "unitId";
    static final String UNIT_TYPE = "unitType";
    static final String UNIT_ISPART = "isPart";
    static final String UNIT_PARENTID = "unitParentId";

    static final String QUERY_CREATE_TABLE_UNIT = " create table " + NAME_TABLE_UNIT + " ( "
            + UNIT_ID + " integer primary key not null , "
            + UNIT_TYPE + " text not null ,"
            + UNIT_ISPART + " integer not null ,"
            + UNIT_PARENTID + " integer null " + " ) ";

    /* UnitOfDealer table */

    static final String NAME_TABLE_UNITOFDEALER = "unitOfDealer";
    static final String UNITOFDEALER_PRICE = "price";

    static  final String QUERY_CREATE_TABLE_UNITOFDEALER = " crate table " + NAME_TABLE_UNITOFDEALER + " ( "
            + UNIT_ID + " integer not null , "
            + DEALER_ID + " integer not null ,"
            + UNITOFDEALER_PRICE + " real not null"
            + " PRIMARY KEY( " + UNIT_ID + " , " +  DEALER_ID + "  )) ";


    private String address;

    Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public SQLAdapter(Context context){
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, Constant.SQL_DB_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(QUERY_CREATE_TABLE_USER);
                db.execSQL(QUERY_CREATE_TABLE_DEALER);
                db.execSQL(QUERY_CREATE_TABLE_STAFF);
                db.execSQL(QUERY_CREATE_TABLE_CUSTOMER);
                db.execSQL(QUERY_CREATE_TABLE_UNIT);
                db.execSQL(QUERY_CREATE_TABLE_UNITOFDEALER);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w("phuongtd", "Upgrading database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS");
            onCreate(db);
        }
    }

    //---opens the database---
    public SQLiteDatabase open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();

        return db;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }


}
