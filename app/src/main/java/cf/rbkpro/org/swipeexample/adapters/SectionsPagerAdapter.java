package cf.rbkpro.org.swipeexample.adapters;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cf.rbkpro.org.swipeexample.Fragments.PlaceholderFragment;
import cf.rbkpro.org.swipeexample.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    String[] tabTitles;
    String[] tabDescriptions;

    public SectionsPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        Resources resources= context.getResources();
        tabTitles=resources.getStringArray(R.array.tab_titles);
        tabDescriptions=resources.getStringArray(R.array.tab_descriptions);
    }

    @Override
    public Fragment getItem(int i) {
        Bundle bundle=new Bundle();
        bundle.putString(PlaceholderFragment.descriptionKey,tabDescriptions[i]);
        if(i==0){
            bundle.putString(PlaceholderFragment.sectionView,"all employees");
        }else bundle.putString(PlaceholderFragment.sectionView,"employees in vacation");
        PlaceholderFragment placeholderFragment= new PlaceholderFragment();
        placeholderFragment.setArguments(bundle);
        return placeholderFragment;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
