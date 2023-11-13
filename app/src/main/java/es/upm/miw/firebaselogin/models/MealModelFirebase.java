package es.upm.miw.firebaselogin.models;

import com.google.firebase.database.PropertyName;

public class MealModelFirebase {
    @PropertyName("strMeal")
    private String strMeal;
    @PropertyName("strMealThumb")
    private String strMealThumb;

    public MealModelFirebase() {
    }

    public MealModelFirebase(String strMeal, String strMealThumb) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }
}
