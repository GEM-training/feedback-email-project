package com.gem.nhom1.feedbackemail.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gem.nhom1.feedbackemail.network.entities.Customer;

/**
 * Created by phuongtd on 19/02/2016.
 */
public class CustomerAdapter {
    private SQLAdapter sqlAdapter;

    private SQLiteDatabase db;

    public CustomerAdapter(Context context){
        sqlAdapter = new SQLAdapter(context);
    }

    public void open(){
        db = sqlAdapter.open();
    }

    public void close(){
        sqlAdapter.close();
    }

    public long insert(Customer customer){

        if(getCustomerById(customer.getId())!= null){
            return -2;
        }
        open();
        ContentValues initialValues = new ContentValues();
        initialValues.put(SQLAdapter.CUSTOMER_ID, customer.getId());
        initialValues.put(SQLAdapter.CUSTOMER_NAME ,customer.getName());
        initialValues.put(SQLAdapter.CUSTOMER_ADDRESS , customer.getAddress());
        initialValues.put(SQLAdapter.CUSTOMER_PHONE , customer.getPhone());

        long kq =  db.insert(SQLAdapter.NAME_TABLE_CUSTOMER, null, initialValues);
        close();

        return kq;
    }

    public Customer getCustomerById(int id){
        open();

        String[] tableColumns = new String[] {
                SQLAdapter.CUSTOMER_ID , SQLAdapter.CUSTOMER_NAME , SQLAdapter.CUSTOMER_PHONE , SQLAdapter.CUSTOMER_ADDRESS
        };
        String whereClause = SQLAdapter.CUSTOMER_ID + " = ?";
        String[] whereArgs = new String[] {
                id+""
        };
        String orderBy = null;
        Cursor c = db.query(SQLAdapter.NAME_TABLE_CUSTOMER, tableColumns, whereClause, whereArgs,
                null, null, orderBy);



        if(c.getCount() ==0 ) {
            close();
            return null;
        }


            c.moveToFirst();
            Customer customer = new Customer();
            customer.setId(c.getInt(0));
            customer.setName(c.getString(1));
            customer.setPhone(c.getString(2));
            customer.setAddress(c.getString(3));

            c.close();
            close();
            return customer;




    }
}
