package com.lanny.retrofitapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lanny.retrofitapplication.model.Photo;
import com.lanny.retrofitapplication.network.GetDataService;
import com.lanny.retrofitapplication.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button button;
    RecyclerView recyclerView;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        button = findViewById(R.id.button2);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Photo>> call = getDataService.getAllPhotos();
                call.enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                        Log.i("xxx", response.toString());
                        callPhotoAdapter(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Photo>> call, Throwable t) {
                        Log.i("xxx", t.getMessage());
                    }
                });
            }
        });
    }

    private void callPhotoAdapter(List<Photo> body) {
        myAdapter = new MyAdapter(body);
        recyclerView.setAdapter(myAdapter);
    }


}
