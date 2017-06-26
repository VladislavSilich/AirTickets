package com.task.example.silich.vladislav.taskwork.network;

import com.task.example.silich.vladislav.taskwork.network.responce.ModelResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 14.06.2017.
 */

public interface RestService{
    //интерфейс для Get-запроса

@GET("tickets/{polet}")
    Call<List<ModelResponce>> upload (@Path("polet") String polet, @Query("apikey")String key, @Header("Authorization") String auth);
}
