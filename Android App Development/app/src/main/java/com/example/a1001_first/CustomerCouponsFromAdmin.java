package com.example.a1001_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerCouponsFromAdmin extends AppCompatActivity {

    ImageView goBack;
    TextView logOut;

    ListView CustomerCouponsFromAdmin;

    ArrayList<String> names = new ArrayList<>();

    DB_Manager db = DB_Manager.getInstance(this);

    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_coupons_from_admin);

        goBack = findViewById(R.id.goback);
        logOut=findViewById(R.id.logoutid);
        CustomerCouponsFromAdmin = findViewById(R.id.listView);
        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("selectedCustomer");

        for (Customer name : db.gethashCustomerCoupons().keySet()) {
            Coupon coupon = db.gethashCustomerCoupons().get(name);
            if(!names.contains(coupon.getTitle()) && name.getEMAIL().equals(customer.getEMAIL() ))
            {
                names.add(coupon.getTitle());

            }
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        CustomerCouponsFromAdmin.setAdapter(arrayAdapter);


        CustomerCouponsFromAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCompany = names.get(position);
                Intent intent = new Intent(CustomerCouponsFromAdmin.this, CouponDetails.class);
                intent.putExtra("selectedCompany", selectedCompany);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerCouponsFromAdmin.this, MainActivity.class));
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}