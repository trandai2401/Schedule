package com.example.webmasterdotnetvn.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webmasterdotnetvn.schedule.API.ApiService;
import com.example.webmasterdotnetvn.schedule.model.CongViec;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XemChiTietCongViecActivity extends AppCompatActivity {
    ArrayList<CongViec> congViecs;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_chi_tiet_cong_viec);
        listView = findViewById(R.id.listViewXemCongViec);
        Intent intent = getIntent();
        String idThu = intent.getStringExtra("idThu");


        TextView thuTextView = findViewById(R.id.thuTextView);
        thuTextView.setText("Thứ "+(Integer.parseInt(idThu)+1));

        ApiService.apiService.getAllCongViecByIdUser(login.staticUser.getId(), idThu).enqueue(
                new Callback<ArrayList<CongViec>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CongViec>> call, Response<ArrayList<CongViec>> response) {
                        if (response.body() != null) {
                            congViecs = response.body();
                            listView.setAdapter(new CongViecAdapter());
                            Toast.makeText(XemChiTietCongViecActivity.this, "Cos du lieu", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(XemChiTietCongViecActivity.this, "khong co du lieu", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<CongViec>> call, Throwable t) {
                        Toast.makeText(XemChiTietCongViecActivity.this, "Call that bai", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }


    class CongViecAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return congViecs.size();
        }

        @Override
        public Object getItem(int position) {
            return congViecs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.xemtkb, null);
            TextView monHoc = view.findViewById(R.id.monHoc);
            TextView gioBatDau = view.findViewById(R.id.gioBatDau);
            TextView GioketThuc = view.findViewById(R.id.GioketThuc);
            ImageView imageView2 = view.findViewById(R.id.imageView2);
            TextView thu = view.findViewById(R.id.textView2);


            GioketThuc.setText(congViecs.get(position).getGioKetThuc().toString());
            gioBatDau.setText(congViecs.get(position).getGioBatDau().toString());

            monHoc.setText(congViecs.get(position).getNoidung());
            thu.setText("Thứ "+ (Integer.valueOf(congViecs.get(position).getId_thu())+1));
            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ApiService.apiService.postDeleteCongViec(congViecs.get(position).getId()).enqueue(
                            new Callback<Integer>() {
                                @Override
                                public void onResponse(Call<Integer> call, Response<Integer> response) {
                                    if (response.body() != null) {
                                        Integer b = response.body();
                                        congViecs.remove(position);
                                        notifyDataSetChanged();
                                        Toast.makeText(XemChiTietCongViecActivity.this, "Đã xóa công việc trong CSDL", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(XemChiTietCongViecActivity.this, "Có trả về nhưng khum có gì", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onFailure(Call<Integer> call, Throwable t) {
                                    Toast.makeText(XemChiTietCongViecActivity.this, "Thất bại rồi bạn ơi", Toast.LENGTH_SHORT).show();

                                }
                            }
                    );
                }
            });
            ImageView imageButton = view.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(XemChiTietCongViecActivity.this, edit.class);

                    intent.putExtra("id", congViecs.get(position).getId());
                    intent.putExtra("id_thu", congViecs.get(position).getId_thu());
                    intent.putExtra("id_user", congViecs.get(position).getId_user());
                    intent.putExtra("noiDung", congViecs.get(position).getNoidung());
                    intent.putExtra("startTime", congViecs.get(position).getGioBatDau());
                    intent.putExtra("endTime", congViecs.get(position).getGioKetThuc());
                    startActivity(intent);
                }
            });

            return view;
        }
    }
}