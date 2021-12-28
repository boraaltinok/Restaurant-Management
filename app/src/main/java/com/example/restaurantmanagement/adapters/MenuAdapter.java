package com.example.restaurantmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.models.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyMenuHolder> {

    Context context;
    List<List<Food>> menu = new ArrayList<List<Food>>();////Food Category 1:çorba 2:ara yemek 3:ana yemek 4:tatlı 5:içecek

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef= database.getReference( "menu");



    public MenuAdapter(Context ct){
        this.context = ct;
        for(int i = 0; i < 4;i++){
            //RETRIEVE THE MENU
        }
    }

    @Override
    public MenuAdapter.MyMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_row, parent, false);
        return new MyMenuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyMenuHolder holder, int position) {
        /*holder.text_foodName.setText(days[position] + ". DAY");
        holder.text_foodDescription.setText(user.month.get(position).exerciseProgramList.size() + "exercises");*/

        /*holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SelectedDaysProgram.class);
                intent.putExtra("exercise count", user.month.get(position).exerciseProgramList.size());
                intent.putExtra("day of month", days[position]);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class MyMenuHolder extends RecyclerView.ViewHolder{
        TextView text_foodName, text_foodDescription;
        public MyMenuHolder(@NonNull View itemView) {
            super(itemView);
            text_foodName = itemView.findViewById(R.id.txt_food);
            text_foodDescription = itemView.findViewById(R.id.txt_foodDescription);
        }
    }
}
