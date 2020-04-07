package com.example.final_project.ui.main;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.final_project.R;

import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment {
    public static String monthStr;
    public static String dayStr;
    public static String textDate;
    public static final String mypreference = "mypref";
    public static final String dated = "date";
    SharedPreferences prefs;
    public static EditText date;

    /**
     * Fragment to search the image of the day
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.search_layout, container, false);
        date = (EditText) root.findViewById(R.id.date);
        prefs = this.getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (prefs.contains(dated)) {
            date.setText(prefs.getString(dated, ""));
            textDate = date.getText().toString();
        }

        date = (EditText) root.findViewById(R.id.date);
        /**
         * EditText = date
         * action listner onclick to select date which calls the DatePicker method
         */
        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePicker();
                // Code here executes on main thread after user presses button
            }
        });
        final Button search = root.findViewById(R.id.Search);
        /**
         * click listner on search button
         */
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent imageDetail = new Intent(getActivity(), ImageDetails.class);
                imageDetail.putExtra("Date", textDate);
                startActivity(imageDetail);
                // Code here executes on main thread after user presses button
            }
        });
        return root;
    }

    /**
     * to get the date picker
     */
    private void DatePicker() {
        Calendar cal = Calendar.getInstance();
        int day, month, year;
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
/**
 *                 Formatting the selected date to pass into the url.
 */

                month = month + 1;
                if (month < 10) {
                    monthStr = "0" + month;
                } else {
                    monthStr = "" + month;
                }

                if (dayOfMonth < 10) {
                    dayStr = "0" + dayOfMonth;
                } else {
                    dayStr = "" + dayOfMonth;
                }
                textDate = year + "-" + monthStr + "-" + dayStr;
                date.setText(textDate);

            }
        }, year, month, day);
        datePickerDialog.show();


    }

    /**
     * to store the previous date data in the sharedPreffrence
     */
    @Override
    public void onPause() {
        String d = date.getText().toString();
        SharedPreferences preferences = this.getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(dated, d);
        editor.commit();

        super.onPause();

    }


}



