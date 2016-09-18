package origamiduck.com.polarbear;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import origamiduck.com.polarbear.MyDatabase.AsyncCurrentQuery;

public class CurrentFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.current_dummy_recyclerview);
        new AsyncCurrentQuery(getActivity(),recyclerView).execute("");
        return view;
    }



}


