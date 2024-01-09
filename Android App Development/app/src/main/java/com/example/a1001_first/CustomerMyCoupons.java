package com.example.a1001_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomerMyCoupons extends AppCompatActivity {
    ImageView goBack,sort;

    ArrayList<String> names = new ArrayList<>();
    DB_Manager db = DB_Manager.getInstance(this);
    ListView CustomerMyCoupons;
    Coupon coupon;

    Customer customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_my_coupons);

        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("selectedCustomer");

        CustomerMyCoupons = findViewById(R.id.listView);
        sort = findViewById(R.id.sort);

        for (Customer name : db.gethashCustomerCoupons().keySet()) {
            Coupon coupon1 = db.gethashCustomerCoupons().get(name);
            if (name.getEMAIL().equals(customer.getEMAIL())) {
                if ((!names.contains(coupon1.getTitle()))) {
                    names.add(coupon1.getTitle());

                }
            }
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        CustomerMyCoupons.setAdapter(arrayAdapter);

        CustomerMyCoupons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCompany = names.get(position);
                for (Company c : db.gethashCC().keySet()) {
                    Coupon coupon = db.gethashCC().get(c);
                  //  String name = ("Company: " + "" + c.getName() + " , " + "Coupon: " + " " + coupon.getTitle());
                    if (selectedCompany.equals(coupon.getTitle())) {
                        selectedCompany = ("Company: " + "" + c.getName() + " , " + "Coupon: " + " " + coupon.getTitle());
                    }
                    }
                Intent intent = new Intent(CustomerMyCoupons.this, CouponDetails.class);
                intent.putExtra("selectedCompany", selectedCompany);
                startActivity(intent);
            }
        });


        goBack = findViewById(R.id.goback);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerMyCoupons.this, sortedCoupons.class);
                intent.putExtra("selectedCustomer",  customer);
                startActivity(intent);

            }
        });
    }

}