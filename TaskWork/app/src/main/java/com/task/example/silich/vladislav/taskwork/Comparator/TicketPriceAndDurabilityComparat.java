package com.task.example.silich.vladislav.taskwork.Comparator;

import com.task.example.silich.vladislav.taskwork.network.responce.ModelResponce;

import java.util.Comparator;

/**
 * Created by Lenovo on 26.06.2017.
 */

public class TicketPriceAndDurabilityComparat implements Comparator<ModelResponce> {
    //сортировка данных по полям price и duration(для лучшего билета)
    @Override
    public int compare(ModelResponce o1, ModelResponce o2) {
        int result = o1.getPrice().compareTo(o2.getPrice());
        if (result != 0) {
            return  (result / Math.abs(result));
        }
        result = o1.getDuration().compareTo(o2.getDuration());
        if (result != 0) return  (result / Math.abs(result));
        return result;
    }
}
