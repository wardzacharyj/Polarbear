package origamiduck.com.polarbear.Widgets;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import origamiduck.com.polarbear.MyDatabase.AsyncDBCheck;
import origamiduck.com.polarbear.R;

public class SimpleScannerFragment extends Fragment implements ZBarScannerView.ResultHandler {

    private ZBarScannerView mScannerView;
    private String outpanEndpoint = "https://api.outpan.com/v2/products/[GTIN]?apikey=93b3b230011902064644ddf9ec0e2019";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.wtf("TAG", "Scanner View was created");
        return buildLayout();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(getActivity(), "Contents = " + rawResult.getContents() + ", Format = " + rawResult.getBarcodeFormat().getName(), Toast.LENGTH_SHORT).show();

        AsyncDBCheck asyncDBCheck = new AsyncDBCheck(getActivity(),rawResult.getContents());
        asyncDBCheck.execute(outpanEndpoint.replace("[GTIN]", rawResult.getContents()));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerFragment.this);
            }
        }, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    public CoordinatorLayout buildLayout(){
        CoordinatorLayout holder = new CoordinatorLayout(getActivity());
        holder.setId(R.id.coor_scanner);


        holder.setLayoutParams(new ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        holder.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimaryDark));

        mScannerView = new ZBarScannerView(getActivity());
        mScannerView.setId(R.id.scanner_view);
        mScannerView.setLayoutParams(new ViewGroup.LayoutParams(ZBarScannerView.LayoutParams.MATCH_PARENT, ZBarScannerView.LayoutParams.MATCH_PARENT));
        holder.addView(mScannerView);
        FloatingActionButton searchFab = new FloatingActionButton(getActivity());
        searchFab.setId(R.id.fab_search);
        searchFab.setSize(FloatingActionButton.SIZE_NORMAL);
        searchFab.setElevation(8);
        searchFab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        searchFab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_search_black_24dp));
        holder.addView(searchFab);
        return holder;
    }





}

