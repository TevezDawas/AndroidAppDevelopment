package com.example.a1001_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Company_For_Admin extends AppCompatActivity {

    Button btnManageCoupon;
    Button btnEdit;
    Button btnDelete;
    ImageView profileImage;
    TextView Name , fullname;
    TextInputLayout CompanyNameLabel;
    TextInputLayout Email;
    TextInputEditText companyNameText;
    TextInputEditText EmailText;
    Company company2;
    ImageView goBack;
    TextView logout;

    DB_Manager db = DB_Manager.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_for_admin);
        profileImage = findViewById(R.id.profile_background);
        goBack = findViewById(R.id.goback);
        btnManageCoupon = findViewById(R.id.managecouponid);
        btnEdit = findViewById(R.id.main_editbtn);
        btnDelete = findViewById(R.id.main_deletebtn);
        Name = findViewById(R.id.company_name);
        CompanyNameLabel = findViewById(R.id.nameLabelid);
        Email = findViewById(R.id.emaillabelid);
        companyNameText = findViewById(R.id.companytext);
        EmailText = findViewById(R.id.emailtextid);
        logout = findViewById(R.id.logoutid);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Company_For_Admin.this, MainActivity.class));
            }
        });


        Intent intent = getIntent();
        String selectedCompany = intent.getStringExtra("selectedCompany");
        companyNameText.setText(selectedCompany);
        for (Company company : db.getCompanys()) {
            if (company.getName().equals(selectedCompany)) {
                EmailText.setText(company.getEmail().toString());
                Name.setText(company.getName().toString());

                company2 = company;


            }
        }


        btnManageCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Company_For_Admin.this, ListCouponsForCompany.class);
                intent.putExtra("selectedCompany", company2);
                startActivity(intent);
            }
        });
        btnManageCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Company_For_Admin.this, ListCouponsForCompany.class);
                intent.putExtra("selectedCompany", company2);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent = new Intent(Company_For_Admin.this, EditCompany.class);
                                           intent.putExtra("selectedCompany", company2);
                                           startActivity(intent);

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
                                             Intent intent = getIntent();
                                             String selectedCompany = intent.getStringExtra("selectedCompany");
                                             companyNameText.setText(selectedCompany);
                                             for (Company company : db.getCompanys()) {
                                                 if (company.getName().equals(selectedCompany)) {
                                                     try {
                                                         db.getdeleteCompany(company);
                                                         startActivity(new Intent(Company_For_Admin.this, MainActivity.class));

                                                     } catch (DataExists e) {
                                                         Toast.makeText(Company_For_Admin.this, "Company already Delet", Toast.LENGTH_SHORT).show();
                                                     }


                                                 }
                                             }
                                         }
                                     }


        );


    }


}