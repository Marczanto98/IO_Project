package com.example.app2;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView appbar_title;
    private TextView appbar_subtitle;
    private TextView date;
    private TextView time;
    private TextView title;
    private FrameLayout date_behavior;
    private LinearLayout titleAppbar;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private String mUrl;
    private String mImg;
    private String mTitle;
    private String mDate;
    private String mSource;
    private String mAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_news_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        date_behavior = findViewById(R.id.date_behavior);
        imageView = findViewById(R.id.backdrop);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        title = findViewById(R.id.title);

        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mImg = intent.getStringExtra("img");
        mTitle = intent.getStringExtra("title");
        mDate = intent.getStringExtra("date");
        mSource = intent.getStringExtra("source");
        mAuthor = intent.getStringExtra("author");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(Utils.getRandomDrawbleColor());

        Glide.with(this)
                .load(mImg)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);

        date.setText(Utils.DateFormat(mDate));
        title.setText(mTitle);

        String author = null;
        if(mAuthor != null || mAuthor != "")
            mAuthor = " \u2022" + mAuthor;
        else
            author = "";

        time.setText(mSource + author + "\u2022" + Utils.DateToTimeFormat(mDate));

        initWebView(mUrl);
    }

    private void initWebView(String url)
    {
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

}
