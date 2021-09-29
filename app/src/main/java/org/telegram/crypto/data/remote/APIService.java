package org.telegram.crypto.data.remote;

import org.telegram.crypto.data.remote.model.ListingsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET(ApiEndPoint.LISTINGS)
    Observable<ListingsResponse> getListingService(@Query("CMC_PRO_API_KEY") String apiKey);
}