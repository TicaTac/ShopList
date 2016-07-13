 package com.clm.shoplist;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.SharedPreferences.*;

 public class MainActivity extends AppCompatActivity {
    ArrayList<Product> productArrayList;
    ArrayAdapter<Product> arrayAdapter;
    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
    final Editor editor = preferences.edit();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView debugTV = (TextView) findViewById(R.id.debugTV);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productArrayList= new ArrayList<>();

        String getPrefs=preferences.getString("list",null);
        if (getPrefs==null){
            getPrefs="Milk;10;false;";
        }

        String[] splitPrefs= getPrefs.split(";");
        Product tempProduct=new Product("",0);
        for(int i=0; i<splitPrefs.length;i++){
            switch (i%3) {
                case 0:
                    tempProduct.setProductName(splitPrefs[i]);
                    break;

                case 1:
                    int amount= Integer.parseInt(splitPrefs[i]);
                    tempProduct.setProductAmmount(amount);
                    break;
                case 2:
                    boolean isChosen=false;
                    if (splitPrefs[i]=="true")
                        isChosen=true;

                    tempProduct.setChoose(isChosen);
                    break;

            }
            if (i>0 && i%3==0)
                productArrayList.add(tempProduct);
        }





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

/*        Button saveBTN = (Button) findViewById(R.id.saveBTN);
        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saveText="";
                for (int i=0; i<productArrayList.size();i++){
                    Product currentProduct= productArrayList.get(i);
                    saveText+=currentProduct.getProductName()+";"+currentProduct.getProductAmmount()+";"+currentProduct.isChosen()+";";
                }

                editor.putString("list",saveText);
                editor.commit();

            }
        }); */

        // CB - on click - update product list



}
     @Override
     protected void onPause() {
         super.onPause();
         String saveText="";
         for (int i=0; i<productArrayList.size();i++){
             Product currentProduct= productArrayList.get(i);
             saveText+=currentProduct.getProductName()+";"+currentProduct.getProductAmmount()+";"+currentProduct.isChosen()+";";
         }

         editor.putString("list",saveText);
         editor.commit();
     }}
