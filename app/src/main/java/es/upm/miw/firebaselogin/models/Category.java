package es.upm.miw.firebaselogin.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Category {

    @SerializedName("meals")
    @Expose
    private List<CategoryTypes> meals;

    public List<CategoryTypes> getMeals() {
        return meals;
    }

    public void setMeals(List<CategoryTypes> meals) {
        this.meals = meals;
    }

}