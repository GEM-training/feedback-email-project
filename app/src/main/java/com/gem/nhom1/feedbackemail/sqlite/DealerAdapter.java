package com.gem.nhom1.feedbackemail.sqlite;/*
package com.gem.nhom1.feedbackemail.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gem.nhom1.feedbackemail.network.entities.Dealer;

import java.util.ArrayList;
import java.util.List;




public class DealerAdapter {
    private SQLAdapter sqlAdapter;

    private SQLiteDatabase db;

    public DealerAdapter(Context context){
        sqlAdapter = new SQLAdapter(context);
    }

    public void open(){
        db = sqlAdapter.open();
    }

    public void close(){
        sqlAdapter.close();
    }

    public long insert(Dealer dealer){

        if(getDealerById(dealer.getDealerId())!= null){
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

    public Dealer getDealerById(int id){
        open();

        String[] tableColumns = new String[] {
                SQLAdapter.DEALER_ID , SQLAdapter.DEALER_NAME , SQLAdapter.DEALER_ADDRESS
        };
        String whereClause = SQLAdapter.DEALER_ID + " = ?";
        String[] whereArgs = new String[] {
                id+""
        };
        String orderBy = null;
        Cursor c = db.query(SQLAdapter.NAME_TABLE_DEALER, tableColumns, whereClause, whereArgs,
                null, null, orderBy);



        if(c.getCount() ==0 ){
            c.close();
            close();
            return null;
        }


        c.moveToFirst();


        Dealer dealer = new Dealer();
        dealer.setDealerId(c.getInt(0));
        dealer.setName(c.getString(1));
        dealer.setAddress(c.getString(2));

        c.close();
        close();
        return dealer;

    }

    public List<Dealer> getListDealer(int startId , int pageZise){
        open();

        List<Dealer> list = new ArrayList<Dealer>();

        String[] tableColumns = new String[] {
                SQLAdapter.DEALER_ID , SQLAdapter.DEALER_NAME , SQLAdapter.DEALER_ADDRESS
        };
        String whereClause = SQLAdapter.DEALER_ID + " > ?";
        String[] whereArgs = new String[] {
                startId+""
        };
        String orderBy = SQLAdapter.DEALER_ID;
        Cursor c = db.query(SQLAdapter.NAME_TABLE_DEALER, tableColumns, whereClause, whereArgs,
                null, null, orderBy , pageZise+"");


        if(c.getCount() ==0 ){
            c.close();
            close();
            return list;
        }


        c.moveToFirst();

        while (c.isAfterLast()==false){
            Dealer dealer = new Dealer();
            dealer.setDealerId(c.getInt(0));
            dealer.setName(c.getString(1));
            dealer.setAddress(c.getString(2));
            c.moveToNext();

            list.add(dealer);
        }


        c.close();
        close();
        return list;
    }
}
*/
