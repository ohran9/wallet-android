package org.telegram.crypto.framework.utils;

import static org.telegram.crypto.framework.utils.AppConstants.BTC;
import static org.telegram.crypto.framework.utils.AppConstants.DOGE;
import static org.telegram.crypto.framework.utils.AppConstants.ETH;
import static org.telegram.crypto.framework.utils.AppConstants.USDC;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.telegram.messenger.R;
import org.telegram.ui.Components.LayoutHelper;

import java.text.DecimalFormat;

public final class CommonUtilities {

    public static String checkString(String value) {
        String checkedValue = "";
        if (!TextUtils.isEmpty(value)) {
            checkedValue = value;
        }
        return checkedValue;
    }

    //***Returns string in currency format, 1,000.00***
    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }

    //***Returns large string in currency format, 10,000,000,000***
    public static String currencyLargeFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("##,###,###,###");
        return formatter.format(Double.parseDouble(amount));
    }

    //***Returns view with icon appended at end or front****
    public static View getLayoutWithIcon(boolean isName, boolean isIconEnd, boolean isVol, Drawable drawable,
                                         View view, String symbol) {
        LinearLayout linearLayout = new LinearLayout(view.getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        ImageView icon = new ImageView(view.getContext());

        if (isName) {
            TextView symbolText = new TextView(view.getContext());
            symbolText.setText(symbol);
            linearLayout = getLayoutWithNameIcon(symbol, icon, linearLayout);
            linearLayout.addView(view, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START));
            linearLayout.addView(symbolText, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START,
                    4, 0, 0, 0));
        } else if (isIconEnd && isVol) {
            icon.setImageDrawable(drawable);
            linearLayout.setGravity(Gravity.CENTER| Gravity.END);
            linearLayout.addView(view, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.END));
            linearLayout.addView(icon, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.END));
        } else if (isIconEnd) {
            icon.setImageDrawable(drawable);
            linearLayout.addView(view, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START));
            linearLayout.addView(icon, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START));
        } else if (drawable != null) {
            icon.setImageDrawable(drawable);
            linearLayout.addView(icon, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START));
            linearLayout.addView(view, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START));
        } else {
            linearLayout.addView(view, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START));
        }

        return linearLayout;
    }

    //***Returns currency name with currency icon appended at front, if icon is available***
    public static LinearLayout getLayoutWithNameIcon(String currencySymbol, ImageView icon,
                                                     LinearLayout linearLayout) {
        if (currencySymbol.equalsIgnoreCase(BTC)) {
            icon.setImageDrawable(icon.getContext().getResources()
                    .getDrawable(R.drawable.ic_bitcoin));
            linearLayout.addView(icon, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START));
        } else if (currencySymbol.equalsIgnoreCase(ETH)) {
            icon.setImageDrawable(icon.getContext().getResources()
                    .getDrawable(R.drawable.ic_ethereum));
            linearLayout.addView(icon, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START));
        } else if (currencySymbol.equalsIgnoreCase(USDC)) {
            icon.setImageDrawable(icon.getContext().getResources()
                    .getDrawable(R.drawable.ic_usd_coin));
            linearLayout.addView(icon, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START));
        } else if (currencySymbol.equalsIgnoreCase(DOGE)) {
            icon.setImageDrawable(icon.getContext().getResources()
                    .getDrawable(R.drawable.ic_doge));
            linearLayout.addView(icon, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,
                    LayoutHelper.WRAP_CONTENT, Gravity.START));
        }
        return linearLayout;
    }
}
