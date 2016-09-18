package origamiduck.com.polarbear;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import origamiduck.com.polarbear.Model.RecentCard;
import origamiduck.com.polarbear.MyAdapters.RecentViewAdapter;

public class RecentFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent, container, false);
        List<RecentCard> rowListItem = getRecentActions();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recent_dummy_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecentViewAdapter(getActivity(), rowListItem));
        return view;
    }

    private List<RecentCard> getRecentActions(){
        List<RecentCard> allItems = new ArrayList<>();
        allItems.add(new RecentCard("Title", "Subtitle",8));
        allItems.add(new RecentCard("Title", "Subtitle",-13));
        allItems.add(new RecentCard("Title", "Subtitle",18));
        allItems.add(new RecentCard("Title", "Subtitle",5));
        return allItems;
    }
}
