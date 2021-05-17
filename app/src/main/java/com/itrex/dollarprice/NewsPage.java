package com.itrex.dollarprice;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NewsPage extends AppCompatActivity {

    TextView title, info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        title = findViewById(R.id.newsPageTitle);
        info = findViewById(R.id.newsPageInfo);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        info.setText(intent.getStringExtra("info"));
        info.setMovementMethod(new ScrollingMovementMethod());


    }
}