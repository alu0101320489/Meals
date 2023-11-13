package es.upm.miw.firebaselogin.views;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import es.upm.miw.firebaselogin.models.Meal;

public class MealsViewAdapter extends ListAdapter<Meal, MealsViewHolder> {

    private Meal meal;

    public MealsViewAdapter(@NonNull DiffUtil.ItemCallback<Meal> diffCallback) {
        super(diffCallback);
    }

    @Override
    public MealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MealsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(MealsViewHolder holder, int position) {
        Meal current = getItem(position);
        if (position == 0) {
            meal = current;
        } else {
            meal = getItem(position - 1);
        }
        holder.bind(current.getStrMeal(), current.getIngredientsAndMeasures(), current.getStrInstructions(), current.getStrMealThumb());
    }

    public Meal getSelectedMeal() {
        return meal;
    }

    public static class MealsDiff extends DiffUtil.ItemCallback<Meal> {

        @Override
        public boolean areItemsTheSame(@NonNull Meal oldItem, @NonNull Meal newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Meal oldItem, @NonNull Meal newItem) {
            return oldItem.getStrMeal().equals(newItem.getStrMeal());
        }
    }
}

