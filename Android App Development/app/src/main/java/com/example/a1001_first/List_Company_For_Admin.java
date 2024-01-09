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

import java.io.Serializable;
import java.util.ArrayList;

public class List_Company_For_Admin extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<>();

    DB_Manager db = DB_Manager.getInstance(this);

    ListView lvNames;
    ImageView goBack , searchBTN;
    TextView logout  , searchBAR ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_company_for_admin);
        searchBTN= findViewById(R.id.searchIconCompanyInAdmin);
        searchBAR= findViewById(R.id.searchTextCompanyInAdmin);
        lvNames = findViewById(R.id.main_lvNames);

        for (Company name : db.getCompanys()) {
            if(!names.contains(name.getName()))
            {
                names.add(name.getName());

            }
        }
        logout=findViewById(R.id.logoutid);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(List_Company_For_Admin.this, MainActivity.class));
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        lvNames.setAdapter(arrayAdapter);


        lvNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCompany = names.get(position);
                Intent intent = new Intent(List_Company_For_Admin.this, Company_For_Admin.class);
                intent.putExtra("selectedCompany", selectedCompany);
                startActivity(intent);
            }
        });

        goBack = findViewById(R.id.goback);

        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = searchBAR.getText().toString();
                if(db.companyExists2(s))
                {
                    Intent intent = new Intent(List_Company_For_Admin.this, Company_For_Admin.class);
                    intent.putExtra("selectedCompany", s);
                    startActivity(intent);
                }

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