package com.example.a21l_5812_smd_a2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a21l_5812_smd_a2.R;
import com.example.a21l_5812_smd_a2.adapters.ImageAdapter;
import com.example.a21l_5812_smd_a2.Models.Folder;
import com.example.a21l_5812_smd_a2.Models.NoteImage;
import com.example.a21l_5812_smd_a2.Models.Subject;
import com.example.a21l_5812_smd_a2.utils.SharedPrefManager;
import java.util.ArrayList;

public class NotesImagesFragment extends Fragment implements ImageAdapter.OnImageClickListener {
    
    private RecyclerView recyclerView;
    private Button btnAddImage, btnShareImage;
    private ImageAdapter adapter;
    private Folder currentFolder;
    private Subject currentSubject;
    private SharedPrefManager prefManager;
    private NoteImage selectedImage;
    
    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    if (imageUri != null) {
                        addImageToFolder(imageUri);
                    }
                }
            });
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_images, container, false);
        
        prefManager = new SharedPrefManager(requireContext());
        initViews(view);
        
        // Get data from arguments
        if (getArguments() != null) {
            currentFolder = (Folder) getArguments().getSerializable("folder");
            currentSubject = (Subject) getArguments().getSerializable("subject");
            if (currentFolder != null) {
                setupRecyclerView();
            }
        }
        
        setupListeners();
        
        return view;
    }
    
    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        btnAddImage = view.findViewById(R.id.btnAddImage);
        btnShareImage = view.findViewById(R.id.btnShareImage);
    }
    
    private void setupRecyclerView() {
        adapter = new ImageAdapter(currentFolder.getImages(), this, prefManager.isShowPreview());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }
    
    private void setupListeners() {
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        
        btnShareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImage != null) {
                    shareImage();
                } else {
                    Toast.makeText(getContext(), "Please select an image first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }
    
    private void addImageToFolder(Uri imageUri) {
        String imageName = "Note_" + (currentFolder.getImages().size() + 1);
        NoteImage newImage = new NoteImage(imageUri.toString(), imageName);
        currentFolder.getImages().add(newImage);
        currentFolder.setImageCount(currentFolder.getImages().size());
        
        // Update adapter
        adapter.notifyDataSetChanged();
        
        // Save to SharedPreferences
        saveDataToPrefs();
    }
    
    private void saveDataToPrefs() {
        ArrayList<Subject> subjects = prefManager.loadSubjects();
        if (subjects != null) {
            for (Subject s : subjects) {
                if (s.getName().equals(currentSubject.getName())) {
                    for (Folder f : s.getFolders()) {
                        if (f.getName().equals(currentFolder.getName())) {
                            f.setImages(currentFolder.getImages());
                            f.setImageCount(currentFolder.getImages().size());
                            break;
                        }
                    }
                    break;
                }
            }
            prefManager.saveSubjects(subjects);
        }
    }
    
    private void shareImage() {
        if (selectedImage != null) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(selectedImage.getImagePath()));
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out my study note from " + currentFolder.getName());
            startActivity(Intent.createChooser(shareIntent, "Share Image via"));
        }
    }
    
    @Override
    public void onImageClick(NoteImage image) {
        selectedImage = image;
        Toast.makeText(getContext(), "Selected: " + image.getName(), Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onImageDelete(NoteImage image) {
        currentFolder.getImages().remove(image);
        currentFolder.setImageCount(currentFolder.getImages().size());
        adapter.notifyDataSetChanged();
        
        if (selectedImage == image) {
            selectedImage = null;
        }
        
        // Save to SharedPreferences
        saveDataToPrefs();
    }
}