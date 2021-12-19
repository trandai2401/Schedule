package com.example.webmasterdotnetvn.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class menu extends AppCompatActivity {
    Button btnlogout;
    Button btnthem;
    Button btntimkiem;
    Button btncapnhat;
    Button btnxemtkb;
    TextView tenUserTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tenUserTextView = findViewById(R.id.tenUserTextView);
        tenUserTextView.setText("Xin chào : "+login.staticUser.getName());


        btnthem = (Button) findViewById(R.id.btnthemsk);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, add.class));
            }
        });

        btnlogout=(Button) findViewById(R.id.btndangxuat);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                Intent in = new Intent(menu.this, login.class);
                startActivity(in);
                finish();
            }
        });

        btncapnhat = (Button) findViewById(R.id.btnquanlicanhan);
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, PersonalManagement.class));
            }
        });

        btnxemtkb =(Button) findViewById(R.id.btn_xemtkb);
        btnxemtkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, xemtkb.class));
            }
        });

        btntimkiem = (Button) findViewById(R.id.btn_timkiem);
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, search.class));
            }
        });
    }
}