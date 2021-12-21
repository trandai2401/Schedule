package com.example.webmasterdotnetvn.schedule;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.webmasterdotnetvn.schedule.API.ApiService;
import com.example.webmasterdotnetvn.schedule.model.CongViec;
import com.google.gson.Gson;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit extends AppCompatActivity {
    private Spinner spnCategory;
    private categoryAdapter categoryAdapter;
    Button btntimepicker;
    Button btntimepicker_1;
    EditText edtcongviec;

    Time startTime, endTime;

    String gioBatDau, gioKetThuc;
    String thu;
    CongViec congViec = new CongViec();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        startTime = Time.valueOf(LocalTime.now().format(dtf));
        btntimepicker = findViewById(R.id.btntimebd);
        edtcongviec = findViewById(R.id.edtcongviec);
        btntimepicker_1 = findViewById(R.id.btntimekt);

        Intent intent = getIntent();


        congViec.setId(intent.getStringExtra("id"));
        congViec.setId_user(intent.getStringExtra("id_user"));
        congViec.setId_thu(intent.getStringExtra("id_thu"));
        congViec.setNoidung(intent.getStringExtra("noiDung"));
        congViec.setGioBatDau(intent.getStringExtra("startTime"));
        congViec.setGioKetThuc(intent.getStringExtra("endTime"));


        edtcongviec.setText(congViec.getNoidung());
        btntimepicker.setText(congViec.getGioBatDau());
        btntimepicker_1.setText(congViec.getGioKetThuc());

        startTime = Time.valueOf(congViec.getGioBatDau());
        endTime = Time.valueOf(congViec.getGioKetThuc());


        thu = congViec.getId_thu();
        spnCategory = findViewById(R.id.spn_category);
        categoryAdapter = new categoryAdapter(this, R.layout.item_selected, getListCategory());
        spnCategory.setAdapter(categoryAdapter);
        spnCategory.setSelection(Integer.parseInt(congViec.getId_thu())-1);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(edit.this, categoryAdapter.getItem(i).getName(), Toast.LENGTH_SHORT).show();

                thu = categoryAdapter.getItem(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private List<category> getListCategory() {
        List<category> list = new ArrayList<>();
        list.add(new category("Thứ 2", "1"));
        list.add(new category("Thứ 3", "2"));
        list.add(new category("Thứ 4", "3"));
        list.add(new category("Thứ 5", "4"));
        list.add(new category("Thứ 6", "5"));
        list.add(new category("Thứ 7", "6"));
        list.add(new category("Chủ nhật", "7"));
        return list;
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                startTime = new Time(selectedHour, selectedMinute, 0);
                endTime = new Time(selectedHour + 1, selectedMinute, 0);
                btntimepicker.setText(String.format(Locale.getDefault(), "%02d:%02d:00 ", selectedHour, selectedMinute));
                btntimepicker_1.setText(String.format(Locale.getDefault(), "%02d:%02d:00 ", selectedHour + 1, selectedMinute));
                gioBatDau = String.format(Locale.getDefault(), "%02d:%02d:00 ", selectedHour, selectedMinute);
                gioKetThuc = String.format(Locale.getDefault(), "%02d:%02d:00 ", selectedHour + 1, selectedMinute);

            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, startTime.getHours(), startTime.getMinutes(), true);

        timePickerDialog.setTitle("");
        timePickerDialog.show();
    }

    public void popTimePicker_1(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                endTime = new Time(selectedHour, selectedMinute, 0);
                btntimepicker_1.setText(String.format(Locale.getDefault(), "%02d:%02d:00 ", selectedHour, selectedMinute));
                gioKetThuc = String.format(Locale.getDefault(), "%02d:%02d:00 ", selectedHour, selectedMinute);
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, endTime.getHours(), endTime.getMinutes(), true);

        timePickerDialog.setTitle("");
        timePickerDialog.show();
    }

    public void addCongViec(View view) {

        congViec.setId_user(login.staticUser.getId());
        congViec.setId_thu(thu);
        congViec.setNoidung(edtcongviec.getText().toString());

        congViec.setGioBatDau(startTime.toString());
        congViec.setGioKetThuc(endTime.toString());

        ApiService.apiService.setCongViec(congViec).enqueue(
                new Callback<CongViec>() {
                    @Override
                    public void onResponse(Call<CongViec> call, Response<CongViec> response) {
                        if (response.body() != null) {
                            CongViec congViec1 = response.body();

                            Intent intent = new Intent(edit.this, XemChiTietCongViecActivity.class);
                            intent.putExtra("idThu", thu);
                            startActivity(intent);

                            Toast.makeText(edit.this, "Cos duwx liệu", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(edit.this, "0 du lieu", Toast.LENGTH_SHORT).show();


                        }
                    }

                    @Override
                    public void onFailure(Call<CongViec> call, Throwable t) {

                        Toast.makeText(edit.this, "That bai", Toast.LENGTH_SHORT).show();

                    }
                }
        );

        Toast.makeText(edit.this, "Theem ", Toast.LENGTH_SHORT).show();
    }
}