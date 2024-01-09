package com.example.a1001_first;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerHomeActivity extends AppCompatActivity {
    TextView welcomeText, textUsername, foodName, electName, restName, vacationName, textTrending,homeText,profileText,logout;
    ImageView  searchIcon, FoodIcon, ElectIcon, RestIcon, VacationIcon, homeIcon, profileIcon;
    EditText searchText;
    LinearLayout Food, Elect, Rest, Vacation,Home,Profile;
    BottomNavigationView nav;
    View navView;

    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);



        homeIcon = findViewById(R.id.homeIcon);
        profileIcon = findViewById(R.id.profileIcon);
        homeText = findViewById(R.id.homeText);
        profileText = findViewById(R.id.profileText);
        Home = findViewById(R.id.home10);
        Profile = findViewById(R.id.profile10);
        welcomeText = findViewById(R.id.textWelcome);
        textUsername = findViewById(R.id.textUserName);
        foodName = findViewById(R.id.foodName);
        electName = findViewById(R.id.electName);
        restName = findViewById(R.id.restName);
        vacationName = findViewById(R.id.vacationName);
        textTrending = findViewById(R.id.textTrending);
        logout = findViewById(R.id.logout);
        searchIcon = findViewById(R.id.searchIcon);
        FoodIcon = findViewById(R.id.foodImage);
        ElectIcon = findViewById(R.id.electImage);
        RestIcon = findViewById(R.id.restImage);
        VacationIcon = findViewById(R.id.vacationImage);
        searchText = findViewById(R.id.searchText);
        Food = findViewById(R.id.layoutFood);
        Elect = findViewById(R.id.layoutElectricity);
        Rest = findViewById(R.id.layoutRest);
        Vacation = findViewById(R.id.layoutVacation);
        nav = findViewById(R.id.bottomNavigationView);
        navView = findViewById(R.id.viewDivider);

        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("selectedCustomer");
        textUsername.setText(customer.getFIRST_NAME() +" "+customer.getLAST_NAME());

        foodName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, FoodCouponsList.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        FoodIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, FoodCouponsList.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CustomerHomeActivity.this, FoodCouponsList.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        electName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, ElectricityCoupons.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        ElectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, ElectricityCoupons.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        Elect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, ElectricityCoupons.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        restName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, RestaurantCoupons.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        RestIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, RestaurantCoupons.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        Rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, RestaurantCoupons.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        vacationName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, VacationCoupons.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        VacationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, VacationCoupons.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        Vacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, VacationCoupons.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });

        profileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, CustomerActivity.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, CustomerActivity.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, CustomerActivity.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, CustomerHomeActivity.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHomeActivity.this, CustomerHomeActivity.class);
                intent.putExtra("selectedCustomer", customer);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}