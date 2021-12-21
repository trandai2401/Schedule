package com.example.webmasterdotnetvn.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webmasterdotnetvn.schedule.API.ApiService;
import com.example.webmasterdotnetvn.schedule.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    Button btnDangNhap;
    EditText edtemail, edtpass;
    public static User  staticUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtemail = findViewById(R.id.edtemail);
        edtpass = findViewById(R.id.edtpass);

        btnDangNhap = (Button) findViewById(R.id.btndangnhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ApiService.apiService.postLogin(edtemail.getText().toString(), edtpass.getText().toString()).enqueue(
                        new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.body() != null) {
//                                    Toast.makeText(login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    User user = response.body();
                                    startActivity(new Intent(login.this, menu.class));
                                    SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("idUser", user.getId());
                                    staticUser = user;
                                    editor.apply();
                                } else {
                                    Toast.makeText(login.this, "Đăng nhập thất bại! Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(login.this, "Đăng nhập thất bại! Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();

                            }
                        }
                );


            }
        });

    }
}