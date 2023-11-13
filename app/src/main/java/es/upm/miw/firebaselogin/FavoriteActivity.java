package es.upm.miw.firebaselogin;

import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.firebaselogin.models.Meal;
import es.upm.miw.firebaselogin.views.FavoriteViewAdapter;
import es.upm.miw.firebaselogin.views.MealsViewAdapter;

public class FavoriteActivity extends AppCompatActivity {

    private FavoriteViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_activity);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewfavorite);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        adapter = new FavoriteViewAdapter(new FavoriteViewAdapter.MealsDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        String userEmail = getIntent().getStringExtra("user");
        String username = userEmail.substring(0, userEmail.indexOf("@"));
        String path = "users/" +username;

        DatabaseReference userMealsRef = FirebaseDatabase.getInstance().getReference(path);

        userMealsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Meal> meals = new ArrayList<>();

                for (DataSnapshot mealSnapshot : dataSnapshot.getChildren()) {
                    Meal meal = mealSnapshot.getValue(Meal.class);
                    meals.add(meal);
                }
                adapter.submitList(meals);
                Log.i("Favoritos", meals.get(0).getStrMealThumb());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FavoriteActivity.this, "Error al obtener recetas: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
