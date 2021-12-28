package com.example.restaurantmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.restaurantmanagement.models.Food;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMenuItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText description, et_foodName;
    EditText price;
    Button add;
    Button cancel;
    int foodCategory;
    private DatabaseReference mDatabase;
    Spinner foodChoiceSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);

        description = findViewById(R.id.food_description);
        price = findViewById(R.id.food_price);
        add = findViewById(R.id.add_food);
        cancel = findViewById(R.id.add_food_cancel);
        foodChoiceSpinner = (Spinner)findViewById(R.id.spinner1);
        et_foodName = (EditText)findViewById(R.id.et_foodName);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.foodChoice,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodChoiceSpinner.setAdapter(adapter);
        foodChoiceSpinner.setOnItemSelectedListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("menu");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descriptionTxt = description.getText().toString();
                Double priceTxt = new Double(price.getText().toString());
                String foodNameTxt = et_foodName.getText().toString();



                if(descriptionTxt.isEmpty() || priceTxt == 0.0){
                    Toast.makeText( getApplicationContext(), "No fields can be left empty!", Toast.LENGTH_SHORT);
                    return;
                }

                //mDatabase = FirebaseDatabase.getInstance().getReference("users");
                Food user = new Food(foodNameTxt, descriptionTxt, priceTxt, foodCategory);
                Toast.makeText(getApplicationContext(), ""+user.getDescription(), Toast.LENGTH_SHORT).show();
                mDatabase.child(foodCategory+"").child(foodNameTxt+"").setValue(user);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EditMenuActivity.class));
                finish();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        foodCategory = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}