package com.mohamed.bloodbank.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoreFragment extends BaseFragment {

    private Unbinder unbinder;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        //  setUpActivity();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onBack(){
        super.onBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
