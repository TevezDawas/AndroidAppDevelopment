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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;

public class EditCompany extends AppCompatActivity   {

    EditText   etEMail , etPassword , etConfirmPassword ;
    TextView etName;
    Button btnCancel , btnEdit ;


    DB_Manager db = DB_Manager.getInstance(this);
    Company company2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_company);

        Intent intent = getIntent();
        company2 = (Company) intent.getSerializableExtra("selectedCompany");



        etName = findViewById(R.id.editCompany_etName);
        etEMail = findViewById(R.id.editCompany_etEMail);
        etPassword = findViewById(R.id.editCompany_etPassword) ;
        etConfirmPassword = findViewById(R.id.editCompany_etConfirmPassword);


        etName.setHint(company2.getName());
        etName.setKeyListener(null);
        etEMail.setHint(company2.getEmail());

        btnCancel = findViewById(R.id.editCompany_CANCEL) ;
        btnEdit = findViewById(R.id.editCompany_Edit) ;

        ButtonsClick buttonsClick = new ButtonsClick();
        btnEdit.setOnClickListener(buttonsClick);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

    class ButtonsClick implements View.OnClickListener {
        public void onClick(View v) {

            if (v.getId() == btnEdit.getId()) {

                try {
                    Company c = new Company(" ", etName.getText().toString(), etEMail.getText().toString(), etPassword.getText().toString());
                    db.addNewCompany(c);
                    Toast.makeText(EditCompany.this, "Company Edited", Toast.LENGTH_SHORT).show();
                    db.getdeleteCompany(company2);
                    startActivity(new Intent(EditCompany.this, MainActivity.class));


                } catch (DataExists e) {
                    Toast.makeText(EditCompany.this, "Company already Edited", Toast.LENGTH_SHORT).show();
                }
            }

            }


        }
    }
