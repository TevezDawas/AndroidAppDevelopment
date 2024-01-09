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

public class AddNewCompany extends AppCompatActivity {

    EditText etName, etEMail, etPassword, etConfirmPassword;
    Button btnCancel, btnAdd;
    DB_Manager db = DB_Manager.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_add_new_company);
        etName = findViewById(R.id.AddNewCompany_etName);
        etEMail = findViewById(R.id.AddNewCompany_etEMail);
        etPassword = findViewById(R.id.AddNewCompany_etPassword);
        etConfirmPassword = findViewById(R.id.AddNewCompany_etConfirmPassword);

        btnAdd = findViewById(R.id.AddCompany_ADD);
        btnCancel = findViewById(R.id.AddCompany_CANCEL);

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

                    Company c = new Company("0", etName.getText().toString(), etEMail.getText().toString(), etPassword.getText().toString());

                    try {
                        if(!db.getCompanys().contains(c.getName()))
                        db.addNewCompany(c);
                        Toast.makeText(AddNewCompany.this, "Company added successfully!", Toast.LENGTH_SHORT).show();

                    } catch (DataExists e) {
                        Toast.makeText(AddNewCompany.this, "Company already exists", Toast.LENGTH_SHORT).show();
                    }


                    // do the function of adding the customer

                } else {
                    Toast.makeText(AddNewCompany.this, "Confirm Password Failed ", Toast.LENGTH_SHORT).show();
                }
            }
                if (v.getId() == btnCancel.getId()) {
                    finish();
                }

        }


    }
}