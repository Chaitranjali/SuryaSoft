package com.hello.assignment.uitility;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hello.assignment.BuildConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonMethods {
    public static boolean isValidEmail(String target) {

        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }

    public static void showLog(String tag, String message) {

        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }

    }

    public static void showToast(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

    public static void showSnackbar(View layout, String msg) {

        final Snackbar snackbar = Snackbar.make(layout, "" + msg, Snackbar.LENGTH_LONG);

       /* snackbar.setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });*/

        View view = snackbar.getView();
        view.setPadding(0 ,0 , 0, 0);

        snackbar.show();

    }


    public static boolean isNetworkAvailable(Context context) {

        try {

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {

                return true;

            }
        } catch (Exception e) {

            e.printStackTrace();

        }
        return false;

    }

    public static void hideKeyboard(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {

            view = new View(activity);

        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }

}
