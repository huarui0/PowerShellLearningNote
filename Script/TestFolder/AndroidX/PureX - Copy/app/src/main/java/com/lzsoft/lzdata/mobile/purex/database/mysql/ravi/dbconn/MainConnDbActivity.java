package com.lzsoft.lzdata.mobile.purex.database.mysql.ravi.dbconn;


import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lzsoft.lzdata.mobile.purex.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainConnDbActivity extends AppCompatActivity {

    Button btnViewProducts, btnUpdateProduct, btnNewProduct, btnDeleteProduct;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ravi_mydb_main);

        btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
        btnNewProduct = (Button) findViewById(R.id.btnCreateProduct);
        btnUpdateProduct = (Button) findViewById(R.id.btnUpdateProduct);
        btnDeleteProduct = (Button) findViewById(R.id.btnDeleteProduct);


        btnViewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainConnDbActivity.this, ViewProductsActivity.class);
                startActivity(intent);
            }
        });


        btnNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainConnDbActivity.this, NewProductActivity.class);
                startActivity(intent);
            }
        });

        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainConnDbActivity.this, UpdateProductActivity.class));
            }
        });

        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainConnDbActivity.this, DeleteProductActivity.class));
            }
        });


    }





}


