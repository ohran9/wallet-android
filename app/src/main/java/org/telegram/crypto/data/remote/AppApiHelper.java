package org.telegram.crypto.data.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.telegram.crypto.data.remote.model.ListingsResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppApiHelper implements ApiHelper {

    private static final int NETWORK_CALL_TIMEOUT = 30;
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/";
    private static final String API_KEY = "b57fec18-3560-4189-8efb-a10363008f9d";
    private final Context mContext;

    public AppApiHelper(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Retrofit getRetrofitInstance() {

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(mContext.getCacheDir(), cacheSize);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        builder.readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient httpClient = builder.build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Override
    public Observable<ListingsResponse> getListings() {
        return getRetrofitInstance().create(APIService.class).getListingService(API_KEY);
    }
}
