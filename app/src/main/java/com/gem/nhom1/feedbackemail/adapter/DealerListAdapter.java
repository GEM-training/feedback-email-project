package com.gem.nhom1.feedbackemail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gem.nhom1.feedbackemail.network.entities.Dealer;
import com.google.gson.Gson;
import com.project.gem.feedbackemail.R;

import java.util.List;

/**
 * Created by phuongtd on 24/02/2016.
 */
public class DealerListAdapter extends BaseAdapter {
    Context context;
    List<Dealer> dealerList;

    public DealerListAdapter(Context context , List<Dealer> dealers){
        this.context = context;
        this.dealerList = dealers;
    }
    @Override
    public int getCount() {
        return dealerList.size();
    }

    @Override
    public Object getItem(int position) {
        return dealerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_dealer_item , parent ,false);

        TextView tv_id = (TextView) convertView.findViewById(R.id.tv_dealer_id);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_dealer_name);
        TextView tv_address = (TextView) convertView.findViewById(R.id.tv_dealer_address);

        Dealer dealer = dealerList.get(position);

        tv_id.setText("ID: "+ dealer.getDealerId());
        tv_name.setText(dealer.getName());
        tv_address.setText(dealer.getAddress());


        return convertView;
    }
}
