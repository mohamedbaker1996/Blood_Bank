package com.mohamed.bloodbank.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohamed.bloodbank.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EmptyFragment extends BaseFragment {

    private Unbinder unbinder;

    public EmptyFragment() {
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
        View view = inflater.inflate(R.layout.fragment_empty, container, false);
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
