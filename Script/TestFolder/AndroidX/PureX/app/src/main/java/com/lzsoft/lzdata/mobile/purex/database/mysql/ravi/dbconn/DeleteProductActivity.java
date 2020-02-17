package com.lzsoft.lzdata.mobile.purex.database.mysql.ravi.dbconn;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lzsoft.lzdata.mobile.purex.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.CURRENT_IP_ADDRESS;
import static com.lzsoft.lzdata.mobile.purex.common.utils.networkutils.CheckNetworkStatus.connDbBaseUrl;

public class DeleteProductActivity extends AppCompatActivity {

    private final String LOG_TAG = DeleteProductActivity.class.getSimpleName();

    private Button buttonViewAtId, buttonDelete;
    private EditText productIdInput;
    private TextView productAtPID;
    //temporary string to show the parsed response
    private String jsonResponse;
    private static String TAG = DeleteProductActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String pid;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ravi_mydb_delete_product);

        buttonViewAtId = (Button) findViewById(R.id.conndb_btn_show_at_id);
        buttonDelete = (Button) findViewById(R.id.conndb_button_delete);

        productAtPID = (TextView) findViewById(R.id.conndb_textview_product_at_id);

        layout = (LinearLayout) findViewById(R.id.conndb_product_deletion_view);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        buttonViewAtId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productIdInput = (EditText) findViewById(R.id.conndb_delete_product_id);
                pid = productIdInput.getText().toString();
                getProductAtPID(pid);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProductAtPID(pid);
            }
        });
    }

    private void deleteProductAtPID(final String pid) {
        pDialog.setMessage("Deleting product "+pid+"...");
        showpDialog();

        connDbBaseUrl  =String.format(
                connDbBaseUrl,
                CURRENT_IP_ADDRESS);
        Log.v(LOG_TAG, "开始DeleteProductActivity.StringRequest：" + connDbBaseUrl + "!");

        String baseUrl = connDbBaseUrl + "delete_product.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hidepDialog();
                        Toast.makeText(DeleteProductActivity.this,response,Toast.LENGTH_LONG).show();
                        Log.v(LOG_TAG, "deleteProductAtPID.onResponse(String response)：" + response + "!");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hidepDialog();
                        Toast.makeText(DeleteProductActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(LOG_TAG, "DeleteProductActivity.deleteProductAtPID_VolleyError：" + error.getMessage() + "!");
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("pid",pid);
                return params;
            }
        };

        // 原始有问题，更改为如下方式。wanglai 2018-11-13
        //adding our stringrequest to queue
        try {
            Volley.newRequestQueue(this).add(stringRequest);
            // getDataSucess = true;
            Log.v(LOG_TAG, "DeleteProductActivity.newRequestQueue.add(stringRequest)成功：" + baseUrl + "!");

        } catch (Exception e) {
            displayToast("错误DeleteProductActivity_newRequestQueue.add：" + e.toString() + ".");

            e.printStackTrace();
            // getDataSucess = false;
        }

        // AppController.getmInstance().addToRequestQueue(stringRequest);

    }


    private void getProductAtPID(String pid) {
        pDialog.setMessage("Fetching product details... ");
        showpDialog();

        String baseUrl = connDbBaseUrl+"get_product_details.php"; //home ip
        baseUrl = String.format(
                baseUrl,
                CURRENT_IP_ADDRESS);
        Log.v(LOG_TAG, baseUrl + "!");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                baseUrl+"?pid="+pid,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json object response
                            // response will be a json object
                            if (response.getInt("success") == 1){
                                JSONArray product = response.getJSONArray("product");
                                JSONObject phone = product.getJSONObject(0);
                                String id = phone.get("pid").toString();
                                String name = phone.get("name").toString();
                                String description = phone.get("description").toString();
                                String price = phone.get("price").toString();
                                String rating = phone.get("rating").toString();
                                String imageUrl = phone.get("image").toString();

//                                String created_at = phone.get("created_at").toString();
//                                String updated_at = phone.get("updated_at").toString();
                                jsonResponse = "";
                                jsonResponse += "Id: " + id + "\n\n";
                                jsonResponse += "Name: " + name + "\n\n";
                                jsonResponse += "Description: " + description + "\n\n";
                                jsonResponse += "Price: " + price + "\n\n";
                                jsonResponse += "Rating: " + rating + "\n\n";
                                jsonResponse += "Image: " + imageUrl + "\n\n";
//                                jsonResponse += "Created at: " + created_at + "\n\n";
//                                jsonResponse += "Updated at: " + updated_at + "\n\n";
                                productAtPID.setText(jsonResponse);

                                layout.setVisibility(View.VISIBLE);


                            }
                            else {
                                Toast.makeText(DeleteProductActivity.this, "No products in the database at that id", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.e(TAG,"DeleteProductActivity_VolleyError: "+ volleyError.getMessage() );
                Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v(LOG_TAG, "DeleteProductActivity.onErrorResponse_Errors：" + volleyError.getMessage() + "!");

                hidepDialog();
            }
        });
        // 原始有问题，更改为如下方式。wanglai 2018-11-13
        //adding our stringrequest to queue
        try {
            Volley.newRequestQueue(this).add(jsonObjReq);
            // getDataSucess = true;
            Log.v(LOG_TAG, "DeleteProductActivity.newRequestQueue.add(stringRequest)成功：" + baseUrl + "!");

        } catch (Exception e) {
            displayToast("错误DeleteProductActivity_newRequestQueue.update：" + e.toString() + ".");

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

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
