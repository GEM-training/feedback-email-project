package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gem.nhom1.feedbackemail.network.entities.UnitPrice;
import com.project.gem.feedbackemail.R;

import java.util.List;

/**
 * Created by nghicv on 25/02/2016.
 */
public class ProductListAdapter extends BaseAdapter {
    private Context context;
    private List<UnitPrice> unitPrices;

    public ProductListAdapter(Context context, List<UnitPrice> unitPrices) {
        this.context = context;
        this.unitPrices = unitPrices;
    }

    @Override
    public int getCount() {
        return unitPrices.size();
    }

    @Override
    public Object getItem(int position) {
        return unitPrices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.product_item, null);
        }

        UnitPrice unitPrice = unitPrices.get(position);

        TextView tvProductName = (TextView) view.findViewById(R.id.product_name);
        tvProductName.setText(unitPrice.getUnit().getType());

        return view;
    }
}
