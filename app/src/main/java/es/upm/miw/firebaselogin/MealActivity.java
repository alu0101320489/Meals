package es.upm.miw.firebaselogin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import es.upm.miw.firebaselogin.models.Meal;
import es.upm.miw.firebaselogin.models.MealModelFirebase;
import es.upm.miw.firebaselogin.models.Meals;
import es.upm.miw.firebaselogin.views.MealsViewAdapter;


public class MealActivity extends AppCompatActivity {

    private MealRESTAPIService apiService;

    private MealsViewAdapter adapter;

    final static String LOG_TAG = "MiW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meals_activity);
        Meals meals = getIntent().getExtras().getParcelable("meals");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        this.adapter = new MealsViewAdapter(new MealsViewAdapter.MealsDiff());
        adapter.submitList(meals.getMeals());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void saveMeal(View view) {
        String userEmail = getIntent().getStringExtra("user");
        String path = "users";
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mealsRef = database.getReference(path);

        TextView nameTextView = findViewById(R.id.nombre);
        TextView urlTextView = findViewById(R.id.url);
        Meal selectedMeal = adapter.getSelectedMeal();
        String mealName = selectedMeal.getStrMeal();
        Log.i("MealActivity", "Meal name: " + mealName);
        String strMealThumb = selectedMeal.getStrMealThumb();

        mealsRef.child(userEmail.substring(0, userEmail.indexOf("@"))).orderByChild("strMeal").equalTo(mealName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(MealActivity.this, "Ya existe en favoritos esta comida", Toast.LENGTH_SHORT).show();
                } else {

                    DatabaseReference userMealRef = mealsRef.child(userEmail.substring(0, userEmail.indexOf("@")));
                    String mealKey = userMealRef.push().getKey();
                    userMealRef.child(mealKey).setValue(new MealModelFirebase(mealName, strMealThumb));
                    Toast.makeText(MealActivity.this, "Comida guardada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MealActivity.this, "Error al verificar la comida: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
