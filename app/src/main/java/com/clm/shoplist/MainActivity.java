package com.clm.shoplist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> productArrayList;
    ArrayAdapter<Product> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productArrayList= new ArrayList<>();
        productArrayList.add (new Product("Milk",10));
        // create new adapter
        arrayAdapter =new productCustomArrayAdapter(this, R.layout.shop_list_item,productArrayList);

        // link adapter to list
        ListView productsLV = (ListView) findViewById(R.id.productsLV);
        productsLV.setAdapter(arrayAdapter);


        //on click - add to list and update
        Button addBTN = (Button) findViewById(R.id.insertBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText productName= (EditText) findViewById(R.id.nameET);
                EditText productAmount = (EditText) findViewById(R.id.amountET);
                int amountInt;
                if (productAmount.getText()==null)
                    amountInt=0;
                else amountInt=Integer.parseInt(productAmount.getText().toString());

                productArrayList.add(new Product( productName.getText().toString() , amountInt ));
                arrayAdapter.notifyDataSetChanged();
            }
        });


        // Listview onitemselectedlongclick - erase list and update
        productsLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                productArrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

        // CB - on click - update product list








    }
}
