package com.gem.nhom1.feedbackemail.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gem.nhom1.feedbackemail.network.entities.Unit;

/**
 * Created by phuongtd on 26/02/2016.
 */
public class UnitAdapter {
    private SQLAdapter sqlAdapter;

    private SQLiteDatabase db;

    public UnitAdapter(Context context){
        sqlAdapter = new SQLAdapter(context);
    }

    public void open(){
        db = sqlAdapter.open();
    }

    public void close(){
        sqlAdapter.close();
    }

    public long insert(Unit unit){


        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(SQLAdapter.UNIT_ID, unit.getUnitId());
        initialValues.put(SQLAdapter.UNIT_TYPE , unit.getType());
        initialValues.put(SQLAdapter.UNIT_ISPART , unit.getIsPart());

        if(unit.getUnit() != null)
        initialValues.put(SQLAdapter.UNIT_PARENTID , unit.getUnit().getUnitId());


        long kq =  db.insert(SQLAdapter.NAME_TABLE_UNIT, null, initialValues);
        close();

        return kq;
    }

    public Unit getById(int id){
        open();

        String[] tableColumns = new String[] {
                SQLAdapter.UNIT_ID , SQLAdapter.UNIT_TYPE , SQLAdapter.UNIT_ISPART , SQLAdapter.UNIT_PARENTID
        };

        String whereClause = SQLAdapter.UNIT_ID + " = ?";
        String[] whereArgs = new String[] {
                id+""
        };
        String orderBy = null;
        Cursor c = db.query(SQLAdapter.NAME_TABLE_UNIT, tableColumns, whereClause, whereArgs,
                null, null, orderBy);

        if(c.getCount() ==0 ) {
            c.close();
            close();
            return null;
        }


        c.moveToFirst();
        Unit unit = new Unit();
        unit.setUnitId(c.getInt(0));
        unit.setType(c.getString(1));
        unit.setIsPart(c.getInt(2));

        Unit parent = null;

        Integer integer = Integer.valueOf(c.getInt(3));

        if(integer != null){
            parent = getById(integer);
        }

        if(parent != null) {
            unit.setUnit(parent);
        }

        c.close();
        close();
        return  unit;


    }
}
