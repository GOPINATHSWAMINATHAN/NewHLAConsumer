package com.hlacab.hla;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gopinath on 02/12/17.
 */

public class BottomSheetRiderFragment extends BottomSheetDialogFragment {


    String mTag;
    public static BottomSheetRiderFragment newInstance(String tag)
    {
        BottomSheetRiderFragment f=new BottomSheetRiderFragment();
        Bundle args=new Bundle();
        args.putString("TAG",tag);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mTag=getArguments().getString("TAG");
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottom_sheet_rider,container,false);
        return view;
    }
}
