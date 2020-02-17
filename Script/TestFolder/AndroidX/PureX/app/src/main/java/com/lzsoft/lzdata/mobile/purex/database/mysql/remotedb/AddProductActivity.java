package com.lzsoft.lzdata.mobile.purex.database.mysql.remotedb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.common.library.jsonparsing.helper.HttpJsonParser;
import com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.BASE_URL;
import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.CURRENT_IP_ADDRESS;

public class AddProductActivity extends AppCompatActivity {

    private final String LOG_TAG = AddProductActivity.class.getSimpleName();

    private static final String KEY_SUCCESS = "success";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_PRODUCT_NAME = "title";
    private static final String KEY_SHORTDESC = "shortdesc";
    private static final String KEY_PRICE = "price";
    private static final String KEY_RATING = "rating";
    private static final String KEY_IMAGEURL = "image";
    // private static String BASE_URL = "http://%s/smarthomeapi/";
    private static String STRING_EMPTY = "";
    private EditText productNameEditText;
    private EditText shortDescEditText;
    private EditText ratingEditText;
    private EditText priceEditText;
    private EditText imageUrlEditText;
    private String productName;
    private String shortDesc;
    private double rating;
    private double price;
    private String imageUrl;

    private Button addButton;
    private int success;
    private String returnMessage;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remotedb_add_product);
        productNameEditText = findViewById(R.id.txtProductNameAdd);
        shortDescEditText = findViewById(R.id.txtShortDescAdd);
        priceEditText = findViewById(R.id.txtPriceAdd);
        ratingEditText = findViewById(R.id.txtRatingAdd);
        imageUrlEditText = findViewById(R.id.txtImageUrlAdd);
        addButton = findViewById(R.id.btnAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    addProduct();
                } else {
                    Toast.makeText(AddProductActivity.this,
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    /**
     * Checks whether all files are filled. If so then calls AddProductAsyncTask.
     * Otherwise displays Toast message informing one or more fields left empty
     */
    private void addProduct() {
        if (!STRING_EMPTY.equals(productNameEditText.getText().toString()) &&
                !STRING_EMPTY.equals(shortDescEditText.getText().toString()) &&
                !STRING_EMPTY.equals(priceEditText.getText().toString()) &&
                !STRING_EMPTY.equals(ratingEditText.getText().toString()) &&
                !STRING_EMPTY.equals(imageUrlEditText.getText().toString())) {

            productName = productNameEditText.getText().toString();
            shortDesc = shortDescEditText.getText().toString();
            price = Double.valueOf(priceEditText.getText().toString());
            rating = Double.valueOf(ratingEditText.getText().toString());
            imageUrl = imageUrlEditText.getText().toString();
            new AddProductAsyncTask().execute();
        } else {
            Toast.makeText(AddProductActivity.this,
                    "One or more fields left empty!",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * AsyncTask for adding a product
     */
    private class AddProductAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display proggress bar
            pDialog = new ProgressDialog(AddProductActivity.this);
            pDialog.setMessage("Adding Product. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters
            httpParams.put(KEY_PRODUCT_NAME, productName);
            httpParams.put(KEY_SHORTDESC, shortDesc);
            httpParams.put(KEY_PRICE, String.valueOf(price));
            // httpParams.put(KEY_RATING, Double.toString(rating));
            httpParams.put(KEY_RATING, String.valueOf(rating));
            // httpParams.put(KEY_PRICE, Double.toString(price));
            httpParams.put(KEY_IMAGEURL, imageUrl);

            BASE_URL = String.format(
                    BASE_URL,
                    CURRENT_IP_ADDRESS);
            Log.v(LOG_TAG, BASE_URL + "!");
            Log.v(LOG_TAG, "httpParams = :" + httpParams + "!");
            String addProductApiUrl = BASE_URL + "addProductApi.php";
            Log.v(LOG_TAG, "addProductApiUrl : " + addProductApiUrl + "!");


            try {
                JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                        addProductApiUrl, "POST", httpParams);
                try {
                    success = jsonObject.getInt(KEY_SUCCESS);
                    returnMessage = jsonObject.getString(KEY_MESSAGE);
                    Log.v(LOG_TAG, "jsonObject.getInt(KEY_SUCCESS)执行成功:" + returnMessage + "!");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(LOG_TAG, "jsonObject.getInt(KEY_SUCCESS)错误:" + e.getMessage() + "!");

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("JSON Parser", "Error parsing data,httpJsonParser.makeHttpRequest错误1 " + e.toString());
                Log.v(LOG_TAG, "httpJsonParser.makeHttpRequest错误:" + e.getMessage() + "!");

            }

            return null;
        }

        protected void onPostExecute(String result) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(AddProductActivity.this,
                                "Product Added", Toast.LENGTH_LONG).show();
                        Toast.makeText(AddProductActivity.this,
                              "From PHP message:" + KEY_MESSAGE, Toast.LENGTH_LONG).show();
                        Intent i = getIntent();
                        //send result code 20 to notify about product update
                        setResult(20, i);
                        //Finish ths activity and go back to listing activity
                        finish();

                    } else {
                        Toast.makeText(AddProductActivity.this,
                                "Some error occurred while adding product",
                                Toast.LENGTH_LONG).show();
                        Toast.makeText(AddProductActivity.this,
                                "From PHP message:" + KEY_MESSAGE, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}