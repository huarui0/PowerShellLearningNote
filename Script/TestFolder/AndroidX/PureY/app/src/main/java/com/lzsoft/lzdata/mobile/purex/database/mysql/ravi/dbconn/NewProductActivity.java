package com.lzsoft.lzdata.mobile.purex.database.mysql.ravi.dbconn;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.common.utils.datautils.AppController;


import java.util.HashMap;
import java.util.Map;

import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.CURRENT_IP_ADDRESS;
import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.connDbBaseUrl;

public class NewProductActivity extends AppCompatActivity {

    private final String LOG_TAG = NewProductActivity.class.getSimpleName();

    private EditText inputName, inputPrice, inputDesc;
    private EditText inputRating, inputImageUrl;

    private Button btnAddProduct;
    private ProgressDialog pDialog;

    private String urlForAddingProduct = connDbBaseUrl+"create_product.php";
    private static String TAG = NewProductActivity.class.getSimpleName();

    String name, price, description;
    String rating, imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ravi_mydb_new_product);

        inputName = (EditText) findViewById(R.id.conndb_edt_product_name);
        inputDesc = (EditText) findViewById(R.id.conndb_edt_product_description);
        inputPrice = (EditText) findViewById(R.id.conndb_edt_product_price);
        inputRating = (EditText) findViewById(R.id.conndb_txtRatingAdd);
        inputImageUrl = (EditText) findViewById(R.id.conndb_txtImageUrlAdd);

        btnAddProduct = (Button) findViewById(R.id.conndb_btn_add_product);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Adding the product to our database");
        pDialog.setCancelable(false);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    addNewProductToDatabase();
                }

            }
        });
    }

    private boolean validate() {

        name = inputName.getText().toString();
        price = inputPrice.getText().toString();
        description = inputDesc.getText().toString();
        rating = inputRating.getText().toString();
        imageUrl = inputImageUrl.getText().toString();

        if (name.length() == 0 ) {
            inputName.setError("Please enter correct name for the product.");
            return false;
        } else if (description.length() == 0){
            inputDesc.setError("Please enter a short description for the product");
            return false;
        } else if(price.length()==0){
            inputPrice.setError("Please enter correct price for the product.");
            return false;
        } else if(rating.length()==0){
            inputPrice.setError("Please enter correct rating for the product.");
            return false;
        } else if(imageUrl.length()==0){
            inputPrice.setError("Please enter correct imageUrl for the product.");
            return false;
        }
        else{
            return true;
        }
    }

    private void addNewProductToDatabase(){

        showpDialog();

        urlForAddingProduct  =String.format(
                urlForAddingProduct,
                CURRENT_IP_ADDRESS);
        Log.v(LOG_TAG, "开始urlForJsonObject：" + urlForAddingProduct + "!");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForAddingProduct,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        resetFields();
                        hidepDialog();
                        Toast.makeText(NewProductActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v(LOG_TAG, "开始onErrorResponse：" + urlForAddingProduct + "!");
                        hidepDialog();
                        Toast.makeText(NewProductActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name",name);
                params.put("description", description);
                params.put("price",price);
                params.put("rating",rating);
                params.put("image",imageUrl);
                return params;
            }
        };

        // 原始有问题，更改为如下方式。wanglai 2018-11-13
        //adding our stringrequest to queue
        try {
            Volley.newRequestQueue(this).add(stringRequest);
            // getDataSucess = true;
            Log.v(LOG_TAG, "newRequestQueue.add(stringRequest)成功：" + urlForAddingProduct + "!");

        } catch (Exception e) {
            displayToast("错误NewProductActivity_newRequestQueue.add：" + e.toString() + ".");

            e.printStackTrace();
            // getDataSucess = false;
        }

        // AppController.getmInstance().addToRequestQueue(stringRequest);

    }

    private void resetFields() {
        inputName.setText("");
        inputDesc.setText("");
        inputPrice.setText("");
        inputRating.setText("");
        inputImageUrl.setText("");
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


    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
