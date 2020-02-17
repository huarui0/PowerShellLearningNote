package com.lzsoft.lzdata.mobile.purex.database.mysql.remotedb;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.CURRENT_IP_ADDRESS;

public class ProductUpdateDeleteActivity extends AppCompatActivity {
    private static String STRING_EMPTY = "";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_ID = "id";
    private static final String KEY_PRODUCT_NAME = "title";
    private static final String KEY_SHOTDESC = "shortdesc";
    private static final String KEY_RATING = "rating";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IMAGEURL = "image";
    // private static String BASE_URL = "http://%s/smarthomeapi/";
    private static String BASE_URL = "http://%s/smarthomeapi/conndb/";
    private String productId;
    private EditText productNameEditText;
    private EditText shortDescEditText;
    private EditText ratingEditText;
    private EditText priceEditText;
    private EditText imageUrlEditText;
    private String productName;
    private String shortDesc;
    private String rating;
    private String price;
    private String imageUrl;
    private Button deleteButton;
    private Button updateButton;
    private int success;
    private ProgressDialog pDialog;

    private final String LOG_TAG = ProductUpdateDeleteActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remotedb_product_update_delete);
        Intent intent = getIntent();
        productNameEditText = findViewById(R.id.txtProductNameUpdate);
        shortDescEditText = findViewById(R.id.txtShotDescUpdate);
        ratingEditText = findViewById(R.id.txtRatingUpdate);
        priceEditText = findViewById(R.id.txtPriceUpdate);
        imageUrlEditText = findViewById(R.id.txtImageUrlUpdate);

        productId = intent.getStringExtra(KEY_ID);
        new FetchProductDetailsAsyncTask().execute();
        deleteButton = (Button) findViewById(R.id.btnProductDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete();
            }
        });
        updateButton = (Button) findViewById(R.id.btnProductUpdate);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    updateProduct();

                } else {
                    Toast.makeText(ProductUpdateDeleteActivity.this,
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    /**
     * Fetches single product details from the server
     */
    private class FetchProductDetailsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(ProductUpdateDeleteActivity.this);
            pDialog.setMessage("Loading Product Details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            httpParams.put(KEY_ID, productId);

            BASE_URL = String.format(
                    BASE_URL,
                    CURRENT_IP_ADDRESS);
            Log.v(LOG_TAG, BASE_URL + "!");

            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "get_product_details.php", "GET", httpParams);
            try {
                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONObject product;
                if (success == 1) {
                    //Parse the JSON response
                    product = jsonObject.getJSONObject(KEY_DATA);
                    productName = product.getString(KEY_PRODUCT_NAME);
                    shortDesc = product.getString(KEY_SHOTDESC);
                    rating = product.getString(KEY_RATING);
                    price = product.getString(KEY_PRICE);
                    imageUrl = product.getString(KEY_IMAGEURL);

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
                    //Populate the Edit Texts once the network activity is finished executing
                    productNameEditText.setText(productName);
                    shortDescEditText.setText(shortDesc);
                    ratingEditText.setText(rating);
                    priceEditText.setText(price);
                    imageUrlEditText.setText(imageUrl);

                }
            });
        }


    }

    /**
     * Displays an alert dialogue to confirm the deletion
     */
    private void confirmDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                ProductUpdateDeleteActivity.this);
        alertDialogBuilder.setMessage("Are you sure, you want to delete this product?");
        alertDialogBuilder.setPositiveButton("Delete",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                            //If the user confirms deletion, execute DeleteProductAsyncTask
                            new DeleteProductAsyncTask().execute();
                        } else {
                            Toast.makeText(ProductUpdateDeleteActivity.this,
                                    "Unable to connect to internet",
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel", null);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**
     * AsyncTask to delete a product
     */
    private class DeleteProductAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(ProductUpdateDeleteActivity.this);
            pDialog.setMessage("Deleting Product. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Set product_id parameter in request
            httpParams.put(KEY_ID, productId);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "delete_product.php", "POST", httpParams);
            try {
                success = jsonObject.getInt(KEY_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(ProductUpdateDeleteActivity.this,
                                "Product Deleted", Toast.LENGTH_LONG).show();
                        Intent i = getIntent();
                        //send result code 20 to notify about product deletion
                        setResult(20, i);
                        finish();

                    } else {
                        Toast.makeText(ProductUpdateDeleteActivity.this,
                                "Some error occurred while deleting product",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    /**
     * Checks whether all files are filled. If so then calls UpdateProductAsyncTask.
     * Otherwise displays Toast message informing one or more fields left empty
     */
    private void updateProduct() {


        if (!STRING_EMPTY.equals(productNameEditText.getText().toString()) &&
                !STRING_EMPTY.equals(shortDescEditText.getText().toString()) &&
                !STRING_EMPTY.equals(ratingEditText.getText().toString()) &&
                !STRING_EMPTY.equals(priceEditText.getText().toString()) &&
                !STRING_EMPTY.equals(imageUrlEditText.getText().toString())
                ) {

            productName = productNameEditText.getText().toString();
            shortDesc = shortDescEditText.getText().toString();
            rating = ratingEditText.getText().toString();
            price = priceEditText.getText().toString();
            new UpdateProductAsyncTask().execute();
        } else {
            Toast.makeText(ProductUpdateDeleteActivity.this,
                    "One or more fields left empty!",
                    Toast.LENGTH_LONG).show();

        }


    }

    /**
     * AsyncTask for updating a product details
     */

    private class UpdateProductAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(ProductUpdateDeleteActivity.this);
            pDialog.setMessage("Updating Product. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters
            httpParams.put(KEY_ID, productId);
            httpParams.put(KEY_PRODUCT_NAME, productName);
            httpParams.put(KEY_SHOTDESC, shortDesc);
            httpParams.put(KEY_RATING, rating);
            httpParams.put(KEY_PRICE, price);
            httpParams.put(KEY_IMAGEURL, imageUrl);

            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "update_product.php", "POST", httpParams);
            try {
                success = jsonObject.getInt(KEY_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message
                        Toast.makeText(ProductUpdateDeleteActivity.this,
                                "Product Updated", Toast.LENGTH_LONG).show();
                        Intent i = getIntent();
                        //send result code 20 to notify about product update
                        setResult(20, i);
                        finish();

                    } else {
                        Toast.makeText(ProductUpdateDeleteActivity.this,
                                "Some error occurred while updating product",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}