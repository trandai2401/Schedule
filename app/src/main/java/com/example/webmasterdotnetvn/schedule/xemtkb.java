package com.example.webmasterdotnetvn.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class xemtkb extends AppCompatActivity {
    Button btn_sua;

    private categoryAdapter categoryAdapter;
    ArrayList<category> dataList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemtkb);

//        btn_sua = (Button) findViewById(R.id.btn_edit);
//        btn_sua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(xemtkb.this, edit.class));
//            }
//        });
        dataList = getListCategory();





        listView = findViewById(R.id.list_view);
        ThuAdapter thuAdapter = new ThuAdapter();
        listView.setAdapter(thuAdapter);


    }

    private ArrayList<category> getListCategory(){
        ArrayList<category> list = new ArrayList<>();
        list.add(new category("Thứ 2","1"));
        list.add(new category("Thứ 3","2"));
        list.add(new category("Thứ 4","3"));
        list.add(new category("Thứ 5","4"));
        list.add(new category("Thứ 6","5"));
        list.add(new category("Thứ 7","6"));
        list.add(new category("Chủ nhật","7"));
        return list;
    }

    class ThuAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.item_category, null);
            TextView tv_category = view.findViewById(R.id.tv_category);
            tv_category.setText(dataList.get(position).getName());
            return view;
        }
    }

}
