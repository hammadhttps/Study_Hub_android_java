package com.example.a21l_5812_smd_a2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a21l_5812_smd_a2.R;
import com.example.a21l_5812_smd_a2.adapters.SubjectAdapter;
import com.example.a21l_5812_smd_a2.Models.Subject;
import com.example.a21l_5812_smd_a2.Models.Folder;
import com.example.a21l_5812_smd_a2.utils.SharedPrefManager;
import java.util.ArrayList;

public class SubjectsFragment extends Fragment implements SubjectAdapter.OnSubjectClickListener {
    
    private RecyclerView recyclerView;
    private SubjectAdapter adapter;
    private ArrayList<Subject> subjectList;
    private SharedPrefManager prefManager;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);
        
        prefManager = new SharedPrefManager(requireContext());
        initViews(view);
        loadSubjects();
        setupRecyclerView();
        
        return view;
    }
    
    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }
    
    private void loadSubjects() {
        // Try to load from SharedPreferences first
        subjectList = prefManager.loadSubjects();
        
        // If no data exists, create sample data
        if (subjectList == null || subjectList.isEmpty()) {
            subjectList = new ArrayList<>();
            subjectList.add(new Subject("Data Structures", 12, R.drawable.ic_dsa));
            subjectList.add(new Subject("Operating Systems", 8, R.drawable.ic_dsa)); // Using available ic_dsa
            subjectList.add(new Subject("Artificial Intelligence", 15, R.drawable.ic_dsa));
            subjectList.add(new Subject("Software for Mobile Devices", 10, R.drawable.ic_dsa));
            
            // Add sample folders for each subject
            addSampleFolders();
            
            // Save to SharedPreferences
            prefManager.saveSubjects(subjectList);
        }
    }
    
    private void addSampleFolders() {
        for (Subject subject : subjectList) {
            subject.getFolders().add(new Folder("Lectures", 24, R.drawable.ic_folder));
            subject.getFolders().add(new Folder("Assignments", 15, R.drawable.ic_folder));
            subject.getFolders().add(new Folder("Quiz Preparation", 32, R.drawable.ic_folder));
            subject.getFolders().add(new Folder("Lab Work", 18, R.drawable.ic_folder));
        }
    }
    
    private void setupRecyclerView() {
        adapter = new SubjectAdapter(subjectList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }
    
    @Override
    public void onSubjectClick(Subject subject) {
        // Save last opened subject
        prefManager.setLastSubject(subject.getName());
        
        // Navigate to FoldersFragment
        FoldersFragment foldersFragment = new FoldersFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("subject", subject);
        foldersFragment.setArguments(bundle);
        
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, foldersFragment)
                .addToBackStack(null)
                .commit();
    }
}