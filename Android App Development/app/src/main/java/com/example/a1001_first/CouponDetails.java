package com.example.a1001_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class CouponDetails extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<>();
    ArrayList<Company> Companys = new ArrayList<>();
    ArrayList<Coupon> Coupons = new ArrayList<>();

    TextView couponName;
    TextInputEditText Title,companyName,catagoryName,startDate,endDate,amount,price,desc;



    Company company1;
    Coupon coupon1;

    HashMap<Company, Coupon> CompanyAndCoupons = new HashMap<>();
    DB_Manager db = DB_Manager.getInstance(this);

    Button btnEdit;
    Button btnDelete;
    ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_details);
        goBack =findViewById(R.id.goback);
        btnEdit = findViewById(R.id.main_editbtn);
        btnDelete = findViewById(R.id.main_deletebtn);
        couponName = findViewById(R.id.coupon_name);
        Title = findViewById(R.id.titleText);
        companyName = findViewById(R.id.companyText);
        catagoryName = findViewById(R.id.catagoryText);
        startDate = findViewById(R.id.startDateText);
        endDate = findViewById(R.id.endDateText);
        amount = findViewById(R.id.amountText);
        price = findViewById(R.id.priceText);
        desc = findViewById(R.id.descText);

        Intent intent = getIntent();
        String selectedCompany = intent.getStringExtra("selectedCompany");


        for (Company c : db.gethashCC().keySet()) {
            Coupon coupon = db.gethashCC().get(c);
            String name = ("Company: " + "" + c.getName() + " , " + "Coupon: " + " " + coupon.getTitle());
            if (selectedCompany.equals(name)) {
                couponName.setText(coupon.getTitle());
                Title.setText(coupon.getTitle());
                companyName.setText(coupon.getCompany());
                catagoryName.setText(coupon.getCategory());
                startDate.setText(coupon.getStartDate());
                endDate.setText(coupon.getEndDate());

                // Set text for AMOUNT and PRICE
                amount.setText(String.valueOf(coupon.getAMOUNT()));
                price.setText(String.valueOf(coupon.getPRICE()));

                desc.setText(coupon.getDescription());
                company1 = c;
                coupon1 = coupon;
            }
        }



        btnEdit.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

                                           startActivity(new Intent(CouponDetails.this , EditCoupon.class));
                                       }
                                   }


        );
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {

                                                     try {
                                                         Toast.makeText(CouponDetails.this, "Coupon  Deleted", Toast.LENGTH_SHORT).show();
                                                         db.deleteCoupon(coupon1);

                                                     } catch (DataExists e) {
                                                         Toast.makeText(CouponDetails.this, "Coupon already Deleted", Toast.LENGTH_SHORT).show();
                                                     }


                                                 }


                                     }


        );



    }
}