package origamiduck.com.polarbear.MyAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import java.util.List;
import origamiduck.com.polarbear.Model.CurrentCard;
import origamiduck.com.polarbear.R;


public class CurrentViewAdapter extends RecyclerView.Adapter<CurrentViewHolder> {

    private List<CurrentCard> itemList;
    private Context context;

    public CurrentViewAdapter(Context context, List<CurrentCard> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public CurrentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_current_card, null);
        CurrentViewHolder rcv = new CurrentViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(CurrentViewHolder holder, int position) {
        if( itemList.get(position).getImageURL() != null &&  itemList.get(position).getImageURL().equals(""))
            Picasso.with(context).load(itemList.get(position).getImageURL()).into(holder.itemImage);

        holder.title.setText(itemList.get(position).getTitle());
        holder.subtitle.setText(itemList.get(position).getSubTitle());
        holder.caption.setText(itemList.get(position).getCaption());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}

