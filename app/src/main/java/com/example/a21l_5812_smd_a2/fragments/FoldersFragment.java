package com.example.a21l_5812_smd_a2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.a21l_5812_smd_a2.R;
import com.example.a21l_5812_smd_a2.adapters.FolderAdapter;
import com.example.a21l_5812_smd_a2.Models.Folder;
import com.example.a21l_5812_smd_a2.Models.Subject;
import com.example.a21l_5812_smd_a2.utils.SharedPrefManager;
import java.util.ArrayList;

public class FoldersFragment extends Fragment implements FolderAdapter.OnFolderClickListener {
    
    private ListView listView;
    private TextView txtSubjectName;
    private FolderAdapter adapter;
    private Subject currentSubject;
    private SharedPrefManager prefManager;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folders, container, false);
        
        prefManager = new SharedPrefManager(requireContext());
        initViews(view);
        
        // Get subject from arguments
        if (getArguments() != null) {
            currentSubject = (Subject) getArguments().getSerializable("subject");
            if (currentSubject != null) {
                txtSubjectName.setText(currentSubject.getName() + " - Folders");
                setupListView();
            }
        }
        
        return view;
    }
    
    private void initViews(View view) {
        listView = view.findViewById(R.id.listView);
        txtSubjectName = view.findViewById(R.id.txtSubjectName);
    }
    
    private void setupListView() {
        adapter = new FolderAdapter(requireContext(), currentSubject.getFolders(), this);
        listView.setAdapter(adapter);
    }
    
    @Override
    public void onFolderClick(Folder folder) {
        // Navigate to NotesImagesFragment
        NotesImagesFragment notesImagesFragment = new NotesImagesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("folder", folder);
        bundle.putSerializable("subject", currentSubject);
        notesImagesFragment.setArguments(bundle);
        
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, notesImagesFragment)
                .addToBackStack(null)
                .commit();
    }
    
    @Override
    public void onFolderDelete(Folder folder) {
        // Remove folder from list
        currentSubject.getFolders().remove(folder);
        
        // Update folder count
        currentSubject.setFolderCount(currentSubject.getFolders().size());
        
        // Update adapter
        adapter.notifyDataSetChanged();
        
        // Save to SharedPreferences
        ArrayList<Subject> subjects = prefManager.loadSubjects();
        if (subjects != null) {
            for (Subject s : subjects) {
                if (s.getName().equals(currentSubject.getName())) {
                    s.setFolders(currentSubject.getFolders());
                    s.setFolderCount(currentSubject.getFolders().size());
                    break;
                }
            }
            prefManager.saveSubjects(subjects);
        }
    }
}