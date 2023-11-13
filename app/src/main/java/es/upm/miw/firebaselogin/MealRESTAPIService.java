package es.upm.miw.firebaselogin;

import es.upm.miw.firebaselogin.models.Category;
import es.upm.miw.firebaselogin.models.Meal;
import es.upm.miw.firebaselogin.models.Meals;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MealRESTAPIService {

    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String s);

    @GET("search.php")
    Call<Meals> getMealsByFirstLetter(@Query("f") String f);

    @GET("filter.php")
    Call<Meals> getMealsByRegion(@Query("a") String a);

    @GET("list.php?c=list")
    Call<Category> getCategories();

}
