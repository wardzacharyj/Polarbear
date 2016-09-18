package origamiduck.com.polarbear.MyAdapters;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import origamiduck.com.polarbear.CurrentFragment;
import origamiduck.com.polarbear.EcoFragment;
import origamiduck.com.polarbear.R;
import origamiduck.com.polarbear.RecentFragment;

public class FridgeTabAdapter  extends FragmentStatePagerAdapter {
    private String[] TITLES;
    public FridgeTabAdapter(FragmentManager fm, Context c) {
        super(fm);
        TITLES = new String[] {c.getResources().getString(R.string.recent), c.getResources().getString(R.string.current), c.getResources().getString(R.string.tips)};
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RecentFragment recentFragment = new RecentFragment();
                return recentFragment;
            case 1:
                CurrentFragment currentFragment = new CurrentFragment();
                return currentFragment;
            case 2:
                EcoFragment ecoFragment = new EcoFragment();
                return ecoFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }
}
