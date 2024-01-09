package com.example.a1001_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyProfile extends AppCompatActivity {

    Button btnEdit ,btnDelete;
    Company company ;
    ImageView homeIcon ,goBack ;
    TextView homeText , name , nameCompany , emailComapny;
    LinearLayout Home;
    TextView logOut;

    DB_Manager db = DB_Manager.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);
        Intent intent = getIntent();
        company = (Company) intent.getSerializableExtra("selectedCompany");
        btnEdit = findViewById(R.id.Company_profile_editbtn);
        homeIcon = findViewById(R.id.homeIcon);
        homeText = findViewById(R.id.homeText);
        Home = findViewById(R.id.home10);
        goBack = findViewById(R.id.goback);
        logOut=findViewById(R.id.logoutid);
        name = findViewById(R.id.company_name);
        nameCompany = findViewById(R.id.companytext);
        emailComapny =  findViewById(R.id.emailtextid);
        btnDelete =  findViewById(R.id.main_deletebtn);

        name.setText(company.getName());
        nameCompany.setText(company.getName());
        emailComapny.setText(company.getEmail());

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompanyProfile.this, MainActivity.class));
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompanyProfile.this , EditCompany.class));
            }
        });
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyProfile.this, CompanyHome.class);
                intent.putExtra("selectedCompany", company);
                startActivity(intent);
            }
        });
        homeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyProfile.this, CompanyHome.class);
                intent.putExtra("selectedCompany", company);
                startActivity(intent);
            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyProfile.this, CompanyProfile.class);
                intent.putExtra("selectedCompany", company);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyProfile.this, EditCompany.class);
                intent.putExtra("selectedCompany", company);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {


                                             for (Company company : db.getCompanys()) {
                                                 if (company.getName().equals(nameCompany.getText().toString())) {
                                                     try {
                                                         db.getdeleteCompany(company);
                                                         Intent intent = new Intent(CompanyProfile.this, Login.class);
                                                         intent.putExtra("selectedCompany", company);
                                                         startActivity(intent);
                                                         Toast.makeText(CompanyProfile.this, "Company  Deleted", Toast.LENGTH_SHORT).show();
                                                         startActivity(new Intent(CompanyProfile.this, MainActivity.class));
                                                     } catch (DataExists e) {
                                                         Toast.makeText(CompanyProfile.this, "Company already Deleted", Toast.LENGTH_SHORT).show();
                                                     }


                                                 }
                                             }
                                         }
                                     }


        );


    }





}