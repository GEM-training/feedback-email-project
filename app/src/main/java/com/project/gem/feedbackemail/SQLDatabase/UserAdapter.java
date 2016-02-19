package com.project.gem.feedbackemail.SQLDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.project.gem.feedbackemail.model.User;

/**
 * Created by phuongtd on 19/02/2016.
 */
public class UserAdapter {
    private SQLAdapter sqlAdapter;

    private SQLiteDatabase db;

    public UserAdapter(Context context){
        sqlAdapter = new SQLAdapter(context);
    }

    public void open(){
        sqlAdapter.open();
        db = sqlAdapter.getDatabase();
    }

    public void close(){
        sqlAdapter.close();
    }

    public long insertUser(User user){
        ContentValues initialValues = new ContentValues();
        initialValues.put(SQLAdapter.USER_ID, user.getUserId());
        initialValues.put(SQLAdapter.USERNAME, user.getUsername());
        initialValues.put(SQLAdapter.PASSWORD, user.getPassword());

        if(user.getCustomer() != null){
            initialValues.put(SQLAdapter.CUSTOMER_ID, user.getCustomer().getId());
        }

        if(user.getDealer() != null){
            initialValues.put(SQLAdapter.DEALER_ID , user.getDealer().getDealerId());
        }

        if(user.getStaff() != null){
            initialValues.put(SQLAdapter.STAFF_ID , user.getStaff().getStaffId());
        }
        return db.insert(SQLAdapter.NAME_TABLE_DEALER, null, initialValues);
    }

    public User getUserByUserNamePassWord(String username , String password){


        return null;
    }

}
