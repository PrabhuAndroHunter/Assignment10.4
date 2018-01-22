package com.assignment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.toString();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private int[] tabIcons = {
            R.drawable.audio,
            R.drawable.video
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init layout file
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        mViewPager = (ViewPager)findViewById(R.id.viewpager_Pp); //  init viewpager
        setViewPager(mViewPager);

        mTabLayout = (TabLayout)findViewById(R.id.tabs_Pp); // init tablayout
        mTabLayout.setupWithViewPager(mViewPager); // setup viewpager
        setupTabIcons(); // set tab icons
    }

    // This method will initialise all tab layout fragents and setup adapters
    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Fragment fragment = new AudioFragment();  // init AudioFragment
        adapter.addFragment(fragment, "Audio");

        fragment = new VideoFragment(); // init videoFragment
        adapter.addFragment(fragment, "Video");
        viewPager.setAdapter(adapter); // set adapter to viewpager
    }
    
    // This method will set icons to all tabs
    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    /* This is ViewPager Adapter,
    * and this will manage tab selection view pager position */
    
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
