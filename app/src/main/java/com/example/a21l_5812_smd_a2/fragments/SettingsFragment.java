package com.example.a21l_5812_smd_a2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.example.a21l_5812_smd_a2.R;
import com.example.a21l_5812_smd_a2.Models.Subject;
import com.example.a21l_5812_smd_a2.Models.Folder;
import com.example.a21l_5812_smd_a2.utils.SharedPrefManager;
import java.util.ArrayList;

public class SettingsFragment extends Fragment {
    
    private Switch switchDarkMode, switchShowPreview;
    private Button btnClearNotes, btnResetData;
    private SharedPrefManager prefManager;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        
        prefManager = new SharedPrefManager(requireContext());
        initViews(view);
        loadSettings();
        setupListeners();
        
        return view;
    }
    
    private void initViews(View view) {
        switchDarkMode = view.findViewById(R.id.switchDarkMode);
        switchShowPreview = view.findViewById(R.id.switchShowPreview);
        btnClearNotes = view.findViewById(R.id.btnClearNotes);
        btnResetData = view.findViewById(R.id.btnResetData);
    }
    
    private void loadSettings() {
        switchDarkMode.setChecked(prefManager.isDarkMode());
        switchShowPreview.setChecked(prefManager.isShowPreview());
    }
    
    private void setupListeners() {
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefManager.setDarkMode(isChecked);
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        
        switchShowPreview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefManager.setShowPreview(isChecked);
                Toast.makeText(getContext(), "Image preview setting saved", Toast.LENGTH_SHORT).show();
            }
        });
        
        btnClearNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all images from all folders
                ArrayList<Subject> subjects = prefManager.loadSubjects();
                if (subjects != null) {
                    for (Subject subject : subjects) {
                        for (Folder folder : subject.getFolders()) {
                            folder.getImages().clear();
                            folder.setImageCount(0);
                        }
                    }
                    prefManager.saveSubjects(subjects);
                }
                Toast.makeText(getContext(), "All notes cleared", Toast.LENGTH_SHORT).show();
            }
        });
        
        btnResetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.clearAllData();
                Toast.makeText(getContext(), "All data reset", Toast.LENGTH_SHORT).show();
                requireActivity().recreate();
            }
        });
    }
}