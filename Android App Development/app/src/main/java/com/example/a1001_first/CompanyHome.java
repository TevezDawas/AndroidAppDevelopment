package com.example.a1001_first;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class CompanyHome extends AppCompatActivity {

    TextView FullNameText;
    Company company ;
    LinearLayout Home,Profile,myCoupons,newCoupon,customers;
    ImageView homeIcon, profileIcon,myCouponImg,customerImg,generateImg;
    TextView homeText,profileText,myCouponsText,generateText,customersText, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB_Manager db = DB_Manager.getInstance(this);
        setContentView(R.layout.activity_company_home);
        myCoupons = findViewById(R.id.myCoupons);
        logout = findViewById(R.id.logout);
        newCoupon = findViewById(R.id.generateCoupon);
        customers = findViewById(R.id.customers);
        myCouponImg = findViewById(R.id.myCouponsImg);
        generateImg = findViewById(R.id.generateImg);
        customerImg = findViewById(R.id.customersImg);
        myCouponsText = findViewById(R.id.myCouponsText);
        generateText = findViewById(R.id.generateText);
        customersText = findViewById(R.id.customersText);
        FullNameText = findViewById(R.id.companyNameView);
        homeText = findViewById(R.id.homeText);
        profileText = findViewById(R.id.profileText);
        Home = findViewById(R.id.home10);
        Profile = findViewById(R.id.profile10);
        profileIcon = findViewById(R.id.profileIcon);
        ButtonsClick buttonsClick = new ButtonsClick();
        myCoupons.setOnClickListener(buttonsClick);
        newCoupon.setOnClickListener(buttonsClick);
        customers.setOnClickListener(buttonsClick);
        homeIcon = findViewById(R.id.homeIcon);
        Intent intent = getIntent();
        company = (Company) intent.getSerializableExtra("selectedCompany");
        FullNameText.setText(company.getName());

        profileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyHome.this, CompanyProfile.class);
                intent.putExtra("selectedCompany", company);
                startActivity(intent);
            }
        });
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyHome.this, CompanyProfile.class);
                intent.putExtra("selectedCompany", company);
                startActivity(intent);
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyHome.this, CompanyProfile.class);
                intent.putExtra("selectedCompany", company);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompanyHome.this, MainActivity.class));
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


    private void CreateNewCouponActivity(Company company) {
        Intent intent = new Intent(CompanyHome.this, AddNewCoupon.class);
        intent.putExtra("selectedCompany", company);
        launcher.launch(intent);
    }

    private void ViewCouponsActivity() {
        Intent intent = new Intent(CompanyHome.this, ListCouponsForCompany.class);
        intent.putExtra("selectedCompany", company);
        launcher.launch(intent);
    }

    private void EditProfileActivity( Company company) {
        Intent intent = new Intent(CompanyHome.this, EditCompany.class);
        intent.putExtra("selectedCompany", company);
        launcher.launch(intent);
    }


    private void VieCustomersActivity() {
        Intent intent = new Intent(CompanyHome.this, ListCustomersForCompany.class);
        intent.putExtra("selectedCompany", company);
        launcher.launch(intent);
    }


    class ButtonsClick implements View.OnClickListener {
        public void onClick(View v) {

            if (v.getId() == newCoupon.getId()) {
                CreateNewCouponActivity(company);
            }

            if (v.getId() == generateImg.getId()) {
                CreateNewCouponActivity(company);
            }
            if (v.getId() == generateText.getId()) {
                CreateNewCouponActivity(company);
            }


            if (v.getId() == customers.getId()) {
                VieCustomersActivity();
            }

            if (v.getId() == customerImg.getId()) {
                VieCustomersActivity();
            }
            if (v.getId() == customersText.getId()) {
                VieCustomersActivity();
            }


            if (v.getId() == myCoupons.getId()) {
                ViewCouponsActivity();
            }

            if (v.getId() == myCouponsText.getId()) {
                ViewCouponsActivity();
            }


            if (v.getId() == myCouponImg.getId()) {
                ViewCouponsActivity();
            }


        }
    }
}









