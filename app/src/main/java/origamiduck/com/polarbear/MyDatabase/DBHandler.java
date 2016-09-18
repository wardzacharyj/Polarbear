package origamiduck.com.polarbear.MyDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    private static DBHandler mInstance = null;


    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FRIDGE";
    private static final String TABLE_NAME = "FOOD";

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_BARCODE = "BARCODE";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_FOOD_TYPE = "FOOD_TYPE";
    private static final String COLUMN_BRAND ="BRAND";
    private static final String COLUMN_NET_WEIGHT = "NET_WEIGHT";
    private static final String COLUMN_ENTRY_DATE = "ENTRY_DATE";
    private static final String COLUMN_EXPIRATION_DATE = "EXPIRATION_DATE";
    private static final String COLUMN_IMAGE_URL = "IMAGE_URL";
    private static final String COLUMN_SKIP_FLAG = "SKIP_FLAG";

    private Context context;

    public static DBHandler getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (mInstance == null) {
            mInstance = new DBHandler(ctx.getApplicationContext());
        }
        return mInstance;
    }


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " TEXT PRIMARY KEY,"
                    + COLUMN_BARCODE + " TEXT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_FOOD_TYPE + " TEXT,"
                    + COLUMN_BRAND + " TEXT,"
                    + COLUMN_NET_WEIGHT + " TEXT,"
                    + COLUMN_ENTRY_DATE + " TEXT,"
                    + COLUMN_EXPIRATION_DATE + " TEXT,"
                    + COLUMN_IMAGE_URL + " TEXT,"
                    + COLUMN_SKIP_FLAG + " INTEGER)";

        Log.wtf("DB", CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,food.getID());
        values.put(COLUMN_BARCODE, food.getBarcode());
        values.put(COLUMN_NAME, food.getName());
        values.put(COLUMN_FOOD_TYPE, food.getType());
        values.put(COLUMN_BRAND, food.getBrand());
        values.put(COLUMN_NET_WEIGHT, food.getNetWeight());
        values.put(COLUMN_ENTRY_DATE,food.getEntryDate());
        values.put(COLUMN_EXPIRATION_DATE, food.getExpirationDate());
        values.put(COLUMN_IMAGE_URL, food.getImageURL());
        values.put(COLUMN_SKIP_FLAG,food.getSkipFlag());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateSkipFlags(String barcode, int skipFlagValue){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SKIP_FLAG, skipFlagValue);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, cv, COLUMN_BARCODE + " = ?", new String[]{barcode});
        db.close();
    }

    public Food getFood(String barcode) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_BARCODE,
                        COLUMN_NAME,
                        COLUMN_FOOD_TYPE,
                        COLUMN_BRAND,
                        COLUMN_NET_WEIGHT,
                        COLUMN_ENTRY_DATE,
                        COLUMN_EXPIRATION_DATE,
                        COLUMN_IMAGE_URL,
                        COLUMN_SKIP_FLAG}, COLUMN_BARCODE + "=?",
                new String[]{barcode}, null, null, null, null);

        Food food = null;
        if(cursor.moveToFirst() && cursor.getCount() >= 1) {
            do {
                 food = new Food(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getInt(9));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return food;

    }

    public List<Food> getAllFoods() {
        List<Food> foodList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do foodList.add(new Food(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                    cursor.getString(7), cursor.getString(8), cursor.getInt(9)));
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodList;
    }

    public List<Food> getAllUniqueFoods() {

        List<Food> foodList = new ArrayList<>();
        String selectQuery = "SELECT DISTINCT BARCODE, NAME, BRAND, NET_WEIGHT, EXPIRATION_DATE FROM "
                + TABLE_NAME +" ORDER BY EXPIRATION_DATE DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setBarcode(cursor.getString(0));
                food.setName(cursor.getString(1));
                food.setBrand(cursor.getString(2));
                food.setNetWeight(cursor.getString(3));
                food.setExpirationDate(cursor.getString(4));
                foodList.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodList;
    }

    public int getFoodCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,food.getID());
        values.put(COLUMN_BARCODE, food.getBarcode());
        values.put(COLUMN_NAME, food.getName());
        values.put(COLUMN_FOOD_TYPE, food.getType());
        values.put(COLUMN_BRAND, food.getBrand());
        values.put(COLUMN_NET_WEIGHT, food.getNetWeight());
        values.put(COLUMN_ENTRY_DATE, food.getEntryDate());
        values.put(COLUMN_EXPIRATION_DATE, food.getExpirationDate());
        values.put(COLUMN_IMAGE_URL, food.getImageURL());
        values.put(COLUMN_SKIP_FLAG, food.getSkipFlag());
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[] { food.getID() });
    }

    public void deleteFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { food.getID() });
        db.close();
    }


}
