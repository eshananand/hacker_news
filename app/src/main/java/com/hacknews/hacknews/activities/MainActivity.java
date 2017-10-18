package com.hacknews.hacknews.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hacknews.hacknews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = MainActivity.class.getSimpleName();


    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.newsRecycler)
    RecyclerView newsRecycler;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.btnSearch:

                break;
        }
    }
}
