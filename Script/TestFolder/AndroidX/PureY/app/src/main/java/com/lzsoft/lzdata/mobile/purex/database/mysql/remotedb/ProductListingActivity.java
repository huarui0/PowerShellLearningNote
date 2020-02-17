package com.lzsoft.lzdata.mobile.purex.database.mysql.remotedb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.common.library.jsonparsing.helper.HttpJsonParser;
import com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus;
import com.lzsoft.lzdata.mobile.purex.database.mysql.ravi.dbconn.NewProductActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.BASE_URL;
import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.CURRENT_IP_ADDRESS;

public class ProductListingActivity extends AppCompatActivity {

    private final String LOG_TAG = NewProductActivity.class.getSimpleName();

    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_ID = "id";
    private static final String KEY_PRODUCT_NAME = "title";
    // private static String BASE_URL = "http://%s/smarthomeapi/";
    private ArrayList<HashMap<String, String>> productList;
    private ListView productListView;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remotedb_product_listing);
        productListView = (ListView) findViewById(R.id.productList);
        new FetchProductsAsyncTask().execute();

    }

    /**
     * Fetches the list of products from the server
     */
    private class FetchProductsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(ProductListingActivity.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            BASE_URL = String.format(
                    BASE_URL,
                    CURRENT_IP_ADDRESS);
            Log.v(LOG_TAG, "开始FetchProductsAsyncTask.doInBackground：" + BASE_URL + "!");

            HttpJsonParser httpJsonParser = new HttpJsonParser();
            JSONObject allProductsJsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "fetch_all_products.php", "GET", null);
            try {
                int success = allProductsJsonObject.getInt(KEY_SUCCESS);
                JSONArray products;
                if (success == 1) {
                    productList = new ArrayList<>();
                    products = allProductsJsonObject.getJSONArray(KEY_DATA);
                    //Iterate through the response and populate products list
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject product = products.getJSONObject(i);
                        Integer productId = product.getInt(KEY_ID);
                        String productName = product.getString(KEY_PRODUCT_NAME);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_ID, productId.toString());
                        map.put(KEY_PRODUCT_NAME, productName);
                        productList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    populateProductList();
                }
            });
        }

    }

    /**
     * Updating parsed JSON data into ListView
     */
    private void populateProductList() {
        ListAdapter adapter = new SimpleAdapter(
                ProductListingActivity.this, productList,
                R.layout.product_list_item, new String[]{KEY_ID,
                KEY_PRODUCT_NAME},
                new int[]{R.id.list_productId, R.id.list_productName});
        // updating listview
        productListView.setAdapter(adapter);
        //Call ProductUpdateDeleteActivity when a product is clicked
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Check for network connectivity
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    String productId = ((TextView) view.findViewById(R.id.list_productId))
                            .getText().toString();
                    Intent intent = new Intent(getApplicationContext(),
                            ProductUpdateDeleteActivity.class);
                    intent.putExtra(KEY_ID, productId);
                    startActivityForResult(intent, 20);

                } else {
                    Toast.makeText(ProductListingActivity.this,
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();

                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 20) {
            // If the result code is 20 that means that
            // the user has deleted/updated the product.
            // So refresh the product listing
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

}