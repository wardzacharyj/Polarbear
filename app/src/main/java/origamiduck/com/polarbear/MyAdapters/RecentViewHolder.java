package origamiduck.com.polarbear.MyAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import origamiduck.com.polarbear.R;



public class RecentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected TextView title;
    protected TextView subtitle;

    protected TextView pointLabel;



    public RecentViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        title = (TextView)itemView.findViewById(R.id.textTitle);
        subtitle = (TextView)itemView.findViewById(R.id.textSubTitle);
        pointLabel = (TextView)itemView.findViewById(R.id.pointValue);


    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }
}

