package com.example.restaurantmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantmanagement.models.Waiter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createWaiterActivity extends AppCompatActivity {
    EditText et_waiterMail, et_waiterName, et_waiterPassword;
    Button btn_registerWaiter;

    private FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_waiter);

        et_waiterMail = (EditText)findViewById(R.id.et_waiterMail);
        et_waiterName= (EditText)findViewById(R.id.et_waiterName);
        et_waiterPassword = (EditText)findViewById(R.id.et_waiterPassword);
        btn_registerWaiter = (Button)findViewById(R.id.btn_registerWaiter);
        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference( "Waiters");


        btn_registerWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String waiterMailTxt = et_waiterMail.getText().toString();
                String waiterNameTxt = et_waiterName.getText().toString();
                String waiterPasswordTxt = et_waiterPassword.getText().toString();

                if( waiterMailTxt.isEmpty()){
                    et_waiterMail.setError("Enter an email!");
                    et_waiterMail.requestFocus();
                    return;
                }

                if( waiterNameTxt.isEmpty()){
                    et_waiterName.setError("Enter a name!");
                    et_waiterName.requestFocus();
                    return;
                }

                if( waiterPasswordTxt.isEmpty()){
                    et_waiterPassword.setError("Enter a password!");
                    et_waiterPassword.requestFocus();
                    return;
                }

                if ( waiterPasswordTxt.length() < 6){
                    et_waiterPassword.setError("Password must be at least 6 characters!");
                    et_waiterPassword.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(waiterMailTxt, waiterPasswordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();

                            //Get user's info
                            String email = user.getEmail();
                            String uid = user.getUid();

                            mRef.child(uid).setValue(new Waiter(waiterNameTxt, user.getEmail(), user.getUid()));
                            Toast.makeText(getApplicationContext(), "YOU CREATED THE WAITER", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
    }
}