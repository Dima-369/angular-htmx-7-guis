package com.seven.guis.springboot;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TimerHelper {

    /**
     * @return a string like "5.0s" or "4.9s" with a dot as the decimal separator.
     */
    public static String formatElapsedMs(int elapsedMs) {
        double elapsedSeconds = (double) elapsedMs / 1000;
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.0s", otherSymbols);
        return df.format(elapsedSeconds);
    }
}
