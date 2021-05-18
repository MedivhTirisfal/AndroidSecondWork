package com.medivh20.androidsecondwork.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.medivh20.androidsecondwork.R;

public class HomeFragment extends Fragment {

    private TabLayout tabs;
    private ViewPager viewPager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initView(root);
        return root;
    }
    public void initView(View root){
        tabs = root.findViewById(R.id.home_tabLayout);
        viewPager = root.findViewById(R.id.home_viewPager);

        String[] titles = new String[]{"娱乐","工作","生活"};
//        for (int i = 0;i<3;i++){
//            TabLayout.Tab tab= tabs.newTab();
//            tab.setText(titles[i]);
//            tabs.addTab(tab);
//        }
        tabs.setupWithViewPager(viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
    }
}