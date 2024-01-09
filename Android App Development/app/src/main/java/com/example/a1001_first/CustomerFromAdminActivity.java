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

public class CustomerFromAdminActivity extends AppCompatActivity {
    Button btnCosCoupon;
    Button btnEdit;
    Button btnDelete;
    TextView Name;
    TextInputLayout fullNameLabel;
    TextInputLayout Email;
    TextInputEditText FullNameText;
    TextInputEditText EmailText;
    TextView logout;
    ImageView goBack;

    Customer customer2;
    DB_Manager db = DB_Manager.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_from_admin);
        logout = findViewById(R.id.logoutid);
        btnCosCoupon = findViewById(R.id.ManageCoscoupons);
        btnEdit = findViewById(R.id.editid);
        btnDelete = findViewById(R.id.deletid);
        Name = findViewById(R.id.full_name);
        fullNameLabel = findViewById(R.id.fullnamelabel);
        Email = findViewById(R.id.emaillabel);
        FullNameText = findViewById(R.id.fullnametext);
        EmailText = findViewById(R.id.emailtext);
        goBack = findViewById(R.id.goback);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerFromAdminActivity.this, MainActivity.class));
            }
        });


        Intent intent = getIntent();
        String selectedCustomer = intent.getStringExtra("selectedCustomer");
        FullNameText.setText(selectedCustomer.toString());
        for(Customer customer : db.getCustomers())
        {
            String name = customer.getFIRST_NAME() +" "+ customer.getFIRST_NAME();
            if(name.equals(selectedCustomer))
            {
                EmailText.setText(customer.getEMAIL().toString());
                Name.setText(customer.getFIRST_NAME().toString() + " "+ customer.getLAST_NAME().toString());
                customer2 =customer;

            }
        }


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCosCoupon.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent(CustomerFromAdminActivity.this, CustomerMyCoupons.class);
                                                intent.putExtra("selectedCustomer", customer2);
                                                startActivity(intent);
                                            }
                                        }


        );

        btnEdit.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent = new Intent(CustomerFromAdminActivity.this, EditCustomer.class);
                                           intent.putExtra("selectedCustomer", customer2);
                                           startActivity(intent);
                                       }
                                   }


        );
        btnDelete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {

                                             Intent intent = getIntent();
                                             String selectedCompany = intent.getStringExtra("selectedCustomer");
                                             FullNameText.setText(selectedCompany);
                                             for(Customer customer : db.getCustomers())
                                             {
                                                 String name = customer.getFIRST_NAME()+' '+ customer.getLAST_NAME();
                                                 if(name.equals(selectedCompany))
                                                 {
                                                     try {
                                                         db.getdeleteCustomer(customer);
                                                         startActivity(new Intent(CustomerFromAdminActivity.this, MainActivity.class));
                                                     } catch (DataExists e) {
                                                         Toast.makeText(CustomerFromAdminActivity.this, "Customer already Deleted", Toast.LENGTH_SHORT).show();
                                                     }


                                                 }
                                             }
                                         }
                                     }


        );






    }
}