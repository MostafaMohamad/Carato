package project.aut.carato;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefs {
    private static SharedPrefs sharedPrefs = new SharedPrefs();
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;

    private final static String USER_ID = "UserId";

    public static SharedPrefs getInstance(Context context){
        if (mSharedPreferences == null){
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            editor = mSharedPreferences.edit();
        }
        return sharedPrefs;
    }

    public void setUserId(String newValue){
        editor.putString(USER_ID,newValue);
        editor.commit();
    }

    public String getUserId(){
        return mSharedPreferences.getString(USER_ID,null);
    }

}
