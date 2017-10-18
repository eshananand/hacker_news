package com.hacknews.hacknews.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hacknews.hacknews.R;
import com.hacknews.hacknews.adapter.RecyclerAdapter;
import com.hacknews.hacknews.apitask.GetApiDataTask;
import com.hacknews.hacknews.application.App;
import com.hacknews.hacknews.models.Hit;
import com.hacknews.hacknews.models.News;
import com.hacknews.hacknews.models.UpdateNewEvent;
import com.hacknews.hacknews.util.Logger;
import com.hacknews.hacknews.util.SharedPref;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import io.objectbox.Box;
import io.objectbox.BoxStore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private long newsId;
    private BoxStore boxStore;
    private Box<News> newsBox;
    private News news;
    private SharedPref mSharedPref;
    private GetApiDataTask getApiDataTask;
    private List<Hit> hitList = new ArrayList<>();
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        EventBus.getDefault().register(this);
        boxStore = ((App)this.getApplication()).getBoxStore();
        newsBox = boxStore.boxFor(News.class);
        mSharedPref = SharedPref.getInstance(this);
        newsId = mSharedPref.getNewsId();
        btnSearch.setOnClickListener(this);

        setUpRecycleView();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    // This method will be called when a News Event is posted
    public void onEventMainThread(UpdateNewEvent event) {
        // your implementation
        Logger.d("LoginINAct", " inside on Event received");
        newsId = event.getMessage();

        setUpRecycleView();
    }

    private void setUpRecycleView()
    {
        if (newsId > 0) {
            news = newsBox.get(newsId);
            mSharedPref.saveNewsId(newsId);

            for (int i = 0; i < news.hits.size(); i++)
            {
                hitList.add(news.hits.get(i));
            }

            mAdapter = new RecyclerAdapter(this, hitList);
            mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
            newsRecycler.setLayoutManager(mLayoutManager);
            newsRecycler.setAdapter(mAdapter);
            progressbar.setVisibility(View.GONE);

        } else {
            Toast.makeText(MainActivity.this, "No News present", Toast.LENGTH_LONG).show();

            return;
        }
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.btnSearch:
                progressbar.setVisibility(View.VISIBLE);
                hitList.clear();
                mAdapter.notifyDataSetChanged();
                getApiDataTask = new GetApiDataTask(MainActivity.this, etSearch.getText().toString());
                getApiDataTask.getNewsData();
                break;
        }
    }
}
