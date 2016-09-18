package origamiduck.com.polarbear.MyAdapters;

/**
 * Created by Zach on 9/13/16.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import origamiduck.com.polarbear.R;

public class MyGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    protected ImageView recipeImage;
    protected TextView title;
    protected TextView subtitle;



    public MyGridViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        recipeImage = (ImageView)itemView.findViewById(R.id.square_image);
        title = (TextView)itemView.findViewById(R.id.text_title);
        subtitle = (TextView)itemView.findViewById(R.id.text_subtitle);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }
}
