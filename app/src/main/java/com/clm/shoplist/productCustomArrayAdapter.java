package com.clm.shoplist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jbt on 7/13/2016.
 */
public class productCustomArrayAdapter extends ArrayAdapter<Product> {
    List<Product> allProducts;
    public productCustomArrayAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        allProducts=objects;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v=convertView;

        if (v==null) {
            LayoutInflater viewInflater = LayoutInflater.from(getContext());
            v=viewInflater.inflate( R.layout.shop_list_item,null);
        }
        Product currentProduct=allProducts.get(position);

        TextView productTV = (TextView) v.findViewById(R.id.productTV);
        productTV.setText(currentProduct.getProductName());

        TextView amountTV = (TextView) v.findViewById(R.id.amountTV);
        amountTV.setText(""+currentProduct.getProductAmmount());

        CheckBox boughtCB=(CheckBox) v.findViewById(R.id.chooseCB);
        boughtCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                allProducts.get(position).setChoose(isChecked);
            }
        });





        return v;
    }

}
