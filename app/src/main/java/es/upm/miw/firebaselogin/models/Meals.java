package es.upm.miw.firebaselogin.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Meals implements Parcelable {

    @SerializedName("meals")
    @Expose
    private ArrayList<Meal> meals;

    public Meals() {
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public String getMeal(int index) {
        return this.meals.get(index).getStrMealThumb();
    }

    // Parcelable methods
    protected Meals(Parcel in) {
        meals = in.createTypedArrayList(Meal.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(meals);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Meals> CREATOR = new Creator<Meals>() {
        @Override
        public Meals createFromParcel(Parcel in) {
            return new Meals(in);
        }

        @Override
        public Meals[] newArray(int size) {
            return new Meals[size];
        }
    };
}
