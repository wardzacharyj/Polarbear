package origamiduck.com.polarbear.MyAdapters;

/**
 * Created by Zach on 9/13/16.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import origamiduck.com.polarbear.Model.RecipeSquare;
import origamiduck.com.polarbear.R;

public class MyGridViewAdapter extends RecyclerView.Adapter<MyGridViewHolder> {

    private List<RecipeSquare> itemList;
    private Context context;

    public MyGridViewAdapter(Context context, List<RecipeSquare> itemList) {
        this.context = context;
        this.itemList = itemList;

    }

    @Override
    public MyGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_square, null);
        MyGridViewHolder rcv = new MyGridViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MyGridViewHolder holder, int position) {

        Log.wtf("Loading",itemList.get(position).getImageURL());

        Picasso.with(context)
                .load(itemList.get(position).getImageURL())
                .into(holder.recipeImage);

        holder.title.setText(itemList.get(position).getTitle());
        holder.subtitle.setText(itemList.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
