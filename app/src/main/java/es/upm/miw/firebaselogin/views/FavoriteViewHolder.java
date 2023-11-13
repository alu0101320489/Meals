package es.upm.miw.firebaselogin.views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import es.upm.miw.firebaselogin.R;

public class FavoriteViewHolder extends RecyclerView.ViewHolder {

    private final TextView name;
    private final TextView ingredients;
    private final TextView instructions;
    private final ImageView imageView;

    public FavoriteViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.nombre);
        ingredients = itemView.findViewById(R.id.ingredientes);
        instructions = itemView.findViewById(R.id.instrucciones);
        imageView = itemView.findViewById(R.id.imageView);
    }

    public void bind(String name, String ingredients, String instructions, String imageUrl) {
        this.name.setText(name);
        this.ingredients.setText(ingredients);
        this.instructions.setText(instructions);
        Picasso.get().load(imageUrl).into(imageView);
    }

    static FavoriteViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_item, parent, false);
        return new FavoriteViewHolder(view);
    }
}
