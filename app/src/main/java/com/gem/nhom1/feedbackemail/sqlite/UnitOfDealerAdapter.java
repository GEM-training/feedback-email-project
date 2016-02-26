package com.gem.nhom1.feedbackemail.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gem.nhom1.feedbackemail.network.entities.UnitOfDealer;
import com.gem.nhom1.feedbackemail.network.entities.UnitPrice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuongtd on 26/02/2016.
 */
public class UnitOfDealerAdapter {
    private SQLAdapter sqlAdapter;

    private SQLiteDatabase db;

    Context context;

    public UnitOfDealerAdapter(Context context){
        sqlAdapter = new SQLAdapter(context);
        this.context = context;
    }

    public void open(){
        db = sqlAdapter.open();
    }

    public void close(){
        sqlAdapter.close();
    }

    public long insert(UnitOfDealer unitOfDealer){

        Log.d("phuongtd" , "UOD unitid: "+unitOfDealer.getUnitId());
        Log.d("phuongtd" , "UOD dealerId: "+unitOfDealer.getDealerId());
        Log.d("phuongtd" , "UOD price: "+unitOfDealer.getPrice());

        open();
        ContentValues initialValues = new ContentValues();
        initialValues.put(SQLAdapter.UNIT_ID, unitOfDealer.getUnitId());
        initialValues.put(SQLAdapter.DEALER_ID , unitOfDealer.getDealerId());
        initialValues.put(SQLAdapter.UNITOFDEALER_PRICE , unitOfDealer.getPrice());

        long kq =  db.insert(SQLAdapter.NAME_TABLE_UNITOFDEALER, null, initialValues);
        close();

        return kq;

    }

    public List<UnitPrice> getListUnitOfDealer(int dealerId , int startIndex , int pageSize){
        open();

        List<UnitPrice> unitPriceList = new ArrayList<>();

        String[] tableColumns = new String[] {
                SQLAdapter.UNIT_ID , SQLAdapter.UNITOFDEALER_PRICE , SQLAdapter.DEALER_ID
        };
        String whereClause = SQLAdapter.DEALER_ID + " = ? and " + SQLAdapter.UNIT_ID + " > ?";
        String[] whereArgs = new String[] {
                dealerId+"" , startIndex+""
        };
        String orderBy = SQLAdapter.UNIT_ID;
        Cursor c = db.query(SQLAdapter.NAME_TABLE_UNITOFDEALER, tableColumns, whereClause, whereArgs,
                null, null, orderBy , pageSize+"");

        if(c.getCount() ==0 ) {
            close();
            return unitPriceList;
        }

        c.moveToFirst();

        while (c.isAfterLast()==false){
            UnitPrice unitPrice = new UnitPrice();
            unitPrice.setUnit(new UnitAdapter(context).getById(c.getInt(0)));
            unitPrice.setPrice(c.getDouble(1));
            c.moveToNext();

            unitPriceList.add(unitPrice);
        }

        c.close();
        close();
        return unitPriceList;



    }


}
