package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gem.nhom1.feedbackemail.network.entities.Product;
import com.project.gem.feedbackemail.R;

import java.util.List;

/**
 * Created by nghicv on 25/02/2016.
 */
public class ProductListAdapter extends BaseAdapter {
    private Context context;
    private List<Product> listProduct;

    public ProductListAdapter(Context context, List<Product> products) {
        this.context = context;
        this.listProduct = products;
    }

    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return listProduct.get(position);
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

        Product product = listProduct.get(position);

        TextView tvProductName = (TextView) view.findViewById(R.id.product_name);
        TextView tvPrice = (TextView) view.findViewById(R.id.price);
        tvProductName.setText(product.getName());
        tvPrice.setText(product.getDescription());

        return view;
    }
}
