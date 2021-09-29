package org.telegram.crypto.ui;

import static org.telegram.crypto.framework.utils.CommonUtilities.currencyFormat;
import static org.telegram.crypto.framework.utils.CommonUtilities.currencyLargeFormat;
import static org.telegram.crypto.framework.utils.CommonUtilities.getLayoutWithIcon;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.telegram.crypto.data.local.model.Currency;
import org.telegram.messenger.R;
import org.telegram.ui.Components.LayoutHelper;

import java.util.ArrayList;
import java.util.List;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.CurrenciesViewHolder> {

    private final List<Currency> mCurrencies = new ArrayList<>();
    private Callback mCallback;
    private boolean isDescending = false;

    public CurrenciesAdapter() {
    }

    //***Add new items to list***
    public void addItems(List<Currency> currencies) {
        this.mCurrencies.clear();
        this.mCurrencies.addAll(currencies);
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onOrderClicked();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public CurrenciesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout horizontalView = new LinearLayout(parent.getContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        horizontalView.setLayoutParams(layoutParams);
        horizontalView.setOrientation(LinearLayout.HORIZONTAL);

        return new CurrenciesViewHolder(horizontalView);
    }

    public static class CurrenciesViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rowBaseLayout;

        public CurrenciesViewHolder(View itemView) {
            super(itemView);
            rowBaseLayout = (LinearLayout) itemView;
        }
    }

    @Override
    public void onBindViewHolder(CurrenciesViewHolder holder, int position) {
        //***Add row view to populate with data***
        holder.rowBaseLayout.addView(getRow(position, holder.rowBaseLayout.getContext()),
                new LinearLayout.LayoutParams(LayoutHelper.MATCH_PARENT, 260));
    }

    @Override
    public int getItemCount() {
        return mCurrencies.size();
    }

    public List<Currency> getmCurrencies() {
        return mCurrencies;
    }

    protected View getRow(int pos, Context context) {

        LinearLayout rowLayout = new LinearLayout(context);

        rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        rowLayout.setOrientation(LinearLayout.HORIZONTAL);

        if (pos == 0) {
            setHeaderLayout(rowLayout);
        } else {
            setRowLayout(pos, rowLayout);
        }

        return rowLayout;
    }


    //***Sets Header layout***
    private void setHeaderLayout(LinearLayout headerLayout) {
        Context context = headerLayout.getContext();

        //***Create views for layout
        LinearLayout orientationLayout = new LinearLayout(context);
        ImageView imgStar = new ImageView(context);
        ImageView imgNumber = new ImageView(context);
        ImageView imgOrder = new ImageView(context);
        TextView txtName = new TextView(context);
        TextView txtPrice = new TextView(context);
        TextView txtTwentyFourHrPr = new TextView(context);
        TextView txtSevenDayPr = new TextView(context);
        TextView txtMarketCap = new TextView(context);
        TextView txtVolume = new TextView(context);

        //***Add color to text views***
        txtName.setTextColor(context.getResources().getColor(R.color.black));
        txtPrice.setTextColor(context.getResources().getColor(R.color.black));
        txtTwentyFourHrPr.setTextColor(context.getResources().getColor(R.color.black));
        txtSevenDayPr.setTextColor(context.getResources().getColor(R.color.black));
        txtMarketCap.setTextColor(context.getResources().getColor(R.color.black));
        txtVolume.setTextColor(context.getResources().getColor(R.color.black));

        imgStar.setImageDrawable(context.getResources()
                .getDrawable(R.drawable.ic_star_outline_24));
        imgNumber.setImageDrawable(context.getResources()
                .getDrawable(R.drawable.ic_numbers_24));

        setOrderIcon(imgOrder);

        View mrkCapView = getLayoutWithIcon(false, true, false,
                context.getResources().getDrawable(R.drawable.ic_info_24), txtMarketCap,
                null);

        View volView = getLayoutWithIcon(false, true, true,
                context.getResources().getDrawable(R.drawable.ic_info_24), txtVolume,
                null);

        //***Set Header star invisible***
        imgStar.setVisibility(View.INVISIBLE);

        //***Add action for clicking number/order icons in header***
        orientationLayout.setOnClickListener(v -> {
            isDescending = !isDescending;
            mCallback.onOrderClicked();
        });

        orientationLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        orientationLayout.setOrientation(LinearLayout.HORIZONTAL);

        orientationLayout.addView(imgNumber, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START | Gravity.CENTER));
        orientationLayout.addView(imgOrder, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START | Gravity.CENTER));

        //***Set text view with data***
        txtName.setText(R.string.title_name);
        txtPrice.setText(R.string.title_price);
        txtTwentyFourHrPr.setText(R.string.title_twenty_four_hr_per);
        txtSevenDayPr.setText(R.string.title_seven_day_per);
        txtMarketCap.setText(R.string.title_market_cap);
        txtVolume.setText(R.string.title_volume);

        //***Set Header cell widths***
        imgStar.setMinimumWidth(50);
        orientationLayout.setMinimumWidth(120);
        txtName.setMinWidth(400);
        txtPrice.setMinWidth(250);
        txtTwentyFourHrPr.setMinWidth(150);
        txtSevenDayPr.setMinWidth(150);
        mrkCapView.setMinimumWidth(400);
        volView.setMinimumWidth(300);

        //***Add Header views***
        headerLayout.addView(imgStar, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START, 0, 16,
                16, 16));
        headerLayout.addView(orientationLayout, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START, 16, 16,
                0, 16));
        headerLayout.addView(txtName, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START, 16,
                16, 0, 16));
        headerLayout.addView(txtPrice, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START, 16,
                16, 0, 16));
        headerLayout.addView(txtTwentyFourHrPr, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START, 16, 16,
                0, 16));
        headerLayout.addView(txtSevenDayPr, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START, 16, 16,
                0, 16));
        headerLayout.addView(mrkCapView, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START, 16, 16,
                0, 16));
        headerLayout.addView(volView, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.END, 16, 16,
                16, 16));
    }

    //***Sets Row layout***
    private void setRowLayout(int pos, LinearLayout rowLayout) {
        Context context = rowLayout.getContext();

        //***Create views for layout
        LinearLayout volumeLayout = new LinearLayout(context);
        ImageView imgStar = new ImageView(context);
        TextView txtOrderNo = new TextView(context);
        TextView txtName = new TextView(context);
        TextView txtPrice = new TextView(context);
        TextView txtTwentyFourHrPr = new TextView(context);
        TextView txtSevenDayPr = new TextView(context);
        TextView txtMarketCap = new TextView(context);
        TextView txtVolume = new TextView(context);
        TextView txtVolByCurrency = new TextView(context);

        //***Add color to text views***
        txtName.setTextColor(context.getResources().getColor(R.color.black));
        txtOrderNo.setTextColor(context.getResources().getColor(R.color.black));
        txtPrice.setTextColor(context.getResources().getColor(R.color.black));
        txtTwentyFourHrPr.setTextColor(context.getResources().getColor(R.color.black));
        txtSevenDayPr.setTextColor(context.getResources().getColor(R.color.black));
        txtMarketCap.setTextColor(context.getResources().getColor(R.color.black));
        txtVolume.setTextColor(context.getResources().getColor(R.color.black));
        setTrendColor(txtTwentyFourHrPr, mCurrencies.get(pos).isTwentyFourHrPos());
        setTrendColor(txtSevenDayPr, mCurrencies.get(pos).isSevenDayPos());

        setStarIcon(mCurrencies.get(pos).isStarStatus(), imgStar);

        //***Add action for clicking star***
        imgStar.setOnClickListener(v -> {
            mCurrencies.get(pos).setStarStatus(!mCurrencies.get(pos).isStarStatus());
            setStarIcon(mCurrencies.get(pos).isStarStatus(), imgStar);
        });

        String priceText = context.getResources().getString(R.string.sign_dollar)
                + currencyFormat(String.valueOf(mCurrencies.get(pos).getPrice()));

        String twentyFHrPr = currencyFormat(String.valueOf(mCurrencies.get(pos)
                .getTwentyFourHrPr())) + context.getResources().getString(R.string.sign_percent);

        String sevenDPr = currencyFormat(String.valueOf(mCurrencies.get(pos)
                .getSevenDayPr())) + context.getResources().getString(R.string.sign_percent);

        String mrkCap = context.getResources().getString(R.string.sign_dollar)
                + currencyLargeFormat(String.valueOf(mCurrencies.get(pos)
                .getMarketCap()));

        String vol = context.getResources().getString(R.string.sign_dollar)
                + currencyLargeFormat(String.valueOf(mCurrencies.get(pos).getVolume()));

        double volumeInCurrency = mCurrencies.get(pos).getVolume() / mCurrencies.get(pos)
                .getPrice();
        String volCurrency = currencyLargeFormat(String.valueOf(volumeInCurrency));
        String volInCurrency = volCurrency + " " + mCurrencies.get(pos).getSymbol();

        //***Set text view with data***
        txtOrderNo.setText(getOrderNo(pos));
        txtName.setText(mCurrencies.get(pos).getName());
        txtPrice.setText(priceText);
        txtTwentyFourHrPr.setText(twentyFHrPr);
        txtSevenDayPr.setText(sevenDPr);
        txtMarketCap.setText(mrkCap);
        txtVolume.setText(vol);
        txtVolByCurrency.setText(volInCurrency);

        volumeLayout.setOrientation(LinearLayout.VERTICAL);
        volumeLayout.addView(txtVolume, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.END));
        volumeLayout.addView(txtVolByCurrency, LayoutHelper
                .createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT,
                        Gravity.END));
        View viewName = getLayoutWithIcon(true, false, false, null,
                txtName, mCurrencies.get(pos).getSymbol());

        //***Set Row cell widths***
        imgStar.setMinimumWidth(50);
        txtOrderNo.setMinimumWidth(120);
        viewName.setMinimumWidth(400);
        txtPrice.setMinWidth(250);
        txtTwentyFourHrPr.setMinWidth(150);
        txtSevenDayPr.setMinWidth(150);
        txtMarketCap.setMinWidth(400);
        volumeLayout.setMinimumWidth(300);

        //***Add Row views***
        rowLayout.addView(imgStar, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START,
                16, 16, 16, 16));
        rowLayout.addView(txtOrderNo, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL,
                14, 16, 0, 16));
        rowLayout.addView(viewName, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START,
                0, 16, 0, 16));
        rowLayout.addView(txtPrice, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START, 16,
                16, 0, 16));
        rowLayout.addView(txtTwentyFourHrPr, LayoutHelper.createLinear(
                LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.START,
                16, 16, 0, 16));
        rowLayout.addView(txtSevenDayPr, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START, 16, 16,
                0, 16));
        rowLayout.addView(txtMarketCap, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT, Gravity.START, 16, 16,
                0, 16));
        rowLayout.addView(volumeLayout, LayoutHelper.createLinear(
                LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.END,
                16, 16, 24, 16));
    }

    //***Sets star icon***
    private void setStarIcon(boolean isStared, ImageView starImage) {
        Context context = starImage.getContext();
        if (isStared) {
            starImage.setImageDrawable(context.getResources()
                    .getDrawable(R.drawable.ic_star_rate_24));
        } else {
            starImage.setImageDrawable(context.getResources()
                    .getDrawable(R.drawable.ic_star_outline_24));
        }
    }

    //***Sets order direction icon***
    private void setOrderIcon(ImageView orderImage) {
        Context context = orderImage.getContext();
        if (isDescending) {
            orderImage.setImageDrawable(context.getResources()
                    .getDrawable(R.drawable.ic_arrow_drop_down_24));
        } else {
            orderImage.setImageDrawable(context.getResources()
                    .getDrawable(R.drawable.ic_arrow_drop_up_24));
        }
    }

    //***Sets red or green based on currency trend in a given time***
    private void setTrendColor(TextView view, boolean isTrendPositive) {
        Context context = view.getContext();
        if (isTrendPositive) {
            view.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            view.setTextColor(context.getResources().getColor(R.color.red));
        }
    }

    //***Returns correct position number***
    private String getOrderNo(int pos) {
        int numberPosition = pos;

        if (isDescending) {
            numberPosition = mCurrencies.size() - pos;
        }

        return String.valueOf(numberPosition);
    }
}