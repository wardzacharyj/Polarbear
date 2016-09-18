package origamiduck.com.polarbear.MyAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import origamiduck.com.polarbear.R;

/**
 * Created by Zach on 9/17/16.
 */

public class CurrentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    protected ImageView itemImage;
    protected TextView title;
    protected TextView subtitle;
    protected TextView caption;

    public CurrentViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemImage = (ImageView)itemView.findViewById(R.id.food_image);
        title = (TextView)itemView.findViewById(R.id.textTitle);
        subtitle = (TextView)itemView.findViewById(R.id.textSubTitle);
        caption = (TextView) itemView.findViewById(R.id.textCaption);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }
}
