package com.example.a21l_5812_smd_a2.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.a21l_5812_smd_a2.fragments.FoldersFragment;
import com.example.a21l_5812_smd_a2.fragments.SettingsFragment;
import com.example.a21l_5812_smd_a2.fragments.SubjectsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SubjectsFragment();
            case 1:
                return new FoldersFragment();
            case 2:
                return new SettingsFragment();
            default:
                return new SubjectsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
