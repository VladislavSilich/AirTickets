package com.task.example.silich.vladislav.taskwork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.task.example.silich.vladislav.taskwork.network.responce.ModelResponce;

import java.util.ArrayList;

/**
 * Created by Lenovo on 21.06.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerviewViewHolder>  {

    Context c;
    ArrayList<ModelResponce> spacecrafts;

    public RecyclerViewAdapter(Context c, ArrayList<ModelResponce> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;

    }

    //INITIALIZE VH
    @Override
    public RecyclerviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.modelrecyclerview,parent,false);

        return new RecyclerviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerviewViewHolder holder, int position) {

        //BIND DATA
        holder.CityText.setText(spacecrafts.get(position).getCityNameFrom() + " -> " +
                spacecrafts.get(position).getCityNameTo() );
        holder.DateText.setText(spacecrafts.get(position).getDepDate() + " " + spacecrafts.get(position).getDepTime() +
                " -> " + spacecrafts.get(position).getArrDate()+ " " + spacecrafts.get(position).getArrTime());
        holder.PriceText.setText("Цена: " + spacecrafts.get(position).getPrice()+ String.valueOf(spacecrafts.get(position).getPriceCurrency()));
        holder.AirportText.setText(spacecrafts.get(position).getAirportNameFrom() + " -> " + spacecrafts.get(position).getAirportNameTo());



    }

    //TOTAL NUM
    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }


}
