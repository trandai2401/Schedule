package com.example.webmasterdotnetvn.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webmasterdotnetvn.schedule.API.ApiService;
import com.example.webmasterdotnetvn.schedule.model.CongViec;
import com.example.webmasterdotnetvn.schedule.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalManagement extends AppCompatActivity {
    TextView tenNguoiDungTextView;
    EditText edit_name,edit_gmail,edit_matkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_management);

        tenNguoiDungTextView = findViewById(R.id.tenNguoiDungTextView);
        edit_name = findViewById(R.id.edit_name);
        edit_gmail= findViewById(R.id.edit_gmail);
        edit_matkhau=findViewById(R.id.edit_matkhau);

        tenNguoiDungTextView.setText(login.staticUser.getName());
        edit_name.setText(login.staticUser.getName());
        edit_gmail.setText(login.staticUser.getEmail());
        edit_matkhau.setText(login.staticUser.getPassword());


    }


    public void callApi(View view) {
        login.staticUser.setEmail(edit_gmail.getText().toString());
        login.staticUser.setName(edit_name.getText().toString());

        ApiService.apiService.setUser(login.staticUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body() != null){
                    login.staticUser = response.body();
                    tenNguoiDungTextView.setText(login.staticUser.getName());
                    edit_name.setText(login.staticUser.getName());
                    edit_gmail.setText(login.staticUser.getEmail());
                    Toast.makeText(PersonalManagement.this, "Đã đổi thông tin cá nhân thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PersonalManagement.this, "Call được nhưng méo đổi được rồi bạn ơi", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(PersonalManagement.this, "Call méo thành luôn", Toast.LENGTH_SHORT).show();

            }
        });
    }
}