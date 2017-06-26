package com.task.example.silich.vladislav.taskwork.managers;

import com.task.example.silich.vladislav.taskwork.network.responce.ModelResponce;
import com.task.example.silich.vladislav.taskwork.network.RestService;
import com.task.example.silich.vladislav.taskwork.network.ServiceGenerator;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Lenovo on 14.06.2017.
 */

public class DataManager {
    //единая точка для запросов
    //при его помощи происходит Get-запрос
    private static DataManager INSTANCE = null;
    private RestService service;

    public DataManager(){
        this.service = ServiceGenerator.createService(RestService.class);
    }

    public static DataManager getInstnce (){
        if (INSTANCE == null){
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }


    public Call<List<ModelResponce>> getAir (String polet, String key, String auth){
        return service.upload(polet,key,auth);
    }

}
