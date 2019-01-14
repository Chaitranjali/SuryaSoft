package com.hello.assignment.uitility;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringDef;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by loki on 19/11/15.
 */
public class AppPreferences {

    Context mContext;
    public static SharedPreferences sharedPreference;
    public static final String MSG_ILLEGAL_ARGS = "The argument should be the application context!";
    public static final String VALUE_NOT_SET = null;
    private static Gson gson;
    private static Gson GSON = new Gson();
    public static SharedPreferences.Editor editor;


    @StringDef
    public @interface Key {
        // MemberList at the name of pojo classes you want to store and user same key.
        String LOGIN_DATA = "LOGIN_DATA";
        String PROFILE = "PROFILE";
    }


    /**
     * This method should be called at least once to set the SharedPreferences,
     * preferably at application launch. Once called, it need not be called
     * again by subsequent Activities
     *
     * @param applicationContext : Must be the application context and not an Activity context
     */
    public static void initPreferences(Context applicationContext) {
        if (applicationContext instanceof Activity) {
            throw new IllegalArgumentException(MSG_ILLEGAL_ARGS);
        } else if (sharedPreference == null) {
            sharedPreference = applicationContext.getSharedPreferences(
                    "coachData", Context.MODE_PRIVATE);

            editor = sharedPreference.edit();

        }
    }

    /**
     * Gets the application preference identified by the argument key. Returns
     * null if the specified preference does not exist
     *
     * @param preference
     * @return Value
     */
    public synchronized static String getPreference(String preference) {
        if (sharedPreference == null) {
            return null;
        }
        return sharedPreference.getString(preference, VALUE_NOT_SET);
    }


    public AppPreferences(Context context) {
        mContext = context;
    }

    public synchronized static String getPreferenceString(String preference,
                                                          String defaultVal) {
        if (sharedPreference == null) {
            return null;
        }
        return sharedPreference.getString(preference, defaultVal);
    }

    /**
     * Saves the specified key value pair in the application preferences
     *
     * @param preference
     * @param value
     */
    public synchronized static void setPreference(String preference, String value) {
        sharedPreference.edit().putString(preference, value).commit();

    }

    public synchronized static void setBoolPreference(String preference, boolean value) {
        sharedPreference.edit().putBoolean(preference, value).commit();

    }

    public synchronized static void setIntPreference(String preference, int value) {
        sharedPreference.edit().putInt(preference, value).commit();
    }

    public static int getIntPreference(String preference) {
        if (sharedPreference == null) {
            return -1;
        }
        return sharedPreference.getInt(preference, -1);

    }

    public synchronized static void setLongPreference(String preference, Long value) {
        sharedPreference.edit().putLong(preference, value).commit();

    }

    public static long getLongPreference(String preference) {
        if (sharedPreference == null) {
            return -1;
        }
        return sharedPreference.getLong(preference, 0);

    }

    public static boolean getBoolPreference(String preference) {
        if (sharedPreference == null) {
            return false;
        }
        return sharedPreference.getBoolean(preference, false);

    }


    /**
     * Returns a Map containing all the Applications preferences
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getAllPreferences() {
        return (Map<String, String>) sharedPreference.getAll();
    }

    /**
     * Multiple preferences can be saved by passing the key value pairs in a Map
     *
     * @param preferenceMap Map containing Keys and Values to be stored.
     */
    public synchronized static void setPreferences(Map<String, String> preferenceMap) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        Set<String> preferenceKeySet = preferenceMap.keySet();
        for (Iterator<String> index = preferenceKeySet.iterator(); index.hasNext(); ) {
            String preference = index.next();
            String value = preferenceMap.get(preference);
            editor.putString(preference, value);
        }
        editor.commit();
    }

    public static void removePreferences(Map<String, String> preferenceMap) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        Set<String> preferenceKeySet = preferenceMap.keySet();
        for (Iterator<String> index = preferenceKeySet.iterator(); index.hasNext(); ) {
            String preference = index.next();
            editor.remove(preference);
        }
        editor.commit();
    }

    public synchronized static void removePreference(String key) {
        if (sharedPreference.contains(key)) {
            sharedPreference.edit().remove(key).commit();
        }
    }

    public static void removeAllPreferences() {
        sharedPreference.edit().clear().commit();
    }

    public synchronized static boolean prefContains(String preferences) {
        return sharedPreference.contains(preferences);
    }

    public String getString(String key, String defaultValue) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        return pref.getString(key, defaultValue);
    }

    public void setString(String key, String value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void setDouble(String key, double value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.commit();
    }


    public double getDouble(String key, float defaultValue) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        return Double.longBitsToDouble(pref.getLong(key, Double.doubleToLongBits(defaultValue)));
    }


    public synchronized static void putProfileObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("key is empty or null");
        }

        editor = sharedPreference.edit();
        editor.putString(key, GSON.toJson(object));
        editor.commit();
    }


    public synchronized static <T> T getProfileObject(String key, Class<T> a) {
        if (sharedPreference == null) {
            return null;
        }
        String gson = sharedPreference.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object storaged with key " + key + " is instanceof other class");
            }
        }
    }

    public synchronized static void putChurchProfileObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("key is empty or null");
        }

        editor = sharedPreference.edit();
        editor.putString(key, GSON.toJson(object));
        editor.commit();
    }


    public synchronized static <T> T getChurchProfileObject(String key, Class<T> a) {
        if (sharedPreference == null) {
            return null;
        }
        String gson = sharedPreference.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object storaged with key " + key + " is instanceof other class");
            }
        }
    }


    public synchronized static void putLoginObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("key is empty or null");
        }

        editor = sharedPreference.edit();
        editor.putString(key, GSON.toJson(object));
        editor.commit();
    }


    public synchronized static <T> T getLoginObject(String key, Class<T> a) {
        if (sharedPreference == null) {
            return null;
        }
        String gson = sharedPreference.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object storaged with key " + key + " is instanceof other class");
            }
        }
    }

    public synchronized static void putPermissionData(String key, Object object) {
        if (object == null) {
            return;
        }

        if (key.equals("") || key == null) {
            return;
        }

        editor = sharedPreference.edit();
        editor.putString(key, GSON.toJson(object));
        editor.commit();
    }


    public synchronized static <T> T getPermissionData(String key, Class<T> a) {
        if (sharedPreference == null) {
            return null;
        }
        String gson = sharedPreference.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                return null;
            }
        }
    }


    // Store MemberList call
    public <T> void putList(String key, List<T> list) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(key, gson.toJson(list));
        editor.apply();
    }

    //  Get MemberList call
    public <T> List<T> getList(String key, Class<T> clazz) {
        Type typeOfT = TypeToken.get(clazz).getType();
        return gson.fromJson(getString(key, null), typeOfT);
    }

    //   Store Array call
    public <T> void putArray(String key, T[] arrays) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(key, gson.toJson(arrays));
        editor.apply();
    }

    // Get Array call
    public <T> T[] getArray(String key, Class<T[]> clazz) {
        return gson.fromJson(getString(key, null), clazz);
    }

    public synchronized static void saveObject(String key, Object value) {

        SharedPreferences.Editor editor = sharedPreference.edit();
        String json = new Gson().toJson(value);
        editor.putString(key, json);
        editor.commit();

    }


    public synchronized static void clearPreference() {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.clear();
        editor.apply();
    }


}
