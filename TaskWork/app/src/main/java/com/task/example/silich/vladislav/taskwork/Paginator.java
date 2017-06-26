package com.task.example.silich.vladislav.taskwork;

import com.task.example.silich.vladislav.taskwork.network.responce.ModelResponce;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 21.06.2017.
 */

public class Paginator
{
    // для работы с перелистыванием страниц
    // указываем количество строк на одной странице
    public static  int TOTAL_NUM_ITEMS= 0;
    public static  int ITEMS_PER_PAGE=7;
    public static  int ITEMS_REMAINING=TOTAL_NUM_ITEMS % ITEMS_PER_PAGE;
    public static  int LAST_PAGE=TOTAL_NUM_ITEMS/ITEMS_PER_PAGE;
    public List<ModelResponce> list  = new ArrayList<>();

    public Paginator(List<ModelResponce> list){
        this.list = list;
        TOTAL_NUM_ITEMS = list.size();
    }

    public ArrayList<ModelResponce> generatePage(int currentPage)
    {
        int startItem=currentPage*ITEMS_PER_PAGE+1;
        int numOfData=ITEMS_PER_PAGE;

        ArrayList<ModelResponce> pageData=new ArrayList<>();

        if (currentPage==LAST_PAGE && ITEMS_REMAINING>0)
        {
            for (int i=startItem;i<startItem+ITEMS_REMAINING;i++)
            {
                pageData.add(list.get(i));
            }
        }else
        {
            for (int i=startItem;i<startItem+numOfData ;i++)
            {
                pageData.add(list.get(i));
            }
        }


        return pageData;
    }

}
