package es.upm.miw.firebaselogin.views;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import es.upm.miw.firebaselogin.R;

public class MealsViewHolder extends RecyclerView.ViewHolder {

    private final TextView name;
    private final TextView ingredients;
    private final TextView instructions;
    private final ImageView imageView;

    private final TextView url;

    public MealsViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.nombre);
        ingredients = itemView.findViewById(R.id.ingredientes);
        instructions = itemView.findViewById(R.id.instrucciones);
        imageView = itemView.findViewById(R.id.imageView);
        url = itemView.findViewById(R.id.url);
    }

    public void bind(String name, String ingredients, String instructions, String imageUrl) {
        this.name.setText(name);
        this.ingredients.setText(ingredients);
        this.instructions.setText(instructions);
        this.url.setText(imageUrl);
        Picasso.get().load(imageUrl).into(this.imageView);
    }

    static MealsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item, parent, false);
        return new MealsViewHolder(view);
    }
}