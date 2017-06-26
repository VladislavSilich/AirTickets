package com.task.example.silich.vladislav.taskwork.Comparator;

import com.task.example.silich.vladislav.taskwork.network.responce.ModelResponce;

import java.util.Comparator;

/**
 * Created by Lenovo on 26.06.2017.
 */

public class    TicketDurabilityCompartor implements Comparator<ModelResponce> {
    //сортировка данных по полю Duration
    @Override
    public int compare(ModelResponce o1, ModelResponce o2) {
        return o1.getDuration().compareTo(o2.getDuration());
    }
}
