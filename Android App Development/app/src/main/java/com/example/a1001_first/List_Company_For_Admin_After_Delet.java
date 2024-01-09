package com.example.a1001_first;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class List_Company_For_Admin_After_Delet extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> names2 = new ArrayList<>();

    DB_Manager db = DB_Manager.getInstance(this);

    ListView lvNames2;
    ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_company_for_admin_after_delet);


        Intent intent = getIntent();
        String selectedCompany = intent.getStringExtra("selectedCompany");

        lvNames2 = findViewById(R.id.main_lvNames2);

        for (Company name : db.getCompanys()) {
            if(!names.contains(name.getName()))
            {
                names.add(name.getName());

            }
        }
        for (Company name : db.getCompanys()) {
            if(name.equals(selectedCompany))
            {
                names.remove(name);

            }
        }



        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        lvNames2.setAdapter(arrayAdapter);


        lvNames2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCompany = names.get(position);
                Intent intent = new Intent(List_Company_For_Admin_After_Delet.this, Company_For_Admin.class);
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
    }

}