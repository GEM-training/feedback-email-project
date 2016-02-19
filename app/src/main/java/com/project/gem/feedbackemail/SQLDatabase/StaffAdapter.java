package com.project.gem.feedbackemail.SQLDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.gem.feedbackemail.model.Dealer;
import com.project.gem.feedbackemail.model.Staff;

/**
 * Created by phuongtd on 19/02/2016.
 */
public class StaffAdapter {
    private SQLAdapter sqlAdapter;

    private SQLiteDatabase db;

    public StaffAdapter(Context context){
        sqlAdapter = new SQLAdapter(context);
    }

    public void open(){
        db = sqlAdapter.open();
    }

    public void close(){
        sqlAdapter.close();
    }

    public long insert(Staff staff){

        if(getStaffById(staff.getStaffId()) != null)
            return -2;
        open();
        ContentValues initialValues = new ContentValues();
        initialValues.put(SQLAdapter.STAFF_ID, staff.getStaffId());
        initialValues.put(SQLAdapter.STAFF_NAME , staff.getName());
        initialValues.put(SQLAdapter.STAFF_PHONE , staff.getPhone());
        initialValues.put(SQLAdapter.DEALER_ADDRESS , staff.getAddress());


        long kq =  db.insert(SQLAdapter.NAME_TABLE_STAFF, null, initialValues);
        close();

        return kq;
    }

    public Staff getStaffById(int id){
        open();

        String[] tableColumns = new String[] {
                SQLAdapter.STAFF_ID , SQLAdapter.STAFF_NAME , SQLAdapter.STAFF_PHONE , SQLAdapter.STAFF_ADDRESS
        };
        String whereClause = SQLAdapter.STAFF_ID + " = ?";
        String[] whereArgs = new String[] {
                id+""
        };
        String orderBy = null;
        Cursor c = db.query(SQLAdapter.NAME_TABLE_STAFF, tableColumns, whereClause, whereArgs,
                null, null, orderBy);

        if(c.getCount() ==0 ) {
            close();
            return null;
        }


            c.moveToFirst();
            Staff staff = new Staff();
            staff.setStaffId(c.getInt(0));
            staff.setName(c.getString(1));
            staff.setPhone(c.getString(2));
            staff.setAddress(c.getString(3));
            close();
            return staff;




    }
}
