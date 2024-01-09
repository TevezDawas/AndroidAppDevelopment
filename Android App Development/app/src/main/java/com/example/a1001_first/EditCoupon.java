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

public class EditCoupon extends AppCompatActivity {
    EditText etTitle , etStartDate , etExpireDate , etPrice  , etAmount , etDescription , rtImageFile ,etCategory;
    Button btnCANCEL , btnADD ;

    Coupon coupon;

    DB_Manager db = DB_Manager.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_coupon);



        etTitle = findViewById(R.id.Edit_Coupon_etTitle);
        etStartDate = findViewById(R.id.Edit_Coupon_etSDate);
        etExpireDate  = findViewById(R.id.Edit_Coupon_etEDate);
        etPrice = findViewById(R.id.Edit_Coupon_etPrice);
        etAmount = findViewById(R.id.Edit_Coupon_etAmount);
        etDescription = findViewById(R.id.Edit_Coupon_etDescribe);
        rtImageFile = findViewById(R.id.Edit_Coupon_etImageFile);
        etCategory = findViewById(R.id.Edit_editTextText) ;
        btnADD = findViewById(R.id.Edit_Coupon_btnADD);
        btnCANCEL = findViewById(R.id.Edit_Coupon_btnCANCEL);


        ButtonsClick buttonsClick = new ButtonsClick();
        btnADD.setOnClickListener(buttonsClick);
        btnCANCEL.setOnClickListener(buttonsClick);


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

            if (v.getId() == btnADD.getId()) {
               try {
                   Coupon c = new Coupon(" 0",null,etCategory.getText().toString(),etTitle.getText().toString(),
                           etDescription.getText().toString(),etStartDate.getText().toString(),etExpireDate.getText().toString(),Integer.parseInt(etPrice.getText().toString()),Double.parseDouble(etAmount.getText().toString())
                           ,rtImageFile.getText().toString());
                   db.addNewCoupon(c);
                    Toast.makeText(EditCoupon.this, "Coupon Edited", Toast.LENGTH_SHORT).show();
                } catch (DataExists e) {
                    Toast.makeText(EditCoupon.this, "Coupon already Edited", Toast.LENGTH_SHORT).show();
                }

            }//

            if (v.getId() == btnCANCEL.getId()) {
                finish();
            }
        }


    }
}