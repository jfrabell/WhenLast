package com.bunnyhutt.whenlast;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jeff on 6/3/16.
 * Contains
 *  username
 *  time
 *  lat
 *  lon
 *  privacy
 *  overnight
 */

public class UserPrefsClass {
    private static UserPrefsClass yourPreference;
    private SharedPreferences sharedPreferences;

    public static UserPrefsClass getInstance(Context context) {
        if (yourPreference == null) {
            yourPreference = new UserPrefsClass(context);
        }
        return yourPreference;
    }

    public UserPrefsClass(Context context) {
        sharedPreferences = context.getSharedPreferences("LocalLandingsPreference",Context.MODE_PRIVATE);
    }

    public void saveData(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public String getData(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }
}