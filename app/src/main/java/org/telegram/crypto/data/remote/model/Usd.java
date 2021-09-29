package org.telegram.crypto.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usd {

    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("volume_24h")
    @Expose
    private Double volume24h;
    @SerializedName("percent_change_1h")
    @Expose
    private Double percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    private Double percentChange24h;
    @SerializedName("percent_change_7d")
    @Expose
    private Double percentChange7d;
    @SerializedName("percent_change_30d")
    @Expose
    private Double percentChange30d;
    @SerializedName("percent_change_60d")
    @Expose
    private Double percentChange60d;
    @SerializedName("percent_change_90d")
    @Expose
    private Double percentChange90d;
    @SerializedName("market_cap")
    @Expose
    private Double marketCap;
    @SerializedName("market_cap_dominance")
    @Expose
    private Double marketCapDominance;
    @SerializedName("fully_diluted_market_cap")
    @Expose
    private Double fullyDilutedMarketCap;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(Double volume24h) {
        this.volume24h = volume24h;
    }

    public Double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(Double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public Double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(Double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public Double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(Double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public Double getPercentChange30d() {
        return percentChange30d;
    }

    public void setPercentChange30d(Double percentChange30d) {
        this.percentChange30d = percentChange30d;
    }

    public Double getPercentChange60d() {
        return percentChange60d;
    }

    public void setPercentChange60d(Double percentChange60d) {
        this.percentChange60d = percentChange60d;
    }

    public Double getPercentChange90d() {
        return percentChange90d;
    }

    public void setPercentChange90d(Double percentChange90d) {
        this.percentChange90d = percentChange90d;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    public Double getMarketCapDominance() {
        return marketCapDominance;
    }

    public void setMarketCapDominance(Double marketCapDominance) {
        this.marketCapDominance = marketCapDominance;
    }

    public Double getFullyDilutedMarketCap() {
        return fullyDilutedMarketCap;
    }

    public void setFullyDilutedMarketCap(Double fullyDilutedMarketCap) {
        this.fullyDilutedMarketCap = fullyDilutedMarketCap;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}