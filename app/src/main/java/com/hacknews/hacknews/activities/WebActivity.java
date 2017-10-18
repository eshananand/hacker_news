package com.hacknews.hacknews.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.hacknews.hacknews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity
{

    String url;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if(intent.getBundleExtra("URL_BUNDLE") != null)
        {
            url =intent.getBundleExtra("URL_BUNDLE").getString("URL");
            webView.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        webView.destroy();
        finish();

    }
}
