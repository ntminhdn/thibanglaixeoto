package com.example.minhnt.thibanglaixeoto.util;

import android.content.Context;

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
}
