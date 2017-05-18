package com.example.duska.axelerom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultsActivity extends AppCompatActivity {

    private ListView lvResults;
    private DBHelper dbHelper;
    private ArrayList<String> arrayNames;
    private ArrayList<Double> arrayTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        lvResults = (ListView) findViewById(R.id.lvResults);
        int arraySize=0;
        arrayNames = new ArrayList<String>();
        arrayTime = new ArrayList<Double>();
        dbHelper = new DBHelper(this);
        //открываем бд на чтение
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        //делаем запрос к бд
        Cursor cursor = database.query(DBHelper.TABLE_RESULTS, null, null, null, null, null, null);

        try{
            //узнаем индекс каждого столбца
              int playerNameColumnIndex = cursor.getColumnIndex(DBHelper.KEY_PLAYERNAME);
              int timeColumnIndex = cursor.getColumnIndex(DBHelper.KEY_TIME);
            //проходим через все ряды
            while(cursor.moveToNext())
            {
                //используем индекс для получения строки
                String currentPlayerName = cursor.getString(playerNameColumnIndex);
                arrayNames.add(currentPlayerName);
                double currentTime = cursor.getInt(timeColumnIndex);
                arrayTime.add(currentTime);
                //выведем в логи таблицу
                Log.d("mLog", "name = " + arrayNames +
                        ", time = " + arrayTime);

            }
        }
        finally {
            cursor.close();
        }
      dbHelper.close();

        arraySize=arrayNames.size();

        String[] names = new String[arraySize];
        double[] times = new double[arraySize];

        for(int i=0; i<arrayNames.size(); i++)
        {
            names[i]=arrayNames.get(i);
            times[i]=arrayTime.get(i);

        }
/*
        // Создадим массивы имён котов
        String[] names = { "Васька", "Барсик", "Мурзик", "Рыжик", "Кузя", "Васька", "Барсик", "Мурзик", "Рыжик", "Кузя", "Петр", "Машка", "Светка", "Барсик", "Мурзик", "Рыжик", "Кузя", "Петр", "Машка", "Светка"};
        // Состояние флажка - Накормлен ли кот
        double[] times = { 23.121, 54.121222, 12.12121213, 13.1214, 26.1215, 87.121, 76.12122, 48.1213, 98.1214, 24.1215, 56.1213, 32.1214, 34.1215, 28.12122, 41.1213, 56.1214, 48.1215, 26.1213, 87.1214, 76.1215 };
    */    // Значок
        int imgYes = R.drawable.owl;

        ArrayList<HashMap<String, Object>> myArrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map;

        // заполняем map
        for (int i=0; i<names.length; i++) {
            map = new HashMap<String, Object>();
            map.put("Icon", imgYes);
            map.put("Name", names[i]);
            map.put("Time", times[i]);
            myArrayList.add(map);
        }

   // Массив имен атрибутов, из которых будут читаться данные
    String[] from = { "Icon", "Name", "Time" };

    // Массив идентификаторов компонентов, в которые будем вставлять данные
    int[] to = { R.id.ColIcon, R.id.ColName, R.id.ColTime };

    SimpleAdapter adapter = new SimpleAdapter(this, myArrayList,
                R.layout.list_row, from, to);
        lvResults.setAdapter(adapter);
    }
}
