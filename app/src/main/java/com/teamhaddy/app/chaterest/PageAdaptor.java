package com.teamhaddy.app.chaterest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

class PageAdapter extends FragmentStateAdapter {
    public PageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TechFragment();
            case 1:
                return new SportsFragment();
            default:
                return new MusicFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}