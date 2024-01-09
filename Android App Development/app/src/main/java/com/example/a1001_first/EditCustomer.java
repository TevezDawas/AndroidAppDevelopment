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

public class EditCustomer extends AppCompatActivity {
    EditText   etEMail , etPassword , etConfirmPassword ;

    TextView etName;
    Button btnCancel , btnEdit ;
    ImageView goBack;
    DB_Manager db = DB_Manager.getInstance(this);
    Customer customer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);


        Intent intent = getIntent();
        customer2 = (Customer) intent.getSerializableExtra("selectedCustomer");


        etName = findViewById(R.id.editCustomer_etName);
        etEMail = findViewById(R.id.editCustomer_etEMail);
        etPassword = findViewById(R.id.editCustomer_etPassword) ;
        etConfirmPassword = findViewById(R.id.editCustomer_etConfirmPassword);


        etName.setHint(customer2.getFIRST_NAME() +" "+customer2.getLAST_NAME());
        etEMail.setHint(customer2.getEMAIL());
        etName.setKeyListener(null);

        btnCancel = findViewById(R.id.editCustomer_CANCEL) ;
        btnEdit = findViewById(R.id.editCustomer_Edit) ;

        ButtonsClick buttonsClick = new ButtonsClick();
        btnEdit.setOnClickListener(buttonsClick);
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

            if (v.getId() == btnEdit.getId()) {


                try {
                    db.getdeleteCustomer(customer2);
                    Customer c = new Customer("0",etName.getText().toString()," ",etEMail.getText().toString(),etPassword.getText().toString());
                    db.addNewCustomer(c);
                    startActivity(new Intent(EditCustomer.this, MainActivity.class));


                    Toast.makeText(EditCustomer.this, "Customer is Edited", Toast.LENGTH_SHORT).show();
                } catch (DataExists e) {
                    throw new RuntimeException(e);
                }
            }

                if (v.getId() == btnCancel.getId()) {
                    finish();
                }




        }
}
}