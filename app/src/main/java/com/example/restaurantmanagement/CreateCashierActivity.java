package com.example.restaurantmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantmanagement.models.Cashier;
import com.example.restaurantmanagement.models.Waiter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateCashierActivity extends AppCompatActivity {
    EditText et_cashierMail, et_cashierName, et_cashierPassword;
    Button btn_registerCashier;

    private FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cashier);

        et_cashierMail = (EditText)findViewById(R.id.et_cashierMail);
        et_cashierName= (EditText)findViewById(R.id.et_cashierName);
        et_cashierPassword = (EditText)findViewById(R.id.et_cashierPassword);
        btn_registerCashier= (Button)findViewById(R.id.btn_registerCashier);
        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference( "Cashiers");

        btn_registerCashier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cashierMailTxt = et_cashierMail.getText().toString();
                String cashierNameTxt = et_cashierName.getText().toString();
                String cashierPasswordTxt = et_cashierPassword.getText().toString();

                if( cashierMailTxt.isEmpty()){
                    et_cashierMail.setError("Enter an email!");
                    et_cashierMail.requestFocus();
                    return;
                }

                if( cashierNameTxt.isEmpty()){
                    et_cashierName.setError("Enter a name!");
                    et_cashierName.requestFocus();
                    return;
                }

                if( cashierPasswordTxt.isEmpty()){
                    et_cashierPassword.setError("Enter a password!");
                    et_cashierPassword.requestFocus();
                    return;
                }

                if ( cashierPasswordTxt.length() < 6){
                    et_cashierPassword.setError("Password must be at least 6 characters!");
                    et_cashierPassword.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(cashierMailTxt, cashierPasswordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();

                            //Get user's info
                            String email = user.getEmail();
                            String uid = user.getUid();

                            mRef.child(uid).setValue(new Cashier(cashierNameTxt, user.getEmail(), user.getUid()));
                            Toast.makeText(getApplicationContext(), "YOU CREATED THE CASHIER", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
    }
}
