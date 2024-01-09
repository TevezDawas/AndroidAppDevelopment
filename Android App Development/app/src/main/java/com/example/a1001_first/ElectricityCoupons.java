package com.example.a1001_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ElectricityCoupons extends AppCompatActivity {
    ImageView goBack,sort;

    ListView ElectricityCoupons;
    ArrayList<String> names = new ArrayList<>();
    DB_Manager db = DB_Manager.getInstance(this);

    Customer customer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrcity_coupons);

        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("selectedCustomer");
        sort = findViewById(R.id.sort);
        goBack = findViewById(R.id.goback);
        ElectricityCoupons = findViewById(R.id.listView);

        for (Coupon name : db.getCCoupons()) {
            if(!names.contains(name.getTitle()))
            {
                if(name.getCategory().equals("Electricity"))
                {
                    names.add(name.getTitle());
                }

            }
        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        ElectricityCoupons.setAdapter(arrayAdapter);

        ElectricityCoupons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCompany = names.get(position);

                for (Coupon name : db.getCCoupons())
                {
                    if(name.getTitle().equals(selectedCompany))
                    {
                        try {
                            db.addNewCustomerCoupon(customer, name);
                            Toast.makeText(ElectricityCoupons.this, "Successfully purchased", Toast.LENGTH_SHORT).show();
                        } catch (DataExists e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ElectricityCoupons.this,sortedCoupons.class));
            }
        });


    }
}