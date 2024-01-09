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
import java.util.Collections;
import java.util.Comparator;

public class sortedCoupons extends AppCompatActivity {

    ImageView goBack;
    ArrayList<String> names = new ArrayList<>();
    DB_Manager db = DB_Manager.getInstance(this);
    ListView lvNames;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_coupons);

        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("selectedCustomer");

        lvNames = findViewById(R.id.listView);
        goBack = findViewById(R.id.goback);

        for (Customer name : db.gethashCustomerCoupons().keySet()) {
            Coupon coupon1 = db.gethashCustomerCoupons().get(name);
            if (name.getEMAIL().equals(customer.getEMAIL())) {
                if (!names.contains(coupon1.getTitle() + coupon1.getPRICE())) {
                    names.add("Title: "+coupon1.getTitle()+" , " + "Price: "+coupon1.getPRICE());
                }
            }
        }

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {

                String[] parts1 = s1.split(" ");
                String[] parts2 = s2.split(" ");


                double price1 = Double.parseDouble(parts1[1]);
                double price2 = Double.parseDouble(parts2[1]);


                return Double.compare(price2, price1);
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        lvNames.setAdapter(arrayAdapter);


        lvNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCompany = names.get(position);
                for (Company c : db.gethashCC().keySet()) {
                    Coupon coupon = db.gethashCC().get(c);

                    if (selectedCompany.equals("Title: "+coupon.getTitle()+" , " + "Price: "+coupon.getPRICE()))
                    {
                        selectedCompany = ("Company: " + "" + c.getName() + " , " + "Coupon: " + " " + coupon.getTitle());
                    }
                }
                Intent intent = new Intent(sortedCoupons.this, CouponDetails.class);
                intent.putExtra("selectedCompany", selectedCompany);
                startActivity(intent);
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
