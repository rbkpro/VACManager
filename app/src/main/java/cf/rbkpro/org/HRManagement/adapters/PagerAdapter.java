package cf.rbkpro.org.HRManagement.adapters;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cf.rbkpro.org.HRManagement.MainFragment;
import cf.rbkpro.org.HRManagement.R;


public class PagerAdapter extends FragmentPagerAdapter {
    String[] tabTitles;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        Resources resources= context.getResources();
        tabTitles=resources.getStringArray(R.array.tab_titles);
    }

    @Override
    public Fragment getItem(int i) {
        Bundle bundle=new Bundle();
        Fragment mainFragment;
        if(i==0){
            bundle.putString(MainFragment.sectionView, "all employees");
        }else{
            bundle.putString(MainFragment.sectionView,"employees in vacation");
        }
        mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
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
