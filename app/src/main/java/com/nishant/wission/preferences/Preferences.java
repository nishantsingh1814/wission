package com.nishant.wission.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class Preferences {
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String PROFILE_PIC = "profile_pic";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String GENDER = "gender";
    private static final String LOGGED_IN = "logged_in";

    private static SharedPreferences getUserPreferences(@NonNull final Context context) {
        return context.getSharedPreferences("com.nishant.wission.user", Context.MODE_PRIVATE);
    }

    public static void saveLoggedInLang(@NonNull final Context context, @NonNull final boolean name) {
        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(LOGGED_IN, name);
            editor.apply();
        }
    }

    public static boolean getLoggedInLang(@NonNull final Context context) {

        final SharedPreferences preferences = getUserPreferences(context);
        return preferences != null && preferences.getBoolean(LOGGED_IN, false);
    }

    public static void saveNameLang(@NonNull final Context context, @NonNull final String name) {
        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString(NAME, name);
            editor.apply();
        }
    }

    public static String getNameLang(@NonNull final Context context) {

        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            return preferences.getString(NAME, null);
        }
        return null;
    }

    public static void saveAgeLang(@NonNull final Context context, @NonNull final String age) {
        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString(AGE, age);
            editor.apply();
        }
    }

    public static String getAgeLang(@NonNull final Context context) {

        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            return preferences.getString(AGE, null);
        }
        return null;
    }

    public static void saveGenderLang(@NonNull final Context context, @NonNull final String gender) {
        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString(GENDER, gender);
            editor.apply();
        }
    }

    public static String getGenderLang(@NonNull final Context context) {

        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            return preferences.getString(GENDER, null);
        }
        return null;
    }

    public static void saveEmailLang(@NonNull final Context context, @NonNull final String name) {
        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString(EMAIL, name);
            editor.apply();
        }
    }

    public static String getEmailLang(@NonNull final Context context) {

        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            return preferences.getString(EMAIL, null);
        }
        return null;
    }

    public static void savePasswordLang(@NonNull final Context context, @NonNull final String password) {
        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString(PASSWORD, password);
            editor.apply();
        }
    }

    public static String getPasswordLang(@NonNull final Context context) {

        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            return preferences.getString(PASSWORD, null);
        }
        return null;
    }

    public static void saveProfilePicLang(@NonNull final Context context, @NonNull final String profile_pic) {
        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString(PROFILE_PIC, profile_pic);
            editor.apply();
        }
    }

    public static String getProfilePicLang(@NonNull final Context context) {

        final SharedPreferences preferences = getUserPreferences(context);
        if (preferences != null) {
            return preferences.getString(PROFILE_PIC, null);
        }
        return null;
    }
}

