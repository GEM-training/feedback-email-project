package com.project.gem.feedbackemail.SQLDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.gem.feedbackemail.model.Customer;
import com.project.gem.feedbackemail.model.Dealer;

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

    public long insert(Dealer dealer){

        if(getCustomerById(dealer.getDealerId())!= null){
            return -2;
        }
        open();
        ContentValues initialValues = new ContentValues();
        initialValues.put(SQLAdapter.DEALER_ID, dealer.getDealerId());
        initialValues.put(SQLAdapter.DEALER_NAME ,dealer.getName());
        initialValues.put(SQLAdapter.DEALER_ADDRESS , dealer.getAddress());

        long kq =  db.insert(SQLAdapter.NAME_TABLE_DEALER, null, initialValues);
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

            close();
            return customer;




    }
}
