package com.hindicoder.retrofitdemoinjava.shasredpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

import com.hindicoder.retrofitdemoinjava.apiresponse.User;

import static com.hindicoder.retrofitdemoinjava.util.Constants.EMAIL;
import static com.hindicoder.retrofitdemoinjava.util.Constants.ID;
import static com.hindicoder.retrofitdemoinjava.util.Constants.LOGGED;
import static com.hindicoder.retrofitdemoinjava.util.Constants.USERNAME;

public class SharedPrefManager {
    private String SHARED_PREF_NAME = "retrofitDemo";
    private SharedPreferences sharedPreferences;
    private Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(User user)
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt(ID,user.getId());
        editor.putString(USERNAME,user.getUsername());
        editor.putString(EMAIL,user.getEmail());
        editor.putBoolean(LOGGED,true);
        editor.apply();
    }

    public boolean isLoggedIn()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(LOGGED,false);
    }

    public User getUser()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(sharedPreferences.getInt(ID,-1),
                        sharedPreferences.getString(USERNAME,null),
                        sharedPreferences.getString(EMAIL,null));
    }

    public void logout()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
