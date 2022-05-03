package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import Beans.Photos;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Photos> data;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler=findViewById(R.id.recyclerData);
        recycler.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));
        getPhotos();
    }

    private void getPhotos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceHolderApi placeholder=retrofit.create(PlaceHolderApi.class);

        Call<List<Photos>> inter=placeholder.getPhotos();

        inter.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {

                List<Photos> lista=response.body();
                data=new ArrayList<Photos>();

                for(Photos po:lista){
                    data.add(new Photos(po.getAlbumId(),
                            po.getId(),po.getTitle(),po.getUrl(),
                            po.getThumbnailUrl()));
                }
                ListAdapter adapter=new ListAdapter(data);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });

    }

}