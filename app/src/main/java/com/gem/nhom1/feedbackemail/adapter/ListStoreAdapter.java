package com.gem.nhom1.feedbackemail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.gem.nhom1.feedbackemail.network.entities.Store;
import com.project.gem.feedbackemail.R;

import java.util.List;

/**
 * Created by phuongtd on 24/02/2016.
 */
public class ListStoreAdapter extends BaseAdapter {
    Context context;
    List<Store> listStore;

    public ListStoreAdapter(Context context, List<Store> stores){
        this.context = context;
        this.listStore = stores;
    }
    @Override
    public int getCount() {
        return listStore.size();
    }

    @Override
    public Object getItem(int position) {
        return listStore.get(position);
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

        Store store = listStore.get(position);

        tv_id.setText("ID: "+ store.getId());
        tv_name.setText(store.getName());
        tv_address.setText(store.getAddress());


        return convertView;
    }
}
