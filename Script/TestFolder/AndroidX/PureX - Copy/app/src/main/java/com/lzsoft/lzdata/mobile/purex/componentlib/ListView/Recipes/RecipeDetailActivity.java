package com.lzsoft.lzdata.mobile.purex.componentlib.ListView.Recipes;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.lzsoft.lzdata.mobile.purex.R;

public class RecipeDetailActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_detail);

        // 1
        String title = this.getIntent().getExtras().getString("title");
        String url = this.getIntent().getExtras().getString("url");
        // url = "http://www.baidu.com";
// 2
        setTitle(title);

// 3
        mWebView = findViewById(R.id.detail_web_view);

// 4
        mWebView.loadUrl(url);
    }
}
