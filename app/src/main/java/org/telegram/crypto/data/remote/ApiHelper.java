package org.telegram.crypto.data.remote;

import org.telegram.crypto.data.remote.model.ListingsResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public interface ApiHelper {

    Retrofit getRetrofitInstance();

    Observable<ListingsResponse> getListings();
}
