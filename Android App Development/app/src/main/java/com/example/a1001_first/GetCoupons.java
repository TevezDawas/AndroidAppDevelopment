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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetCoupons extends AppCompatActivity {

    ImageView goBack,sort;

    ArrayList<String> names = new ArrayList<>();
    DB_Manager db = DB_Manager.getInstance(this);
    ListView GetCoupons;

    Customer customer;

    Coupon nameCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_coupons);

        GetCoupons = findViewById(R.id.listView);
        sort = findViewById(R.id.sort);
        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("selectedCustomer");

        for (Coupon name : db.getCCoupons()) {
            if(!name.getCategory().equals("Food") &&!name.getCategory().equals("Electricity") &&!name.getCategory().equals("Vacation")&&!name.getCategory().equals("Restaurant"))
            if(!names.contains(name.getTitle()))
            {
                names.add(name.getTitle());
                nameCoupon = name;

            }
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        GetCoupons.setAdapter(arrayAdapter);

        GetCoupons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date currentDate = new Date();
                String currentDateStr = dateFormat.format(currentDate);
               // if((nameCoupon.getAMOUNT() > 0))
                //{
                    Toast.makeText(GetCoupons.this, "Successfully purchased", Toast.LENGTH_SHORT).show();

                try {

                    db.addNewCustomerCoupon(customer ,nameCoupon);
                    Intent I=new Intent(GetCoupons.this, CustomerMyCoupons.class);
                    I.putExtra("selectedCustomer", (Serializable) customer);

                } catch (DataExists e) {
                    throw new RuntimeException(e);
                }
                //}

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

                Intent intent = new Intent(GetCoupons.this, sortedCoupons.class);
                intent.putExtra("selectedCustomer",  customer);
                startActivity(intent);
            }
        });
    }
}