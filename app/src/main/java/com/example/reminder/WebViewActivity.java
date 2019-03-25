package com.example.reminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import static com.example.reminder.MainActivity.WEBSITE_OPENEN;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = new WebView(this);
        setContentView(webView);

        Intent intent = getIntent();
        Website website = intent.getParcelableExtra(WEBSITE_OPENEN);

        String URL = website.getURL();

        webView.loadUrl(URL);
    }
}
