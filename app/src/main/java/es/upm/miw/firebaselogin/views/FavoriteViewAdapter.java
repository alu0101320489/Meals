package es.upm.miw.firebaselogin.views;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.upm.miw.firebaselogin.models.Meal;

public class FavoriteViewAdapter extends ListAdapter<Meal, FavoriteViewHolder> {

    public FavoriteViewAdapter(@NonNull DiffUtil.ItemCallback<Meal> diffCallback) {
        super(diffCallback);
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return FavoriteViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        Meal current = getItem(position);
        holder.bind(current.getStrMeal(), current.getIngredientsAndMeasures(), current.getStrInstructions(), current.getStrMealThumb());
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
