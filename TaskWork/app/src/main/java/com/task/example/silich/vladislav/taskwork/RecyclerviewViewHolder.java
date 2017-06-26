package com.task.example.silich.vladislav.taskwork;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Lenovo on 21.06.2017.
 */

public class RecyclerviewViewHolder extends RecyclerView.ViewHolder{

    TextView CityText,AirportText;
    TextView DateText,PriceText;

    public RecyclerviewViewHolder(View itemView) {
        super(itemView);
        CityText = (TextView)itemView.findViewById(R.id.CityText);
        DateText = (TextView)itemView.findViewById(R.id.dateText);
        PriceText = (TextView)itemView.findViewById(R.id.priceText);
        AirportText = (TextView)itemView.findViewById(R.id.airportText);
    }

}
