package origamiduck.com.polarbear.MyDatabase;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import origamiduck.com.polarbear.Model.CurrentCard;
import origamiduck.com.polarbear.MyAdapters.CurrentViewAdapter;
import origamiduck.com.polarbear.R;

/**
 * Created by Zach on 9/17/16.
 */
public class AsyncCurrentQuery extends AsyncTask<String , Void ,String>{
    private Context context;
    private RecyclerView rView;
    private ArrayList<CurrentCard> currentCards;

    public AsyncCurrentQuery(Context c, RecyclerView recyclerview){
        this.context = c;
        this.rView = recyclerview;
    }

    @Override
    protected String doInBackground(String... params) {
        DBHandler dbHandler = DBHandler.getInstance(context);

        List<Food> foods = dbHandler.getAllUniqueFoods();
        currentCards = new ArrayList<>();

        for(Food food : foods){
            currentCards.add(new CurrentCard(food.getID(),food.getName(),food.getBrand(),food.getNetWeight(), food.getImageURL()));
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(context));
        rView.setAdapter(new CurrentViewAdapter(context, currentCards));

    }

}
