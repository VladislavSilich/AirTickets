package com.task.example.silich.vladislav.taskwork.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.task.example.silich.vladislav.taskwork.Comparator.TicketDateComparator;
import com.task.example.silich.vladislav.taskwork.Comparator.TicketDurabilityCompartor;
import com.task.example.silich.vladislav.taskwork.Comparator.TicketPriceAndDurabilityComparat;
import com.task.example.silich.vladislav.taskwork.Comparator.TicketPriceComparator;
import com.task.example.silich.vladislav.taskwork.Paginator;
import com.task.example.silich.vladislav.taskwork.R;
import com.task.example.silich.vladislav.taskwork.RecyclerViewAdapter;
import com.task.example.silich.vladislav.taskwork.Utills.NetworkStatusChecker;
import com.task.example.silich.vladislav.taskwork.managers.DataManager;
import com.task.example.silich.vladislav.taskwork.network.responce.ModelResponce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity implements View.OnClickListener {
    int respCounter;
    DataManager dataManager;
    long dateDiff;
    EditText edtDateTo,edtDateFrom;
    EditText edtCityNameFrom,edtCityNameTo;
    ImageView imageCalendarFirst;
    ImageView imageCalendarSecond;
    Button btnSearch,btnNext,btnPrevious;
    final String key = "73d2de171981dfff528df75a1442352d";
    final String auth = "Basic dXNlcjEyOkpkc2tpeUdrbDJITg==";
    List<ModelResponce> listResponce;
    List<ModelResponce> allResponce;
    List<String> cityNameTo;
    private int DIALOG_DATE_FROM = 1;
    private int DIALOG_DATE_TO = 2;
    Map<String,String> mapCities;
    Calendar calendar = Calendar.getInstance();
    int mYear = calendar.get(Calendar.YEAR);
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    int mMonth = calendar.get(Calendar.MONTH) ;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    int totalPages;
    int currentPage = 0;
    ProgressBar indicator;
    Paginator paginator;
    Spinner spinner;
    String [] dataSpinner = {"по дате","по цене","по длительности полёта","по рейтингу: Лучший билет"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtDateFrom = (EditText)findViewById(R.id.dateFrom);
        edtDateTo = (EditText)findViewById(R.id.dateTo);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnNext = (Button)findViewById(R.id.btnNext);
        btnPrevious = (Button)findViewById(R.id.btnPrevious);
        edtCityNameFrom = (EditText)findViewById(R.id.cityNameFrom);
        edtCityNameTo = (EditText)findViewById(R.id.cityNameTo);
        imageCalendarFirst = (ImageView)findViewById(R.id.imageFirstCalendar);
        imageCalendarSecond = (ImageView)findViewById(R.id.imageSecondCalendar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        indicator = (ProgressBar)findViewById(R.id.progressBar);
        btnPrevious.setEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataSpinner);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapterSpinner);
        spinner.setPrompt("Сортировать по: ");
        //обработка нажатия на диалог, проверка данных для сортировки
        //если данных для сортировки нет, то выводит сообщение пользователя
        spinner.post(new Runnable() {
            @Override
            public void run() {
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    if (paginator != null) {
                                        Collections.sort(listResponce, new TicketDateComparator());
                                        recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this, paginator.generatePage(currentPage)));
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this,"Нет данных для сортировки",Toast.LENGTH_LONG).show();
                                    }
                                    break;
                                case 1:
                                    if(paginator != null) {
                                        Collections.sort(listResponce, new TicketPriceComparator());
                                        recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this, paginator.generatePage(currentPage)));
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this,"Нет данных для сортировки",Toast.LENGTH_LONG).show();
                                    }
                                    break;
                                case 2:
                                    if(paginator != null) {
                                        Collections.sort(listResponce, new TicketDurabilityCompartor());
                                        recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this, paginator.generatePage(currentPage)));
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this,"Нет данных для сортировки",Toast.LENGTH_LONG).show();
                                    }
                                    break;
                                case 3:
                                    if(paginator != null){
                                        showDialog();
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this,"Нет данных для сортировки",Toast.LENGTH_LONG).show();
                                    }
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
        });



        //обработка нажатия на кнопки для перелистывания страниц
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage+=1;
                recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this,paginator.generatePage(currentPage)));
                toggleButton();
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage-=1;
                recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this,paginator.generatePage(currentPage)));
                toggleButton();
            }
        });
        dataManager = DataManager.getInstnce();
        cityNameTo = new ArrayList<>();
        listResponce = new ArrayList<>();
        allResponce = new ArrayList<>();
        mapCities = new HashMap<>();
        mapCities.put("МОСКВАСИМФЕРОПОЛЬ","MOWSIP");
        mapCities.put("СИМФЕРОПОЛЬМОСКВА","SIPMOW");
        imageCalendarSecond.setOnClickListener(this);
        imageCalendarFirst.setOnClickListener(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (NetworkStatusChecker.isNetworkAvailable(MainActivity.this) == true) {
                        listResponce.clear();
                        pathDefinition();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Пожалуйста, проверьте подключение к интернету",Toast.LENGTH_LONG).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    //обработка активноти кнопок кнопок для перелистывания страниц
    //если страница первая,то кнопка "предыдущая" не кликабельна
    private void toggleButton() {
        if (currentPage == totalPages) {
            btnNext.setEnabled(false);
            btnPrevious.setEnabled(true);
        } else if (currentPage == 0) {
            btnPrevious.setEnabled(false);
            btnNext.setEnabled(true);
        } else if (currentPage >= 1 && currentPage <= totalPages) {
            btnNext.setEnabled(true);
            btnPrevious.setEnabled(true);
        }
    }
    //метода преобразовывает данные о городах в формат для запроса
    //и вызывает метод getDate
    private void pathDefinition() throws ParseException {
        String cities = (edtCityNameFrom.getText().toString() + edtCityNameTo.getText().toString()).toUpperCase().trim();
        String path = mapCities.get(cities);
        if(path != null) {
            getDate(path);
        }
        else {
            Toast.makeText(this,"Нет данных о полёте",Toast.LENGTH_LONG).show();
        }
    }
    //принимает данные о городах, преобразовывает дату для запроса
    //вызывает метод,который получает responce
    private void getDate(String path) throws ParseException {
        String dateTo = edtDateTo.getText().toString();
        String dateFrom = edtDateFrom.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateFormatNetwork = new SimpleDateFormat("yyyyMMdd");
        Calendar calendarFrom = Calendar.getInstance();
        Calendar calendarTo = Calendar.getInstance();
        calendarFrom.setTime(dateFormat.parse(dateFrom));
        calendarTo.setTime(dateFormat.parse(dateTo));
        listResponce.clear();
         respCounter = 0;
        Date secondDate = calendarTo.getTime();
        Date firstDate = calendarFrom.getTime();
        dateDiff = ((secondDate.getTime() - firstDate.getTime()) / 1000 / 60 / 60 /24);
       while (calendarFrom.compareTo(calendarTo) != 0) {
            getTickets(path,dateFormatNetwork.format(calendarFrom.getTime()));
          calendarFrom.add(Calendar.DATE, 1);
      }
    }
    private void getTickets(String path,String date) {
        indicator.setVisibility(View.VISIBLE);
            String flight1 = "";
            flight1 = path + date;
            Call<List<ModelResponce>> call = dataManager.getAir(flight1, key, auth);
            call.enqueue(new Callback<List<ModelResponce>>() {
                @Override
                public void onResponse(Call<List<ModelResponce>> call, Response<List<ModelResponce>> response) {
                    if (response.code() == 200) {
                        List<ModelResponce> list = response.body();
                        listResponce.addAll(list);
                        respCounter++;
                        checkLastResponce();
                        Toast.makeText(MainActivity.this,"Найдено " + String.valueOf(listResponce.size()) + " билетов", Toast.LENGTH_LONG).show();
                    }
                    else if (response.code() == 400){
                        Toast.makeText(MainActivity.this,"Вы ввели некорректную дату",Toast.LENGTH_LONG).show();
                    }
            }
                @Override
                public void onFailure(Call<List<ModelResponce>> call, Throwable t) {
                }
            });
    }
    //сравнивает количество дат с количеством запросов
    //размещает данные в листе
    private void checkLastResponce() {
        if (respCounter == dateDiff){
             paginator = new Paginator(listResponce);
              totalPages = Paginator.TOTAL_NUM_ITEMS / Paginator.ITEMS_PER_PAGE;
            listResponce.size();
            Collections.sort(listResponce,new TicketDateComparator());
            recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this,paginator.generatePage(currentPage)));
            indicator.setVisibility(View.INVISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnPrevious.setVisibility(View.VISIBLE);
        }
    }
    //обработка нажатия на диалог с датами
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageFirstCalendar :
                showDialog(DIALOG_DATE_FROM);
                break;
            case R.id.imageSecondCalendar:
                showDialog(DIALOG_DATE_TO);
                break;
        }
    }
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE_FROM) {
            DatePickerDialog dialogDateFrom = new DatePickerDialog(this, myCallBackFrom, mYear, mMonth, mDay);
            return dialogDateFrom;
        }
        else if (id == DIALOG_DATE_TO){
            DatePickerDialog dialogDateTo = new DatePickerDialog(this, myCallBackTo, mYear, mMonth, mDay);
            return dialogDateTo;
        }
        return super.onCreateDialog(id);
    }
    DatePickerDialog.OnDateSetListener myCallBackFrom = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear + 1;
            mDay = dayOfMonth;
            edtDateFrom.setText( mDay + "." + mMonth + "." + mYear);
        }
    };
    DatePickerDialog.OnDateSetListener myCallBackTo = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear + 1;
            mDay = dayOfMonth;
            edtDateTo.setText( mDay + "." + mMonth + "." + mYear);
        }
    };
    //диалог для выбора лучшего билета
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Важное сообщение!")
                .setMessage("Билеты сортируются по двум криитериям:цена,длительность полёта.\n"+"Отсартированный список вы увидите на экране.")
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Collections.sort(listResponce, new TicketPriceAndDurabilityComparat());
                                recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this, paginator.generatePage(currentPage)));
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
