package org.telegram.crypto.data.local.model;

public class Currency {

    private String name;
    private String symbol;
    private double price;
    private double twentyFourHrPr;
    private double sevenDayPr;
    private double marketCap;
    private double volume;
    private boolean starStatus;
    private boolean isTwentyFourHrPos;
    private boolean isSevenDayPos;

    public Currency() {

    }

    public Currency(String name, String symbol, double price, double twentyFourHrPr,
                    double sevenDayPr, double marketCap, double volume, boolean starStatus,
                    boolean isTwentyFourHrPos, boolean isSevenDayPos) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.twentyFourHrPr = twentyFourHrPr;
        this.sevenDayPr = sevenDayPr;
        this.marketCap = marketCap;
        this.volume = volume;
        this.starStatus = starStatus;
        this.isTwentyFourHrPos = isTwentyFourHrPos;
        this.isSevenDayPos = isSevenDayPos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTwentyFourHrPr() {
        return twentyFourHrPr;
    }

    public void setTwentyFourHrPr(double twentyFourHrPr) {
        this.twentyFourHrPr = twentyFourHrPr;
    }

    public double getSevenDayPr() {
        return sevenDayPr;
    }

    public void setSevenDayPr(double sevenDayPr) {
        this.sevenDayPr = sevenDayPr;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public boolean isStarStatus() {
        return starStatus;
    }

    public void setStarStatus(boolean starStatus) {
        this.starStatus = starStatus;
    }

    public boolean isTwentyFourHrPos() {
        return isTwentyFourHrPos;
    }

    public void setTwentyFourHrPos(boolean twentyFourHrPos) {
        isTwentyFourHrPos = twentyFourHrPos;
    }

    public boolean isSevenDayPos() {
        return isSevenDayPos;
    }

    public void setSevenDayPos(boolean sevenDayPos) {
        isSevenDayPos = sevenDayPos;
    }
}
