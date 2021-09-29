package org.telegram.crypto.data;

import org.telegram.crypto.data.local.model.Currency;
import org.telegram.crypto.data.remote.model.CurrencyResponse;
import org.telegram.crypto.data.remote.model.Usd;

import java.util.ArrayList;
import java.util.List;

public class Converters {

    public static List<Currency> currencyResponsesToCurrencies(List<CurrencyResponse> currencyResponses) {

        if (currencyResponses == null)
            return null;

        List<Currency> currencies = new ArrayList<>();

        for (CurrencyResponse currencyResponse : currencyResponses) {
            String name = currencyResponse.getName();
            String symbol = currencyResponse.getSymbol();
            Usd quoteUsd = currencyResponse.getQuote().getUsd();
            double price = quoteUsd.getPrice();
            double twentyFourHrPr = quoteUsd.getPercentChange24h();
            double sevenDayPr = quoteUsd.getPercentChange7d();
            double marketCap = quoteUsd.getMarketCap();
            double volume = quoteUsd.getVolume24h();
            boolean starStatus = false;
            boolean isTwentyFourHrPos = false;
            boolean isSevenDayPos = false;

            if (currencyResponse.getQuote().getUsd().getPercentChange24h() >= 0) {
                isTwentyFourHrPos = true;
            }

            if (currencyResponse.getQuote().getUsd().getPercentChange7d() >= 0) {
                isSevenDayPos = true;
            }

            Currency currency = new Currency(name, symbol, price, twentyFourHrPr, sevenDayPr,
                    marketCap, volume, starStatus, isTwentyFourHrPos, isSevenDayPos);
            currencies.add(currency);
        }

        return currencies;
    }
}
