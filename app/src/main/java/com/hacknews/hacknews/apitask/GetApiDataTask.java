package com.hacknews.hacknews.apitask;

import android.app.Activity;

import com.google.gson.Gson;
import com.hacknews.hacknews.R;
import com.hacknews.hacknews.application.App;
import com.hacknews.hacknews.models.Hit;
import com.hacknews.hacknews.models.News;
import com.hacknews.hacknews.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.greenrobot.event.EventBus;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eshan on 10/18/17.
 */

public class GetApiDataTask
{

    private static final String TAG = GetApiDataTask.class.getSimpleName();
    private static Retrofit retrofit = null;
    private Activity mContext;
    private String category;


    public GetApiDataTask( Activity context,String category) {
        mContext = context;
        this.category = category;
    }

    public static Retrofit getClient(Activity activity) {
        if (retrofit == null) {

            String url = activity.getApplicationContext().getString(R.string.url);
            Logger.d(TAG, "url is " + url);


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


    public void getNewsData() {
        final ApiService getResponse = getClient(mContext).create(ApiService.class);
        Call<ResponseBody> call = getResponse.getNews(category);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody serverResponse = response.body();

                if (serverResponse != null) {
                    if (response.isSuccessful()) {
                        try {
                            String repString = serverResponse.string();
                            JSONObject jsonObject = new JSONObject(repString);

                            News news = new News();
                            news.setPage(jsonObject.getInt("page"));
                            news.setNbPages(jsonObject.getInt("nbPages"));
                            news.setNbHits(jsonObject.getInt("nbHits"));
                            news.setHitsPerPage(jsonObject.getInt("hitsPerPage"));
                            news.setExhaustiveNbHits(jsonObject.getBoolean("exhaustiveNbHits"));
                            news.setQuery(jsonObject.getString("query"));
                            JSONArray jsonArray = jsonObject.getJSONArray("hits");
                            Gson gson = new Gson();
                            for(int i = 0; i < jsonArray.length(); i ++)
                            {
                                Hit hit = gson.fromJson(jsonArray.get(i).toString(), Hit.class);
                                news.hits.add(hit);
                            }
                            Logger.d(TAG, "hit author is " + news.hits.get(0).getAuthor());
                            BoxStore boxStore = ((App)mContext.getApplication()).getBoxStore();
                            Box<News> newsBox = boxStore.boxFor(News.class);
                            newsBox.put(news);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                    }
                } else {
                    assert serverResponse != null;
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


}
