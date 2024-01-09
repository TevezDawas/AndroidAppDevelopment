package com.example.a1001_first;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewCustomer extends AppCompatActivity {

    EditText etFirstName, etLastName, etEMail, etPassword, etConfirmPassword;
    Button btnCancel, btnAdd;
    DB_Manager db = DB_Manager.getInstance(this);

    ArrayList<Customer> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        etFirstName = findViewById(R.id.AddCustomer_etFirstName);
        etLastName = findViewById(R.id.AddCustomer_etLastName);
        etEMail = findViewById(R.id.AddCustomer_etEmailAddress);
        etPassword = findViewById(R.id.AddCustomer_etPassword);
        etConfirmPassword = findViewById(R.id.AddCustomer_etConfirmPassword);

        btnAdd = findViewById(R.id.AddCustomer_btnADD);
        btnCancel = findViewById(R.id.AddCustomer_btnCANCEL);

        ButtonsClick buttonsClick = new ButtonsClick();
        btnAdd.setOnClickListener(buttonsClick);
        btnCancel.setOnClickListener(buttonsClick);
    }


    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();

                }
            }
    );


    class ButtonsClick implements View.OnClickListener {
        public void onClick(View v) {

            if (v.getId() == btnAdd.getId()) {

                if (etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {

                    Customer c = new Customer("0", etFirstName.getText().toString(), etLastName.getText().toString(), etEMail.getText().toString(), etPassword.getText().toString());

                    try {
                        names =db.getCustomers();

                            if(!names.contains( c.getFIRST_NAME()+" "+c.getLAST_NAME()))
                            {
                                db.addNewCustomer(c);
                                Toast.makeText(AddNewCustomer.this, "Customer added successfully!", Toast.LENGTH_SHORT).show();
                            }

                    } catch (DataExists e) {
                        Toast.makeText(AddNewCustomer.this, "Customer already exists", Toast.LENGTH_SHORT).show();
                    }

                }
            }

                if (v.getId() == btnCancel.getId()) {
                    finish();
                }



        }
    }
}