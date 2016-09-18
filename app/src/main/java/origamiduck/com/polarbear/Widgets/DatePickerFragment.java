package origamiduck.com.polarbear.Widgets;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import origamiduck.com.polarbear.R;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView expDate;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        expDate = (TextView) getActivity().findViewById(R.id.pick_exp_date);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    public void onDateSet(DatePicker view, int year, int month, int day) {
        String rawDate = month + "/" + day + "/" + year;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dt1 = format1.parse(rawDate);
            DateFormat format2 = new SimpleDateFormat("EEEE");
            String finalDay = format2.format(dt1);
            expDate.setText(finalDay + " " + rawDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
