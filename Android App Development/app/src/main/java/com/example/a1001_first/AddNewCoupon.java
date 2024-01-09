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

public class AddNewCoupon extends AppCompatActivity {

    EditText etTitle , etStartDate , etExpireDate , etPrice  , etAmount , etDescription , rtImageFile ,etCategory;
    Button btnCancel , btnADD ;

    Company company ;
    DB_Manager db = DB_Manager.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_coupon);


        etTitle = findViewById(R.id.AddNewCoupon_etTitle);
        etStartDate = findViewById(R.id.AddNewCoupon_etSDate);
        etExpireDate  = findViewById(R.id.AddNewCoupon_etEDate);
        etPrice = findViewById(R.id.AddNewCoupon_etPrice);
        etAmount = findViewById(R.id.AddNewCoupon_etAmount);
        etDescription = findViewById(R.id.AddNewCoupon_etDescribe);
        rtImageFile = findViewById(R.id.AddNewCoupon_etImageFile);
        etCategory = findViewById(R.id.editTextText) ;
        btnADD = findViewById(R.id.AddNewCoupon_btnADD);
        btnCancel = findViewById(R.id.AddNewCoupon_btnCANCEL);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ButtonsClick buttonsClick = new ButtonsClick();
        btnADD.setOnClickListener(buttonsClick);



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
            Intent intent = getIntent();
            company = (Company) intent.getSerializableExtra("selectedCompany");
            Coupon c = new Coupon("0", company.getId(), etCategory.getText().toString(), etTitle.getText().toString(), etDescription.getText().toString(), etStartDate.getText().toString(), etExpireDate.getText().toString(), Integer.parseInt(etAmount.getText().toString()), Double.parseDouble(etPrice.getText().toString()), rtImageFile.getText().toString());
            if (v.getId() == btnADD.getId()) {
                try {
                  if(!db.getCCoupons().contains(c.getTitle()))
                  {
                      db.addNewCoupon(c);
                      db.addNewCouponforCompany(company, c);
                      Toast.makeText(AddNewCoupon.this, "Coupon added successfully!", Toast.LENGTH_SHORT).show();
                  }

                } catch (DataExists e) {
                    Toast.makeText(AddNewCoupon.this, "Coupon already exists", Toast.LENGTH_SHORT).show();
                }

            }
        }





    }
}