package com.huarui.mobile.sunshine;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by wanglai on 11/27/2016.
 */

public class MainListView2Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview2_main);

        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.custom_list);


        ColorDrawable devidrColor = new ColorDrawable(getColor(R.color.list_row_default_bg));
        lv1.setDivider(devidrColor);
        lv1.setDividerHeight(1);

        lv1.setAdapter(new CustomListAdapter(this, image_details));
        lv1.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                NewsItem newsData = (NewsItem) o;
                Toast.makeText(MainListView2Activity.this, "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList getListData() {
        ArrayList<NewsItem> results = new ArrayList<NewsItem>();
        NewsItem newsData = new NewsItem();
        newsData.setHeadline("Dance of Democracy");
        newsData.setReporterName("Pankaj Gupta");
        newsData.setDate("May 26, 2013, 13:35");
        results.add(newsData);


        NewsItem newsData2 = new NewsItem();
        newsData2.setHeadline("Dance of Democracy");
        newsData2.setReporterName("Pankaj Gupta");
        newsData2.setDate("May 26, 2013, 13:35");
        results.add(newsData2);


        NewsItem newsData3 = new NewsItem();
        newsData3.setHeadline("再见吧，你们！");
        newsData3.setReporterName("张三");
        newsData3.setDate("May 27, 2013, 15:35");
        results.add(newsData3);

        // Add some more dummy data for testing
        return results;
    }
}
