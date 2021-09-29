package org.telegram.crypto.ui;

import android.content.Context;

import org.telegram.crypto.data.AppDataManager;
import org.telegram.crypto.data.Converters;
import org.telegram.crypto.data.local.model.Currency;
import org.telegram.crypto.data.remote.AppApiHelper;
import org.telegram.crypto.framework.base.BaseViewModel;
import org.telegram.crypto.framework.utils.rx.AppSchedulerProvider;
import org.telegram.crypto.framework.utils.rx.SchedulerProvider;
import org.telegram.messenger.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class CurrenciesViewModel extends BaseViewModel<CurrenciesNavigator> {

    private final SchedulerProvider mSchedulerProvider;
    private final AppDataManager mDataManager;
    private Disposable mDisposable;

    public CurrenciesViewModel(Context context) {
        AppApiHelper mAppApiHelper = new AppApiHelper(context);
        mSchedulerProvider = new AppSchedulerProvider();
        mDataManager = new AppDataManager(mAppApiHelper);
    }

    //***For api call to load data***
    protected void loadCurrencies() {
        mDisposable = mDataManager.getListings()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(listingsResponse -> {

                    List<Currency> currenciesLoad = Converters.
                            currencyResponsesToCurrencies(listingsResponse.getData());

                    if (currenciesLoad != null) {

                        List<Currency> currenciesFetched = new ArrayList<>();

                        currenciesFetched.add(new Currency());
                        currenciesFetched.addAll(currenciesLoad);

                        //***Send fetched data to show in view***
                        getNavigator().setCurrencies(currenciesFetched);
                    } else if (listingsResponse.getStatus().getErrorMessage() != null) {
                        getNavigator().onError(listingsResponse.getStatus().getErrorMessage(),
                                0);
                    } else {
                        getNavigator().onError(null,
                                R.string.error_message_load);
                    }
                }, throwable -> {
                    if (throwable != null && throwable.getMessage() != null) {
                        getNavigator().onError(throwable.getMessage(), 0);
                    } else if (throwable != null) {
                        getNavigator().onError(throwable.toString(), 0);
                    } else {
                        getNavigator().onError(null,
                                R.string.error_message_load);
                    }
                });
    }

    //***Dispose api call***
    protected void disposeDisposable() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
