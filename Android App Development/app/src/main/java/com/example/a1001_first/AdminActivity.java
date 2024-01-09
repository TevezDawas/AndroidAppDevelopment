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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    LinearLayout manageCompanies, manageCustomers ,addCustomer,addCompany;
    ImageView manageCompaniesImg, manageCustomersImg ,addCustomerImg ,addCompanyImg;
    TextView manageCompaniesText, manageCustomersText ,addCustomerText,addCompanyText,logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        manageCustomers = findViewById(R.id.layoutManCustomer);
        manageCompanies = findViewById(R.id.layoutmanCompany);
        addCustomer = findViewById(R.id.layoutNewCustomer);
        addCompany = findViewById(R.id.layoutNewCompany);
        manageCustomersImg = findViewById(R.id.manCustomerImage);
        manageCompaniesImg = findViewById(R.id.manCompanyImage);
        addCustomerImg = findViewById(R.id.newCustomerImage);
        addCompanyImg = findViewById(R.id.NewCompanyImage);
        manageCustomersText = findViewById(R.id.manCustomerName);
        manageCompaniesText = findViewById(R.id.manCompanyName);
        addCustomerText = findViewById(R.id.newCustomerName);
        addCompanyText = findViewById(R.id.NewCompanyName);
        logout =findViewById(R.id.logout);

        ButtonsClick buttonsClick = new ButtonsClick();
        manageCustomers.setOnClickListener(buttonsClick);
        manageCompanies.setOnClickListener(buttonsClick);
        addCustomer.setOnClickListener(buttonsClick);
        addCompany.setOnClickListener(buttonsClick);
        manageCustomersImg.setOnClickListener(buttonsClick);
        manageCompaniesImg.setOnClickListener(buttonsClick);
        addCustomerImg.setOnClickListener(buttonsClick);
        addCompanyImg.setOnClickListener(buttonsClick);
        manageCustomersText.setOnClickListener(buttonsClick);
        manageCompaniesText.setOnClickListener(buttonsClick);
        addCustomerText.setOnClickListener(buttonsClick);
        addCompanyText.setOnClickListener(buttonsClick);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, MainActivity.class));
            }
        });
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

    private void OpenMangeCustomerActivity() {
        Intent intent = new Intent(AdminActivity.this, List_Customer_For_Admin.class);
        intent.putExtra("requestCode", 1);
        launcher.launch(intent);
    }
    private void OpenMangeCompanyActivity() {
        Intent intent = new Intent(AdminActivity.this, List_Company_For_Admin.class);
        intent.putExtra("requestCode", 2);
        launcher.launch(intent);
    }
    private void OpenADDCompanyActivity() {
        Intent intent = new Intent(AdminActivity.this, AddNewCompany.class);
        intent.putExtra("requestCode", 3);
        launcher.launch(intent);
    }
    private void OpenADDCustomerActivity() {
        Intent intent = new Intent(AdminActivity.this, AddNewCustomer.class);
        intent.putExtra("requestCode", 4);
        launcher.launch(intent);
    }

    class ButtonsClick implements View.OnClickListener {
        public void onClick(View v) {


            if (v.getId() == manageCustomers.getId()) {
                OpenMangeCustomerActivity();
            }
            if (v.getId() == manageCustomersImg.getId()) {
                OpenMangeCustomerActivity();
            }
            if (v.getId() == manageCustomersText.getId()) {
                OpenMangeCustomerActivity();
            }
            if (v.getId() == manageCompanies.getId()) {
                OpenMangeCompanyActivity();
            }
            if (v.getId() == manageCompaniesImg.getId()) {
                OpenMangeCompanyActivity();
            }
            if (v.getId() == manageCompaniesText.getId()) {
                OpenMangeCompanyActivity();
            }
            if (v.getId() == addCustomer.getId()) {
                OpenADDCustomerActivity();
            }
            if (v.getId() == addCustomerImg.getId()) {
                OpenADDCustomerActivity();
            }
            if (v.getId() == addCustomerText.getId()) {
                OpenADDCustomerActivity();
            }
            if (v.getId() == addCompany.getId()) {
                OpenADDCompanyActivity();
            }
            if (v.getId() == addCompanyImg.getId()) {
                OpenADDCompanyActivity();
            }
            if (v.getId() == addCompanyText.getId()) {
                OpenADDCompanyActivity();
            }
        }
    }
}