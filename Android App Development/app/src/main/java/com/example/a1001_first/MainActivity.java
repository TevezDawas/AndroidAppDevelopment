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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

        Button btnLogin , btnSignCompany , btnSignCustomer ;
        RadioGroup rgUserType;
        EditText etUN, etPass;
        UserType userType = null;
        RadioButton rbAdmin, rbCustomer, rbCompany;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            rgUserType = findViewById(R.id.main_rgUserType);
            etUN = findViewById(R.id.main_etUN);
            etPass = findViewById(R.id.main_etPass);
            btnLogin = findViewById(R.id.main_btnLogin);
            rbAdmin = findViewById(R.id.main_rbAdmin);
            rbCustomer = findViewById(R.id.main_rbCustomer);
            rbCompany = findViewById(R.id.main_rbCompany);
            btnSignCompany = findViewById(R.id.main_SignCompany) ;
            btnSignCustomer = findViewById(R.id.main_SingCustomer);
            ButtonsClick buttonsClick = new ButtonsClick();
            btnLogin.setOnClickListener(buttonsClick);
            btnSignCompany .setOnClickListener(buttonsClick);
            btnSignCustomer.setOnClickListener(buttonsClick);
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


        private void OpenAdminActivity() {
            Intent intent = new Intent(MainActivity.this,AdminActivity.class);
            intent.putExtra("requestCode", 1);
            launcher.launch(intent);

        }

        private void OpenCustomerActivity(Customer customer) {
            Intent I=new Intent(MainActivity.this, CustomerHomeActivity.class);
            I.putExtra("selectedCustomer", (Serializable) customer);
            launcher.launch(I);
        }

        private void OpenCompanyActivity(Company company) {
            Intent intent = new Intent(MainActivity.this, CompanyHome.class);//no act yet
            intent.putExtra("requestCode", 3);
            intent.putExtra("selectedCompany", (Serializable) company);
            launcher.launch(intent);
        }


        private void OpenADDCompanyActivity() {
            Intent intent = new Intent(MainActivity.this, AddNewCompany.class);
            intent.putExtra("requestCode", 4);
            launcher.launch(intent);
        }

        private void OpenADDCustomerActivity() {
            Intent intent = new Intent(MainActivity.this, AddNewCustomer.class);
            intent.putExtra("requestCode", 5);
            launcher.launch(intent);
        }


        class ButtonsClick implements View.OnClickListener {
            public void onClick(View v) {

                if (rbAdmin.isChecked())
                    userType = UserType.ADMIN;
                if (rbCustomer.isChecked())
                    userType = UserType.CUSTOMER;
                if (rbCompany.isChecked())
                    userType = UserType.COMPANY;

                if (v.getId() == btnLogin.getId()) {
                    String un = etUN.getText().toString();
                    String pass = etPass.getText().toString();


                    Object o = Login.getInstance(MainActivity.this).signIn(un, pass, userType);
                    if (o == null) {
                        Toast.makeText(MainActivity.this, "Error User Name or Password", Toast.LENGTH_SHORT).show();
                        return;
                    }if (o instanceof Boolean) {

                        OpenAdminActivity();
                    }if (o instanceof Customer) {
                        OpenCustomerActivity((Customer) o);
                    }

                    if (o instanceof Company) {
                        OpenCompanyActivity((Company) o);
                    }

                }

                if (v.getId() == btnSignCompany.getId()) {
                    OpenADDCompanyActivity() ;

                }

                if (v.getId() == btnSignCustomer.getId()) {
                    OpenADDCustomerActivity() ;
                }


            }
        }
    }
