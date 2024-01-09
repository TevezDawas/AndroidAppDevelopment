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
import android.widget.TextView;

import java.util.ArrayList;

public class List_Customer_For_Admin extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<>();

    DB_Manager db = DB_Manager.getInstance(this);

    ListView lvNames;
    ImageView goBack;
    TextView logout;
    ImageView searchBTN ;
    EditText searchBAR ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer_for_admin);
        searchBTN = findViewById(R.id.searchIcon77);
        searchBAR = findViewById(R.id.searchText77);
        lvNames = findViewById(R.id.main_lvNames);

          for (Customer name : db.getCustomers()) {
              if(!names.contains(name.getFIRST_NAME() +' '+name.getLAST_NAME()))
              {
                  names.add(name.getFIRST_NAME() +' '+name.getLAST_NAME());
              }


        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        lvNames.setAdapter(arrayAdapter);

        lvNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCustomer = names.get(position);
                Intent intent = new Intent(List_Customer_For_Admin.this, CustomerFromAdminActivity.class);
                intent.putExtra("selectedCustomer", selectedCustomer);
                startActivity(intent);
            }
        });

        goBack = findViewById(R.id.goback);


        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = searchBAR.getText().toString();
                if(db.CustomerExists2(s))
                {
                    Intent intent = new Intent(List_Customer_For_Admin.this, CustomerFromAdminActivity.class);
                    intent.putExtra("selectedCustomer", s);
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

        logout=findViewById(R.id.logoutid);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(List_Customer_For_Admin.this, MainActivity.class));
            }
        });

    }

}