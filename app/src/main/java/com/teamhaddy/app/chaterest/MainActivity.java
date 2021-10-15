package com.teamhaddy.app.chaterest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager2 viewPager2=findViewById(R.id.viewPager);
        viewPager2.setAdapter(new PageAdapter(this));

        TabLayout tabLayout=findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator =new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: {
                        tab.setText("Tech");
                        tab.setIcon(R.drawable.ic_tech);
                        break;
                    }
                    case 1: {
                        tab.setText("Sports");
                        tab.setIcon(R.drawable.ic_sports);
                        break;
                    }
                    case 2: {
                        tab.setText("Music");
                        tab.setIcon(R.drawable.ic_music);
                    }
                }
            }
        }
        );
        tabLayoutMediator.attach();
    }
}