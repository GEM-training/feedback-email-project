package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gem.nhom1.feedbackemail.network.dto.ListProductDTO;
import com.gem.nhom1.feedbackemail.network.entities.Product;
import com.project.gem.feedbackemail.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by nghicv on 25/02/2016.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    List<Product> products;

    public ProductListAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvPrice.setText(product.getName());
        holder.tvPrice.setText(product.getDescription());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvProductName;
        public TextView tvPrice;

        public ViewHolder(View v) {
            super(v);
            tvProductName = (TextView) v.findViewById(R.id.tv_product_name);
            tvPrice = (TextView) v.findViewById(R.id.tv_price);
        }
    }



}
