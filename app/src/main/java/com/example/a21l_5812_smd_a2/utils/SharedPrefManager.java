package com.example.a21l_5812_smd_a2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.a21l_5812_smd_a2.Models.Subject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPrefManager {

    private static final String PREF_NAME = "StudyHubPrefs";
    private static final String KEY_SUBJECTS = "subjects";
    private static final String KEY_DARK_MODE = "dark_mode";
    private static final String KEY_SHOW_PREVIEW = "show_preview";
    private static final String KEY_LAST_SUBJECT = "last_subject";


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;


    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }


    // Save subjects
    public void saveSubjects(ArrayList<Subject> subjects) {
        String json = gson.toJson(subjects);
        editor.putString(KEY_SUBJECTS, json);
        editor.apply();
    }

    // Load subjects
    public ArrayList<Subject> loadSubjects() {
        String json = sharedPreferences.getString(KEY_SUBJECTS, null);
        Type type = new TypeToken<ArrayList<Subject>>(){}.getType();
        return json == null ? null : gson.fromJson(json, type);
    }

    // Save dark mode state
    public void setDarkMode(boolean enabled) {
        editor.putBoolean(KEY_DARK_MODE, enabled);
        editor.apply();
    }

    // Get dark mode state
    public boolean isDarkMode() {
        return sharedPreferences.getBoolean(KEY_DARK_MODE, false);
    }

    // Save show preview state
    public void setShowPreview(boolean enabled) {
        editor.putBoolean(KEY_SHOW_PREVIEW, enabled);
        editor.apply();
    }

    // Get show preview state
    public boolean isShowPreview() {
        return sharedPreferences.getBoolean(KEY_SHOW_PREVIEW, true);
    }

    // Save last opened subject
    public void setLastSubject(String subjectName) {
        editor.putString(KEY_LAST_SUBJECT, subjectName);
        editor.apply();
    }

    // Get last opened subject
    public String getLastSubject() {
        return sharedPreferences.getString(KEY_LAST_SUBJECT, "");
    }

    // Clear all data
    public void clearAllData() {
        editor.clear();
        editor.apply();
    }

}
