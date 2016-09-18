package origamiduck.com.polarbear.MyDatabase;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import origamiduck.com.polarbear.R;


public class AsyncDBCheck extends AsyncTask<String , Void ,String> implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private String server_response;

    private DBHandler db;
    private Context context;
    private String barcode;

    private CoordinatorLayout coordinatorLayout;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private EditText editTextName;
    private EditText editTextBrand;
    private TextView textViewExpLabel;
    private Spinner spinnerFoodType;
    private EditText editTextNetWeight; private Spinner spinnerNetWeightUnit;

    private String imageUrl = "";
    private String entDate = "";
    private String expDate = "";

    private String name = "";
    private String brand = "";
    private String type = "";
    private String netWeight = "";


    private int skipFlag = -1;

    private FloatingActionButton fab;
    public AsyncDBCheck(Context context, String barcode) {
        db = DBHandler.getInstance(context);
        this.context = context;
        this.barcode = barcode;
        fab = (FloatingActionButton) ((Activity) this.context).findViewById(R.id.confirm_entry);
        fab.setOnClickListener(this);



        coordinatorLayout = (CoordinatorLayout) ((Activity) this.context).findViewById(R.id.coor_scanner);
        slidingUpPanelLayout = (SlidingUpPanelLayout) ((Activity) this.context).findViewById(R.id.sliding_layout);
        editTextName = (EditText) ((Activity) this.context).findViewById(R.id.input_name);
        editTextBrand = (EditText) ((Activity) this.context).findViewById(R.id.input_brand);
        textViewExpLabel = (TextView) ((Activity) this.context).findViewById(R.id.pick_exp_date);
        editTextNetWeight = (EditText) ((Activity) this.context).findViewById(R.id.weight_number_input);
        spinnerFoodType = (Spinner) ((Activity) this.context).findViewById(R.id.input_type);
        spinnerFoodType.setOnItemSelectedListener(this);

        List<String> foodCategories = Arrays.asList(context.getResources().getStringArray(R.array.food_categories));
        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, foodCategories);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFoodType.setAdapter(catAdapter);

        spinnerNetWeightUnit = (Spinner) ((Activity) this.context).findViewById(R.id.weight_unit_input);
        spinnerNetWeightUnit.setOnItemSelectedListener(this);
        List<String> units = Arrays.asList(context.getResources().getStringArray(R.array.us_units_ab));
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, units);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNetWeightUnit.setAdapter(unitAdapter);

    }

    @Override
    public void onClick(View v) {

        if(editTextName.getText().toString().equals("")
                || spinnerFoodType.getSelectedItem().toString().equals("")
                || editTextBrand.getText().toString().equals("")
                || editTextNetWeight.getText().toString().equals("")){

            new MaterialDialog.Builder(context)
                    .title(context.getResources().getText(R.string.mat_title))
                    .content(context.getResources().getText(R.string.mat_content))
                    .positiveText(context.getResources().getText(R.string.mat_confirm))
                    .show();

        }
        else {
            // Begin Insert
            Food newEntry = new Food(
                    UUID.randomUUID().toString(),
                    barcode,
                    editTextName.getText().toString(),
                    spinnerFoodType.getSelectedItem().toString(),
                    editTextBrand.getText().toString(),
                    editTextNetWeight.getText().toString().concat(spinnerNetWeightUnit.getSelectedItem().toString()),
                    entDate,
                    expDate,
                    imageUrl,
                    Food.SKIP_FALSE
            );



            db.addFood(newEntry);
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        }



    }

    @Override
    protected String doInBackground(String... strings) {

        Food food = db.getFood(barcode);
        if(food != null){
            if(!food.getName().equals("")) name = food.getName();
            if(!food.getBrand().equals("")) brand = food.getBrand();
            if(!food.getType().equals("")) type = food.getType();
            if(!food.getNetWeight().equals("")) netWeight = food.getNetWeight();
            if(!food.getExpirationDate().equals("")) expDate = food.getExpirationDate();
            skipFlag = food.getSkipFlag();
            return null;
        }
        else {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int responseCode = urlConnection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    server_response = readStream(urlConnection.getInputStream());
                    Log.v("Response", server_response);
                }
                if(server_response != null){
                    JSONObject jsonObject = new JSONObject(server_response);
                    JSONObject attr = null;
                    Calendar c = Calendar.getInstance();
                    String formatDate = formatDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                    entDate = formatDate;
                    //barcode = jsonObject.getString("gtin");

                    try{
                        if(jsonObject.getString("name") != null )
                            name = jsonObject.getString("name");
                    }catch (Exception e){
                        name = "";
                    }

                    try {
                        attr = jsonObject.getJSONObject("attributes");
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        if(attr.getString("Brand") != null)
                         brand = attr.getString("Brand");
                    }catch (Exception e){
                        brand = "";
                    }

                    try {
                        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("images"));
                        if(jsonArray.get(0).toString().equals("") || jsonArray.get(0) != null)
                            imageUrl = (jsonArray.get(0).toString());
                    }catch (Exception e){
                        imageUrl = "";
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e){
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("Response", "" + server_response);

        // Indicate no skip
        if(skipFlag == 0){
            Log.wtf("TAG","SKIP FLAG IS ZERO");
            new MaterialDialog.Builder(context)
                    .title(R.string.dialog_title)
                    .content(R.string.dialog_des)
                    .positiveText(R.string.dialog_pos)
                    .negativeText(R.string.dialog_neg)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            final Food newEntry = new Food(
                                    UUID.randomUUID().toString(),
                                    barcode,
                                    editTextName.getText().toString(),
                                    spinnerFoodType.getSelectedItem().toString(),
                                    editTextBrand.getText().toString(),
                                    editTextNetWeight.getText().toString()+" "+spinnerNetWeightUnit.getSelectedItem().toString(),
                                    entDate,
                                    expDate,
                                    imageUrl,
                                    Food.SKIP_TRUE
                            );

                            Log.wtf("TAG","ADD FOOD ENTRY");
                            db.addFood(newEntry);
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, name+ " was added", Snackbar.LENGTH_LONG)
                                    .setAction("UNDO", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            db.deleteFood(newEntry);
                                        }
                                    });
                            snackbar.show();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            populateEntryDrawer();
                        }
                    })
                    .show();
        }
        else populateEntryDrawer();

    }

    public String formatDate(int year, int month, int day) {
        String rawDate = month + "/" + day + "/" + year;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dt1 = format1.parse(rawDate);
            DateFormat format2 = new SimpleDateFormat("EEEE");
            String finalDay = format2.format(dt1);
            return finalDay + " " + rawDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void populateEntryDrawer(){

        editTextName.setText(name);
        editTextBrand.setText(brand);
        textViewExpLabel.setText(context.getResources().getString(R.string.choose_expiration));

        // Set Spinners
        String[] foodCategories = context.getResources().getStringArray(R.array.food_categories);
        String foodType =  type;
        for (int z = 0; z < foodCategories.length; z++) {
            if(foodCategories[z].equalsIgnoreCase(foodType)){
                spinnerFoodType.setSelection(z);
                break;
            }
        }

        if(editTextNetWeight.getText().toString().split(" ").length == 2){
            editTextNetWeight.setText(netWeight.split(" ")[0]);
            String[] temp = context.getResources().getStringArray(R.array.us_units_ab);
            String readUnit =  netWeight.split(" ")[1];
            for (int i = 0; i < temp.length; i++) {
                if(temp[i].equalsIgnoreCase(readUnit)){
                    spinnerNetWeightUnit.setSelection(i);
                    break;
                }
            }
        }
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);


    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) response.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        switch(parent.getId()) {
            case R.id.input_type:
                //food.setType(item);
                break;
            case R.id.weight_unit_input:
                //food.setNetWeight(food.getNetWeight().split(" ")[0] +" "+item);
                break;
            default:
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

}
