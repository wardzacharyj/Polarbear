package origamiduck.com.polarbear;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import origamiduck.com.polarbear.Model.RecipeSquare;
import origamiduck.com.polarbear.MyAdapters.GridSpacingItemDecoration;
import origamiduck.com.polarbear.MyAdapters.MyGridViewAdapter;
import origamiduck.com.polarbear.MyDatabase.AsyncRecipe;

/**
 * Created by Zach on 9/13/16.
 */
public class SuggestionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suggestion, container, false);

        List<RecipeSquare> rowListItem = getSuggestions();


        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recipe_grid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(new MyGridViewAdapter(getActivity(), rowListItem));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 8, false));   //colums, spacing, edge?


        AsyncRecipe asyncRecipe = new AsyncRecipe(getActivity(),recyclerView);
        asyncRecipe.execute("");


        return view;
    }

    /////////////////////////////////////////////////////////////////////////////////////

    private List<RecipeSquare> getSuggestions(){

        List<RecipeSquare> allItems = new ArrayList<>();
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));
        allItems.add(new RecipeSquare("https://cbsla.files.wordpress.com/2013/09/apple-pie-thinkstock.jpg", "title","subtitle"));

        return allItems;
    }



    /////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onRefresh() {
        refreshItems();
    }


    void refreshItems() {
        //recyclerView.removeAllViews();
        // Load new items if there
        // ...

        // Load complete
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        //recyclerView.getAdapter().notifyDataSetChanged();
    }
}