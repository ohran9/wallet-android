package org.telegram.crypto.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.telegram.crypto.data.local.model.Currency;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrenciesFragment extends BaseFragment implements CurrenciesNavigator,
        CurrenciesAdapter.Callback {

    private Context mContext;
    private CurrenciesViewModel mViewModel;
    private LinearLayout mParentLayout;
    private RecyclerView mRecyclerView;
    private CurrenciesAdapter mAdapter;

    @Override
    public boolean onFragmentCreate() {
        if (mContext != null) {
            mViewModel = new CurrenciesViewModel(mContext);
            mViewModel.setNavigator(this);
        }
        return super.onFragmentCreate();
    }

    @Override
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
    }

    public CurrenciesFragment() {
        super();
    }

    @Override
    public View createView(Context context) {
        this.mContext = context;

        if (mViewModel == null) {
            mViewModel = new CurrenciesViewModel(context);
            mViewModel.setNavigator(this);
        }
        setup();
        return fragmentView;
    }


    //***Set up initial views***
    private void setup() {
        mParentLayout = new LinearLayout(mContext);
        TextView titleText = new TextView(mContext);

        mParentLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        mParentLayout.setOrientation(LinearLayout.VERTICAL);

        actionBar.setBackground(null);
        actionBar.setTitleColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        actionBar.setItemsColor(Theme.getColor(
                Theme.key_windowBackgroundWhiteGrayText2), false);
        actionBar.setItemsBackgroundColor(Theme.getColor(
                Theme.key_actionBarWhiteSelector), false);
        actionBar.setCastShadows(false);
        actionBar.setAddToContainer(false);
        if (!AndroidUtilities.isTablet()) {
            actionBar.showActionModeTop();
        }
        actionBar.addView(titleText, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,
                LayoutHelper.WRAP_CONTENT, Gravity.CENTER | Gravity.LEFT, 16,
                16, 0, 0));

        titleText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText2));
        titleText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        titleText.setText(R.string.title_currency_listings);

        mParentLayout.addView(actionBar, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,
                AndroidUtilities.statusBarHeight, Gravity.CENTER | Gravity.LEFT));
        setRecyclerView();

        //***Make Api call to fetch listing data***
        mViewModel.loadCurrencies();

        fragmentView = mParentLayout;
        fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
    }

    //***Initialize RecyclerView and Adapter***
    private void setRecyclerView() {
        mAdapter = new CurrenciesAdapter();
        mAdapter.setCallback(this);
        mRecyclerView = new RecyclerView(mContext);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }

    //***Order listing when header order icon clicked***
    @Override
    public void onOrderClicked() {
        List<Currency> oldOrderList = mAdapter.getmCurrencies();
        oldOrderList.remove(0);
        Collections.reverse(oldOrderList);
        List<Currency> newOrderList = new ArrayList<>();
        newOrderList.add(new Currency());
        newOrderList.addAll(oldOrderList);
        mAdapter.addItems(newOrderList);
        mRecyclerView.setAdapter(mAdapter);
    }

    //***Set api fetched data to adapter and then add to fragment view***
    @Override
    public void setCurrencies(List<Currency> currencies) {
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(mContext);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        horizontalScrollView.setLayoutParams(layoutParams);
        horizontalScrollView.setFillViewport(true);

        //***Add fetched list to adapter***
        mAdapter.addItems(currencies);

        //***Add RecyclerView to layout***
        horizontalScrollView.addView(mRecyclerView, LayoutHelper
                .createLinear(LayoutHelper.WRAP_CONTENT,
                        LayoutHelper.WRAP_CONTENT, 16, 16,
                        16, 16));

        mParentLayout.addView(horizontalScrollView);

        //***Dispose disposable used to make api call***
        mViewModel.disposeDisposable();
    }

    //***Toast to show error message***
    @Override
    public void onError(String errorMessage, int messageStringId) {
        if (errorMessage != null)
            Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, mContext.getString(messageStringId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mRecyclerView.setAdapter(mAdapter);
    }
}
