package com.gem.nhom1.feedbackemail.SQLDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gem.nhom1.feedbackemail.network.entities.User;

/**
 * Created by phuongtd on 19/02/2016.
 */
public class UserAdapter {
    private SQLAdapter sqlAdapter;

    private Context context;

    private SQLiteDatabase db;

    public UserAdapter(Context context){
        sqlAdapter = new SQLAdapter(context);
        this.context = context;
    }

    public void open(){
        db = sqlAdapter.open();
    }

    public void close(){
        sqlAdapter.close();
    }

    public long insertUser(User user){

        if(getUserByUserNamePassWord(user.getUsername() , user.getPassword()) != null){
            return -2;
        }
        open();
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
        long kq =  db.insert(SQLAdapter.NAME_TABLE_USER, null, initialValues);
        close();

        return kq;
    }

    public User getUserByUserNamePassWord(String username , String password){
        open();

        String[] tableColumns = new String[] {
                SQLAdapter.USER_ID , SQLAdapter.USERNAME , SQLAdapter.PASSWORD , SQLAdapter.CUSTOMER_ID , SQLAdapter.DEALER_ID , SQLAdapter.STAFF_ID
        };
        String whereClause = SQLAdapter.USERNAME + " = ? AND " + SQLAdapter.PASSWORD + " = ?";
        String[] whereArgs = new String[] {
                username ,
                password
        };
        String orderBy = null;
        Cursor c = db.query(SQLAdapter.NAME_TABLE_USER, tableColumns, whereClause, whereArgs,
                null, null, orderBy);


        if(c.getCount() ==0 )
            return null;

        c.moveToFirst();

        User u = new User();

        u.setUserId(c.getInt(0));
        u.setUsername(username);
        u.setPassword(password);

        CustomerAdapter customerAdapter = new CustomerAdapter(context);
        u.setCustomer(customerAdapter.getCustomerById(c.getInt(3)));

        DealerAdapter dealerAdapter = new DealerAdapter(context);
        u.setDealer(dealerAdapter.getDealerById(c.getInt(4)));


        StaffAdapter staffAdapter = new StaffAdapter(context);
        u.setStaff(staffAdapter.getStaffById(c.getInt(5)));

        close();
        return u;
    }

    public User getUserById(int id){
        open();

        String[] tableColumns = new String[] {
                SQLAdapter.USER_ID , SQLAdapter.USERNAME , SQLAdapter.PASSWORD , SQLAdapter.CUSTOMER_ID , SQLAdapter.DEALER_ID , SQLAdapter.STAFF_ID
        };
        String whereClause = SQLAdapter.USER_ID + " = ?";
        String[] whereArgs = new String[] {
                id + ""
        };
        String orderBy = null;
        Cursor c = db.query(SQLAdapter.NAME_TABLE_USER, tableColumns, whereClause, whereArgs,
                null, null, orderBy);


        if(c.getCount() ==0 )
            return null;

        c.moveToFirst();

        User u = new User();

        u.setUserId(c.getInt(0));
        u.setUsername(c.getString(1));
        u.setPassword(c.getString(2));

        CustomerAdapter customerAdapter = new CustomerAdapter(context);
        u.setCustomer(customerAdapter.getCustomerById(c.getInt(3)));

        DealerAdapter dealerAdapter = new DealerAdapter(context);
        u.setDealer(dealerAdapter.getDealerById(c.getInt(4)));

        StaffAdapter staffAdapter = new StaffAdapter(context);
        u.setStaff(staffAdapter.getStaffById(c.getInt(5)));


        close();
        return u;
    }

}
