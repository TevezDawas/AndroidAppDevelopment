package com.example.a1001_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListCustomersForCompany extends AppCompatActivity {


    ImageView goBack;
    ImageView searchBTN ;
    EditText searchBAR ;

    ArrayList<String> names = new ArrayList<>();

    DB_Manager db = DB_Manager.getInstance(this);

    ListView lvNames;

    Company company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customers_for_company);
        searchBTN = findViewById(R.id.searchIcon44);
        searchBAR = findViewById(R.id.searchText44);
        goBack =findViewById(R.id.goback);

        lvNames = findViewById(R.id.customer_view_for_company);

        Intent intent = getIntent();
        company = (Company) intent.getSerializableExtra("selectedCompany");

        for(Company c:db.getCompanys())
        {
                for (Customer name : db.gethashCustomerCoupons().keySet()) {
                    if(c.getName().equals(company.getName()))
                    {

                        if(!names.contains(name.getFIRST_NAME() +' '+name.getLAST_NAME()))
                        {
                            names.add(name.getFIRST_NAME() +' '+name.getLAST_NAME());
                        }
                    }



                }


        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        lvNames.setAdapter(arrayAdapter);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = searchBAR.getText().toString();
                Intent intent = new Intent(ListCustomersForCompany.this, CustomerFromAdminActivity.class);
                intent.putExtra("selectedCompany", s);
                startActivity(intent);
            }
        });
    }
}