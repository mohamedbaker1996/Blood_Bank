package com.mohamed.bloodbank.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.SupportMapFragment;
import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PickHosiptalLocation extends BaseFragment {

//    @BindView(R.id.pick_location_map_fragment)
//    android.widget.fragment pickLocationMapFragment;
    @BindView(R.id.btn_pick_hospital_location_fragment_choose_location)
    Button btnPickHospitalLocationFragmentChooseLocation;
    private Unbinder unbinder;

    public PickHosiptalLocation() {
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
        View view = inflater.inflate(R.layout.fragment_pick_hotpital_location, container, false);
        //  setUpActivity();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.pick_location_map_fragment);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_pick_hospital_location_fragment_choose_location)
    public void onViewClicked() {
    }
}
