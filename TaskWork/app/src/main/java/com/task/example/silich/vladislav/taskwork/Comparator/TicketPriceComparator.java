package com.task.example.silich.vladislav.taskwork.Comparator;

import com.task.example.silich.vladislav.taskwork.network.responce.ModelResponce;

import java.util.Comparator;

/**
 * Created by Lenovo on 26.06.2017.
 */

public class TicketPriceComparator implements Comparator<ModelResponce> {
    //сортирвка данных по полю Price
    @Override
    public int compare(ModelResponce o1, ModelResponce o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }
}
