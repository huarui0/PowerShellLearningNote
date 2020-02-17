package com.lzsoft.lzdata.mobile.purex.database.mysql.puredata;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.anniinteropter.MyBounceInterpolator;
import com.lzsoft.lzdata.mobile.purex.persistencedata.entity.Product;
import com.lzsoft.lzdata.mobile.purex.persistencedata.adapter.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.CURRENT_IP_ADDRESS;

public class MySQLDataPureActivity extends AppCompatActivity {

    private final String LOG_TAG = MySQLDataPureActivity.class.getSimpleName();

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS_FORMATTER = "http://%s/smarthomeapi/productApi.php";
    // private static final String  URL_PRODUCTS = "http://192.168.1.102/smarthomeapi/productApi.php";
    // private static String  URL_PRODUCTS = "http://192.168.0.14/smarthomeapi/productApi.php";
    private static String  URL_PRODUCTS = "http://%s/smarthomeapi/productApi.php";

// 用配置文件，如Json格式的文件，就不用修改程序了，后续更改。wanglai 2018-11-12
    private static final String[] IP_ADDRESS_GROUP = {"192.168.1.102","192.168.0.14","192.168.0.74"};


    //a list to store all the products
    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;

    private Button returnTestButton;
    private Button forwardTestButton;
    private boolean getDataSucess = false;

    // 线程标志
    private boolean isStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql_pure_data);

        returnTestButton = findViewById(R.id.mySql_return_button_test);
        returnTestButton.setOnClickListener(new View.OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(MySQLDataPureActivity.this, "返回的按钮", Toast.LENGTH_SHORT).show();
                // Button button = findViewById(R.id.enlarge_to_ani_button_test);
                didTapImageButton(returnTestButton);

/*
                Intent intent = new Intent(AnimationTestActivity.this, MoviesTabsSwipeActivity.class);
                startActivity(intent);
*/
                // startActivity(new Intent(MySQLDataPureActivity.this, AnimationTestActivity.class));

            }
        });

        forwardTestButton = findViewById(R.id.mySql_forward_button_test);
        forwardTestButton.setOnClickListener(new View.OnClickListener() {//匿名内部类，按钮的点击事件

            @Override
            public void onClick(View v) {
                Toast.makeText(MySQLDataPureActivity.this, "启动图片放大界面的按钮", Toast.LENGTH_SHORT).show();
                // Button button = findViewById(R.id.enlarge_to_ani_button_test);
                didTapImageButton(forwardTestButton);
/*
                Intent intent = new Intent(AnimationTestActivity.this, MoviesTabsSwipeActivity.class);
                startActivity(intent);
*/
                // startActivity(new Intent(MySQLDataPureActivity.this, FoodSQLiteDemoActivity.class));

            }
        });

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        //initializing the productlist
        productList = new ArrayList<>();

        // this method will fetch and parse json
        // to display it in recyclerview
        loadProducts();
        // loadProducts_TestData();
    }

    public Runnable loadProductsRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            while (getDataSucess = false && !isStop)
            {
                // TODO add code to refresh in background
                try
                {
                    loadProducts();
                    Thread.sleep(1000);// sleeps 1 second
                    //Do Your process here.
                } catch (InterruptedException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    };

    @Override
    protected void onDestroy() {
        // 关闭定时器
        isStop = true;

        super.onDestroy();

    }

    public final boolean productURLTest(String[] ipAddressGroup) {

            try {
                // Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 192.168.1.14");
                Process p1 = Runtime.getRuntime().exec("ping 192.168.1.14");
                boolean returnVal = p1.waitFor(3,TimeUnit.SECONDS);
                boolean reachable = (returnVal==true);
                return reachable;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
    }

    public final String productURL(String[] ipAddressGroup) {
        // Java String Array to List
        List<String> ipAddressGroupList = Arrays.asList(ipAddressGroup);
        String volidProductURL = null;
        for (String strIpAddress : ipAddressGroup) {

            System.out.print(strIpAddress);

            // 使用域名创建对象
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getByName(strIpAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            System.out.println(inetAddress);

            // 获得对象中存储的IP
            String ip = inetAddress.getHostAddress();
            System.out.println("IP:" + ip);

            String ipAddr = String.format(
                    URL_PRODUCTS_FORMATTER,
                    strIpAddress);

        }

        return volidProductURL;
    }

    // added by wanglai 2018-11-12
    public final boolean isNetWorkConnected() {
        // get Connectivity Manager object to check connection
        ConnectivityManager cm =
                (ConnectivityManager)getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();

        // Check for network connections
        if (isConnected) {
            return true;
        } else {
            return false;
        }
    }
    // 功能同上（相同）
    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    public static boolean IsReachable(Context context, String check_url) {
        // First, check we have any sort of connectivity
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        boolean isReachable = false;

        if (netInfo != null && netInfo.isConnected()) {
            // Some sort of connection is open, check if server is reachable
            try {

                URL url = new URL(check_url);

                // URL url = new URL("http://192.168.100.93/office_com/www/api.php/office_com/Logins/SignUp?api_user=endpoint");
                //URL url = new URL("http://10.0.2.2");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestProperty("User-Agent", "Android Application");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(10 * 1000);
                try {
                    urlc.connect();
                    System.out.println("-----fffff");
                } catch (Exception e) {

                    System.out.println("-----fffff  " + e);

                }
                isReachable = (urlc.getResponseCode() == 200);
            } catch (IOException e) {

            }
        }

        return isReachable;
    }

    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        // StringRequest stringRequest = null;

        URL_PRODUCTS = String.format(
                URL_PRODUCTS_FORMATTER,
                CURRENT_IP_ADDRESS);
        Log.v(LOG_TAG, URL_PRODUCTS + "!");

/*
        for (String strIpAddress : IP_ADDRESS_GROUP) {
            URL_PRODUCTS = String.format(
                    URL_PRODUCTS_FORMATTER,
                    strIpAddress);
            Log.v(LOG_TAG, URL_PRODUCTS + "!");

            // boolean isReachabled;
            // isReachabled = IsReachable(MySQLDataPureActivity.this, URL_PRODUCTS);
            Log.v(LOG_TAG, "getDataSucess = " + getDataSucess + "!");
            while (getDataSucess == false) {
*/

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    Log.d(LOG_TAG, "StringRequest onResponse begin: " + "!");
                                    //converting the string to json array object
                                    JSONArray array = new JSONArray(response);
                                    Log.d(LOG_TAG, "StringRequest new JSONArray(response) ended: " + "!");

                                    //traversing through all the object
                                    for (int i = 0; i < array.length(); i++) {

                                        //getting product object from json array
                                        JSONObject product = array.getJSONObject(i);

// Best method to check if a value of a String is null in Java
/*
// if(myString == null){}

// Objects.equals(null, myString);

// ObjectUtils.isEmpty(myString);

// myString.equals(null)

// myString.compareTo(null);
*/
                                        Double thisPrice;
                                        if(!product.get("price").equals(null)) {
                                            //do something
                                            try {
                                                thisPrice = product.getDouble("price");
                                            } catch (JSONException e) {
                                                thisPrice = Double.valueOf(0);
                                                e.printStackTrace();
                                            }
                                        } else {
                                            //do something else
                                            Log.v(LOG_TAG, "product.getDouble(\"price\")" + product.get("price").toString() + "!");

                                            thisPrice = Double.valueOf(0);
                                        }

                                        Double thisRating;
                                        if(!product.get("rating").equals(null)) {
                                            //do something
                                            try {
                                                thisRating = product.getDouble("rating");
                                            } catch (JSONException e) {
                                                Log.e(LOG_TAG, "product.getDouble(\"rating\")" + product.toString() + "!");
                                                thisRating = Double.valueOf(0);
                                                e.printStackTrace();
                                            }
                                        } else {
                                            //do something else
                                            thisRating = Double.valueOf(0);
                                        }

                                        //adding the product to product list
                                        productList.add(new Product(
                                                product.getInt("id"),
                                                product.getString("title"),
                                                product.getString("shortdesc"),
                                                thisRating,
                                                thisPrice,
                                                product.getString("image")
                                        ));
                                    }

                                    //creating adapter object and setting it to recyclerview
                                    //                   ProductAdapter adapter = new ProductAdapter(MySQLDataPureActivity.this, productList);
                                    //                   recyclerView.setAdapter(adapter);
                                    Log.v(LOG_TAG, "begin productList recyclerView.setAdapter:" + "!");
                                    ProductAdapter adapter = new ProductAdapter(getBaseContext(), productList);
                                    recyclerView.setAdapter(adapter);
                                    Log.v(LOG_TAG, "recyclerView.setAdapter has finished:" + "!!");

                                    // getDataSucess = true;

                                } catch (JSONException e) {
                                    Log.e(LOG_TAG, e.getMessage(), e);
                                    e.printStackTrace();

                                    // getDataSucess = false;

                                }
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(LOG_TAG, error.getMessage(), error);

                                // getDataSucess = false;

                            }
                        });
/*
                if (getDataSucess == false) {
                    break;
                }
*/

                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                ));

/*
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
*/

                //adding our stringrequest to queue

                try {
                    Volley.newRequestQueue(this).add(stringRequest);

                    // getDataSucess = true;

                } catch (Exception e) {
                    displayToast("错误：" + e.toString() + ".");

                    e.printStackTrace();

                    // getDataSucess = false;

                }
/*
                if (getDataSucess = true) {
                    break;
                }
*/

/*
            }
        }
*/
    }

    private void loadProducts_TestData() {

        /*
         * 测试用数据
         * */

//adding the product to product list
        productList.add(new Product(
                555,
                "电感式接近传感器KJT-J12M",
                "KJTDQ/凯基特",
                3.5,
                55.12,
                "https://cbu01.alicdn.com/img/ibank/2016/924/783/3609387429_503867266.220x220.jpg"
        ));

        productList.add(new Product(
                555,
                "生产测温型NTC热敏电阻3950/NTC温度传感器",
                "可加工各类型探头",
                4.5,
                5.18,
                "https://cbu01.alicdn.com/img/ibank/2018/880/341/8754143088_399655671.220x220.jpg"
        ));

        ProductAdapter adapter = new ProductAdapter(getBaseContext(),productList);
        recyclerView.setAdapter(adapter);

    }

    public void didTapImageButton(Button thisButton) {
        Button button = thisButton;
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_button);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.3, 20);  // 0.2 改为 0.5 跳得就很激烈
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
