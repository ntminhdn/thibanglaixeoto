package com.example.minhnt.thibanglaixeoto.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.example.minhnt.thibanglaixeoto.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by minh.nt on 8/9/2017.
 */

public class Util {
    public static String loadFromAssets(Context context, String name) {
        try {
            StringBuilder buf = new StringBuilder();
            InputStream json = context.getAssets().open(name);
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
            return buf.toString();
        } catch (IOException ex) {
            return null;
        }
    }

    public static void showMessage(@NonNull Context context, String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context).setTitle(R.string.app_name).setMessage(message).setPositiveButton(android.R.string.ok, listener).show();
    }

    public static void showMessage(@NonNull Context context, String message, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener listener2) {
        new AlertDialog.Builder(context).setTitle(R.string.app_name).setMessage(message).setPositiveButton(android.R.string.ok, listener).setNegativeButton("Không nhắc lại", listener2).show();
    }
}
