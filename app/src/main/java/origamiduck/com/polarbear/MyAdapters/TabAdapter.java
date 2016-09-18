package origamiduck.com.polarbear.MyAdapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import origamiduck.com.polarbear.R;
import origamiduck.com.polarbear.SuggestionFragment;
import origamiduck.com.polarbear.Widgets.SimpleScannerFragment;

/**
 * Created by Zach on 9/13/16.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] TITLES;
    public TabAdapter(FragmentManager fm, Context c) {
        super(fm);
        TITLES = new String[] {c.getResources().getString(R.string.scanner), c.getResources().getString(R.string.suggestions)};
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                //ScannerFragment scannerFragment = new ScannerFragment();
                SimpleScannerFragment simpleScannerFragment = new SimpleScannerFragment();
                return simpleScannerFragment;
            case 1:
                SuggestionFragment suggestionFragment = new SuggestionFragment();
                return suggestionFragment;
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
