package org.telegram.crypto.ui;

import org.telegram.crypto.data.local.model.Currency;

import java.util.List;

public interface CurrenciesNavigator {
    void setCurrencies(List<Currency> currencies);

    void onError(String errorMessage, int messageStringId);
}
