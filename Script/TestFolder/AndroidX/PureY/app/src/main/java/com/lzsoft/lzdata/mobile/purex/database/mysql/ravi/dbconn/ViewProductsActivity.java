package com.lzsoft.lzdata.mobile.purex.database.mysql.ravi.dbconn;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lzsoft.lzdata.mobile.purex.database.mysql.puredata.MySQLDataPureActivity;
import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.common.utils.datautils.AppController;
import com.lzsoft.lzdata.mobile.purex.persistencedata.entity.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.CURRENT_IP_ADDRESS;
import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.connDbBaseUrl;

public class ViewProductsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private final String LOG_TAG = ViewProductsActivity.class.getSimpleName();

    //json object response url
    private String urlForJsonObject = connDbBaseUrl+"get_all_products.php";

    private static String TAG = MainConnDbActivity.class.getSimpleName();

    //progress dialog
    private ProgressDialog pDialog;

    private ListView productsList;
    private TextView noConnection;
    private TextView viewDescription;
    private SwipeRefreshLayout swipeLayout;

    //temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ravi_mydb_view_products);

        productsList = (ListView) findViewById(R.id.listview_all_products);
        noConnection = (TextView) findViewById(R.id.no_connection);
        viewDescription = (TextView) findViewById(R.id.view_description);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeLayout.setOnRefreshListener(this);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait....");
        pDialog.setCancelable(false);

        if (checkForConnection()){
            viewDescription.setVisibility(View.VISIBLE);
            makeJsonObjectRequest();
        }
        else{
            viewDescription.setVisibility(View.GONE);
            noConnection.setVisibility(View.VISIBLE);
        }
    }

    private void makeJsonObjectRequest() {

        showpDialog();

        final List<String> productsArrayList = new ArrayList<String>();
//        后面再用下面的！！ wanglai 2018-11-13
//        final List<Product> productsArrayList = new ArrayList<Product>();

        urlForJsonObject  =String.format(
                urlForJsonObject,
                CURRENT_IP_ADDRESS);
        Log.v(LOG_TAG, "开始urlForJsonObject：" + urlForJsonObject + "!");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlForJsonObject,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString()); //for android log cat??

                        try {
                            // Parsing json object response
                            // response will be a json object
                            int success = response.getInt("success");
                            if (success == 1){
                                JSONArray products = response.getJSONArray("products");
                                for(int i =0; i<products.length(); i++){
                                    JSONObject phone = products.getJSONObject(i);
                                    String name = phone.get("name").toString();
                                    String description = phone.get("description").toString();
                                    String price = phone.get("price").toString();
                                    String rating = phone.get("rating").toString();
                                    String image = phone.get("image").toString();

                                    jsonResponse = "";
                                    jsonResponse += "Name: " + name + "\n\n";
                                    jsonResponse += "Description: " + description + "\n\n";
                                    jsonResponse += "Price: " + price + "\n\n";
                                    jsonResponse += "Rating: " + rating + "\n\n";
                                    jsonResponse += "Image: " + image + "\n\n";

                                    productsArrayList.add(jsonResponse);

                                }

                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                        ViewProductsActivity.this,
                                        android.R.layout.simple_list_item_1,
                                        productsArrayList );

                                productsList.setAdapter(arrayAdapter);
                            }
                            else {
                                Toast.makeText(ViewProductsActivity.this, "No products in the database", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            Log.e(LOG_TAG, "makeJsonObjectRequest_Error：" + e.getMessage() + "!");

                        }

                        hidepDialog();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG,"Error: "+ volleyError.getMessage() );
                Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // 原始有问题，更改为如下方式。wanglai 2018-11-13
        //adding our stringrequest to queue
        try {
            Volley.newRequestQueue(this).add(jsonObjReq);
            // getDataSucess = true;
        } catch (Exception e) {
            displayToast("错误newRequestQueue.add：" + e.toString() + ".");

            e.printStackTrace();
            // getDataSucess = false;
        }



        //adding request to request queue
        // AppController.getmInstance().addToRequestQueue(jsonObjReq);





    }



    private void showpDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hidepDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

    @Override
    public void onRefresh() {
        if (checkForConnection()){
            viewDescription.setVisibility(View.VISIBLE);
            noConnection.setVisibility(View.GONE);
            makeJsonObjectRequest();
            swipeLayout.setRefreshing(false);

        }
        else{
            productsList.setVisibility(View.GONE);
            viewDescription.setVisibility(View.GONE);
            noConnection.setVisibility(View.VISIBLE);
            swipeLayout.setRefreshing(false);

        }
    }

    @Override
    public void onDestroy() {
        // You must call this or the ad adapter may cause a memory leak
        super.onDestroy();
    }

    private Boolean checkForConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
