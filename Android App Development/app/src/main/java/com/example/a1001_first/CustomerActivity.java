package com.example.a1001_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CustomerActivity extends AppCompatActivity {

    Button btnMyCoupon;
    Button btnEdit;

    TextView Name;
    TextInputLayout fullNameLabel;
    TextInputLayout Email;
    TextInputEditText FullNameText;
    TextInputEditText EmailText;
    TextView homeText,profileText;
    ImageView homeIcon, profileIcon;
    Button getBtnCoupon;

    Button btnDeletee;
    LinearLayout Home,Profile;

    Customer customer;

    DB_Manager db = DB_Manager.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("selectedCustomer");

        homeIcon = findViewById(R.id.homeIcon2);
        profileIcon = findViewById(R.id.profileIcon2);
        homeText = findViewById(R.id.homeText2);
        profileText = findViewById(R.id.profileText2);
        Home = findViewById(R.id.home12);
        Profile = findViewById(R.id.profile12);

        btnMyCoupon = findViewById(R.id.mycoupons);
        getBtnCoupon = findViewById(R.id.getCoupons2);
        btnEdit = findViewById(R.id.main_editbtn);
        btnDeletee = findViewById(R.id.main_deleteBtn);

        Name = findViewById(R.id.full_name);
        fullNameLabel = findViewById(R.id.fullnamelabel);
        Email = findViewById(R.id.emaillabel);
        FullNameText = findViewById(R.id.fullnametext);
        EmailText = findViewById(R.id.emailtext);



        Name.setText(customer.getFIRST_NAME()+ " "+customer.getLAST_NAME());
        FullNameText.setText(customer.getFIRST_NAME()+ " "+customer.getLAST_NAME());
        EmailText.setText(customer.getEMAIL());


        homeText.setOnClickListener(view -> startActivity(new Intent(CustomerActivity.this, CustomerHomeActivity.class)));
        homeIcon.setOnClickListener(view -> startActivity(new Intent(CustomerActivity.this, CustomerHomeActivity.class)));
        Home.setOnClickListener(view -> startActivity(new Intent(CustomerActivity.this, CustomerHomeActivity.class)));



        btnDeletee.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {

                                              for(Customer customer : db.getCustomers())
                                              {
                                                  String name = customer.getFIRST_NAME()+' '+ customer.getLAST_NAME();
                                                  if(name.equals(FullNameText.getText().toString()))
                                                  {
                                                      try {
                                                          db.getdeleteCustomer(customer);
                                                          Intent intent = new Intent(CustomerActivity.this, Login.class);
                                                          intent.putExtra("selectedCustomer", customer);
                                                          startActivity(intent);
                                                          Toast.makeText(CustomerActivity.this, "Customer  Deleted", Toast.LENGTH_SHORT).show();
                                                          startActivity(new Intent(CustomerActivity.this, MainActivity.class));
                                                      } catch (DataExists e) {
                                                          Toast.makeText(CustomerActivity.this, "Customer already Deleted", Toast.LENGTH_SHORT).show();
                                                      }


                                                  }
                                              }


                                          }

                                      }


        );



        getBtnCoupon.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent(CustomerActivity.this, GetCoupons.class);
                                                intent.putExtra("selectedCustomer", customer);
                                                startActivity(intent);

                                            }
                                        }

        );
        btnMyCoupon.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Intent intent = new Intent(CustomerActivity.this, CustomerMyCoupons.class);
                                               intent.putExtra("selectedCustomer", customer);
                                               startActivity(intent);

                                           }
                                       }

        );
        btnEdit.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Intent intent = new Intent(CustomerActivity.this, EditCustomer.class);
                                               intent.putExtra("selectedCustomer", customer);
                                               startActivity(intent);

                                           }
                                       }

        );


    }


}