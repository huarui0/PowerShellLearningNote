// http://blog.csdn.net/ahuier/article/details/10418147

package com.huarui.mobile.sunshine;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.huarui.mobile.sunshine.Service.PersonService;
import com.huarui.mobile.sunshine.data.DBOpenHelper;
import com.huarui.mobile.sunshine.data.PersonDao;

import java.util.List;
import java.util.Map;


/**
 * Created by wanglai on 1/4/2017.
 */

public class MainPersonsActivity extends Activity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    private static final String TAG = "MainPersonsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        initComponent();
        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DBOpenHelper helper = new DBOpenHelper(MainPersonsActivity.this);
                //调用 getWritableDatabase()或者 getReadableDatabase()其中一个方法将数据库建立
                helper.getWritableDatabase();
            }
        });
        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                PersonService service = new PersonDao(MainPersonsActivity.this);
                //Object[] params = {"张三","北京","男"};
                Object[] params = {"李四","上海","男"}; //新增加一条记录
                boolean flag = service.addPersion(params);
                Log.i(TAG, "--->" + flag);
            }
        });
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonService service = new PersonDao(MainPersonsActivity.this);
                Object[] params = {1};
                //将ID为1的记录删除
                boolean flag = service.deletePerson(params);
                Log.i(TAG, "---->" + flag);
            }
        });
        button4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //将ID为3的这一条记录修改
                PersonService service = new PersonDao(MainPersonsActivity.this);
                Object[] params = {"AHuier", "厦门", "男", "3"};
                boolean flag = service.updatePerson(params);
                Log.i(TAG, "--->" + flag);
            }
        });
        button5.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //查询ID为3的单条记录
                PersonService service = new PersonDao(MainPersonsActivity.this);
                String[] seleStrings = {"3"};
                Map<String, String> map = service.viewPerson(seleStrings);
                Log.i(TAG, "------查询单条记录--> " + map.toString());
            }
        });
        button6.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //查询多条记录，这里我们不需要传递参数，所以可以参数可以置为null
                PersonService service = new PersonDao(MainPersonsActivity.this);
                List<Map<String, String>> list = service.listPersonMaps(null);
                Log.i(TAG, "---查询所有记录--->> " + list.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_person_main, menu);
        return true;
    }

    private void initComponent(){
        button1 = (Button)findViewById(R.id.btnCreateDatabase);
        button2 = (Button)findViewById(R.id.btnInsertRecord);
        button3 = (Button)findViewById(R.id.btnDeleteRecord);
        button4 = (Button)findViewById(R.id.btnChangeRecord);
        button5 = (Button)findViewById(R.id.btnQuerySingleRecord);
        button6 = (Button)findViewById(R.id.btnQueryAllRecord);
    }
}
