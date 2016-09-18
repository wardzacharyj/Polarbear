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

import origamiduck.com.polarbear.Model.RecentCard;
import origamiduck.com.polarbear.Model.RecipeSquare;
import origamiduck.com.polarbear.R;

public class RecentViewAdapter extends RecyclerView.Adapter<RecentViewHolder> {

    private List<RecentCard> itemList;
    private Context context;

    public RecentViewAdapter(Context context, List<RecentCard> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public RecentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_card, null);
        RecentViewHolder rcv = new RecentViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecentViewHolder holder, int position) {
        holder.title.setText(itemList.get(position).getTitle());
        holder.subtitle.setText(itemList.get(position).getSubtitle());
        if(itemList.get(position).getPointLabel() <= 0)holder.pointLabel.setTextColor(context.getResources().getColor(R.color.red));
        else holder.pointLabel.setTextColor(context.getResources().getColor(R.color.green));
        holder.pointLabel.setText(itemList.get(position).getPointLabel()+" points");
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
