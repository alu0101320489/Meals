package es.upm.miw.firebaselogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import es.upm.miw.firebaselogin.models.Meals;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    final static String LOG_TAG = "MiW";

    private static final String API_BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private MealRESTAPIService apiService;

    private String[] regions = {"American", "British", "Canadian", "Chinese", "Croatian", "Dutch", "Egyptian", "Filipino", "French", "Greek", "Indian", "Irish", "Italian", "Jamaican", "Japanese", "Kenyan", "Malaysian", "Mexican", "Moroccan", "Polish", "Portuguese", "Russian", "Spanish", "Thai", "Tunisian", "Turkish", "Unknown", "Vietnamese"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(MealRESTAPIService.class);
    }

    public void getMealByName(View v) {

        EditText evname = findViewById(R.id.editText);
        String name = evname.getText().toString();
        Call<Meals> call_async = apiService.getMealByName(name);

        call_async.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                Meals meal = response.body();
                Log.i(LOG_TAG, "Comida: " + name);
                if (null != meal) {

                    Intent intent = new Intent(SearchActivity.this, MealActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("meals", meal);
                    String username = getIntent().getStringExtra("user");
                    intent.putExtra("user", username);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.strError), Toast.LENGTH_SHORT).show();
                    Log.i(LOG_TAG, getString(R.string.strError));
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    public void getMealsByFirstLetter(View v) {

            EditText evname = findViewById(R.id.editText2);
            String name = evname.getText().toString();
            Call<Meals> call_async = apiService.getMealsByFirstLetter(name);

            call_async.enqueue(new Callback<Meals>() {
                @Override
                public void onResponse(Call<Meals> call, Response<Meals> response) {
                    Meals meal = response.body();
                    Log.i(LOG_TAG, "Comida: " + name);
                    if (null != meal) {

                        Intent intent = new Intent(SearchActivity.this, MealActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("meals", meal);
                        String username = getIntent().getStringExtra("user");
                        intent.putExtra("user", username);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.strError), Toast.LENGTH_SHORT).show();
                        Log.i(LOG_TAG, getString(R.string.strError));
                    }
                }

                @Override
                public void onFailure(Call<Meals> call, Throwable t) {
                    Toast.makeText(
                            getApplicationContext(),
                            "ERROR: " + t.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
    }

    public void getMealsByRegion(View v) {

            EditText evname = findViewById(R.id.editText3);
            String name = evname.getText().toString();
            Call<Meals> call_async = apiService.getMealsByRegion(name);

            call_async.enqueue(new Callback<Meals>() {
                @Override
                public void onResponse(Call<Meals> call, Response<Meals> response) {
                    Meals meal = response.body();
                    Log.i(LOG_TAG, "Comida: " + name);
                    if (null != meal) {

                        Intent intent = new Intent(SearchActivity.this, MealActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("meals", meal);
                        String username = getIntent().getStringExtra("user");
                        intent.putExtra("user", username);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.strError), Toast.LENGTH_SHORT).show();
                        Log.i(LOG_TAG, getString(R.string.strError));
                    }
                }

                @Override
                public void onFailure(Call<Meals> call, Throwable t) {
                    Toast.makeText(
                            getApplicationContext(),
                            "ERROR: " + t.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
    }

    public void showRegionListDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.regions_list, null);

        ListView regionListView = dialogView.findViewById(R.id.regionListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, regions);
        regionListView.setAdapter(adapter);

        builder.setTitle("Lista de Regiones")
                .setView(dialogView)
                .setPositiveButton("Cerrar", null)
                .show();
    }
}
