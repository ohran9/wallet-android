package org.telegram.crypto.data;

import org.telegram.crypto.data.remote.ApiHelper;
import org.telegram.crypto.data.remote.model.ListingsResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    public AppDataManager(ApiHelper mApiHelper) {
        this.mApiHelper = mApiHelper;
    }

    @Override
    public Retrofit getRetrofitInstance() {
        return mApiHelper.getRetrofitInstance();
    }

    @Override
    public Observable<ListingsResponse> getListings() {
        return mApiHelper.getListings();
    }
}
