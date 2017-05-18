package com.example.duska.axelerom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultsActivity extends AppCompatActivity {

    ListView lvResults;
    public static int id=0;
    MazeActivity mazeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        lvResults = (ListView) findViewById(R.id.lvResults);

        mazeActivity = new MazeActivity();
        ArrayList<String> arrayNames_ = new ArrayList<String>();
       // arrayNames_ = значения из результатов(файл? бд?)
        int arraySize=arrayNames_.size();

        String[] names2 = new String[arraySize];
        int[] times2 = new int[arraySize];

        for(int i=0; i<arrayNames_.size(); i++)
        {
            names2[i]=arrayNames_.get(i);
            times2[i]=0;

        }

        // Создадим массивы имён котов
        String[] names = { "Васька", "Барсик", "Мурзик", "Рыжик", "Кузя", "Васька", "Барсик", "Мурзик", "Рыжик", "Кузя", "Петр", "Машка", "Светка", "Барсик", "Мурзик", "Рыжик", "Кузя", "Петр", "Машка", "Светка"};
        // Состояние флажка - Накормлен ли кот
        double[] times = { 23.121, 54.121222, 12.12121213, 13.1214, 26.1215, 87.121, 76.12122, 48.1213, 98.1214, 24.1215, 56.1213, 32.1214, 34.1215, 28.12122, 41.1213, 56.1214, 48.1215, 26.1213, 87.1214, 76.1215 };
        // Значок
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
