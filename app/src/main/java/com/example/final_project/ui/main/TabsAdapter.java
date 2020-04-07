package com.example.final_project.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.final_project.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private Context mContext;

    public TabsAdapter(Context context, FragmentManager fm) {
        super(fm);

    }

    public TabsAdapter(FragmentManager fm, int NoofTabs, Context context) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }


    /**
     * To show tabs in the {@link com.example.final_project.NASAImage} activity
     * which returns fragments
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SearchFragment search = new SearchFragment();
                return search;
            case 1:
                SavedImagesFragment savedImages = new SavedImagesFragment();
                return savedImages;

            default:
                return null;
        }
    }
}