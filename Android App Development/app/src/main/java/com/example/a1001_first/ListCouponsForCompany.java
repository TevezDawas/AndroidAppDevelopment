package com.example.a1001_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ListCouponsForCompany extends AppCompatActivity {
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Company> Companys = new ArrayList<>();
    ArrayList<Coupon> Coupons = new ArrayList<>();
    ImageView goBack,sort;
    ListView lvNames;
    Company company;
    ImageView searchBTN ;
    EditText searchBAR ;




    HashMap<Company, Coupon> CompanyAndCoupons = new HashMap<>();
    DB_Manager db = DB_Manager.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_coupons_for_company);
        lvNames=findViewById(R.id.coupon_view_for_company);
        searchBTN = findViewById(R.id.searchIcon88);
        searchBAR = findViewById(R.id.searchText88);
        Intent intent = getIntent();
        company = (Company) intent.getSerializableExtra("selectedCompany");


        for (Company company1 : db.gethashCC().keySet()) {
            Coupon coupon = db.gethashCC().get(company1);
            if(company1.getName().equals(company.getName()))
            {

                if(!names.contains("Company: "+""+company1.getName()+ " , " +"Coupon: " +" "+ coupon.getTitle()))
                    names.add("Company: "+""+company1.getName()+ " , " +"Coupon: " +" "+ coupon.getTitle());
            }



        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        lvNames.setAdapter(arrayAdapter);

        sort = findViewById(R.id.sort);
        goBack =findViewById(R.id.goback);
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
                if(db.CouponExists2(s))
                {
                    Intent intent = new Intent(ListCouponsForCompany.this,CouponDetails.class);
                    intent.putExtra("selectedCompany", s);
                    startActivity(intent);
                }

            }
        });

        lvNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCompany = names.get(position);
                Intent intent = new Intent(ListCouponsForCompany.this, CouponDetails.class);
                intent.putExtra("selectedCompany", selectedCompany);
                startActivity(intent);
            }
        });
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListCouponsForCompany.this, SortedCoupons_For_Comapny.class);
                intent.putExtra("selectedCompany", company);
                startActivity(intent);

            }
        });



    }
}