package com.example.a1001_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListMyCoupons_for_admin extends AppCompatActivity {


    ListView listview;
    ImageView goBack;
    TextView logout;
    ImageView searchBTN ;
    EditText searchBAR ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_coupons_from_admin);
        goBack = findViewById(R.id.goback);
        listview  = findViewById(R.id.listviewId);
        logout=findViewById(R.id.logoutid);
        ArrayList<String> names = getList();
        searchBTN = findViewById(R.id.searchIcon56);
        searchBAR = findViewById(R.id.searchText56);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        listview.setAdapter(arrayAdapter);

        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = searchBAR.getText().toString();
                Intent intent = new Intent(ListMyCoupons_for_admin.this, CustomerFromAdminActivity.class);
                intent.putExtra("selectedCompany", s);
                startActivity(intent);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListMyCoupons_for_admin.this, MainActivity.class));
            }
        });


    }

    private ArrayList<String> getList(){
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=1;i<=10;i++){
            arrayList.add("item " + i);
        }
        return arrayList;

    }
}