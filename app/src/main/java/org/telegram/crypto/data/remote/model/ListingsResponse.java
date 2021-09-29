package org.telegram.crypto.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListingsResponse {

    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("data")
    @Expose
    private List<CurrencyResponse> currencyResponses = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<CurrencyResponse> getData() {
        return currencyResponses;
    }

    public void setData(List<CurrencyResponse> currencyResponses) {
        this.currencyResponses = currencyResponses;
    }

}