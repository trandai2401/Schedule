package com.example.webmasterdotnetvn.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class add extends AppCompatActivity {
    private Spinner spnCategory;
    private categoryAdapter categoryAdapter;
    Button btntimepicker;
    Button btntimepicker_1;
    int hour, minute;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btntimepicker = findViewById(R.id.btntimebd);

        btntimepicker_1 = findViewById(R.id.btntimekt);
        spnCategory = findViewById(R.id.spn_category);
        categoryAdapter = new categoryAdapter(this, R.layout.item_selected, getListCategory());
        spnCategory.setAdapter(categoryAdapter);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(add.this, categoryAdapter.getItem(i).getName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private List<category> getListCategory(){
        List<category> list = new ArrayList<>();
        list.add(new category("Thứ 2","1"));
        list.add(new category("Thứ 3","2"));
        list.add(new category("Thứ 4","3"));
        list.add(new category("Thứ 5","4"));
        list.add(new category("Thứ 6","5"));
        list.add(new category("Thứ 7","6"));
        list.add(new category("Chủ nhật","7"));
        return list;
    }



    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                btntimepicker.setText(String.format(Locale.getDefault(), "%02d:%02d ", hour, minute));
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute,true);

        timePickerDialog.setTitle("");
        timePickerDialog.show();
    }

    public void popTimePicker_1(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                btntimepicker_1.setText(String.format(Locale.getDefault(), "%02d:%02d ", hour, minute));
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute,true);

        timePickerDialog.setTitle("");
        timePickerDialog.show();
    }
}