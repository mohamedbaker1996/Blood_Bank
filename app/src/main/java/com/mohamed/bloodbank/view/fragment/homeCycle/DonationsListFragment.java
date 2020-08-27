package com.mohamed.bloodbank.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.adapter.DonationAdapter;
import com.mohamed.bloodbank.adapter.SpinnerAdapter;
import com.mohamed.bloodbank.data.model.donations.DonationData;
import com.mohamed.bloodbank.data.model.donations.Donations;
import com.mohamed.bloodbank.helper.OnEndLess;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamed.bloodbank.data.api.RetrofitClient.getClient;
import static com.mohamed.bloodbank.helper.GeneralRequest.getSpinnerData;

public class DonationsListFragment extends BaseFragment {

    @BindView(R.id.donation_list_fragment_sp_blood_type)
    Spinner donationListFragmentSpBloodType;
    @BindView(R.id.donation_list_fragment_sp_city)
    Spinner donationListFragmentSpCity;
    @BindView(R.id.donation_list_fragment_rv_donation)
    RecyclerView donationListFragmentRvDonation;
    private Unbinder unbinder;
    private LinearLayoutManager linearLayout;
    private List<DonationData> donationDataList = new ArrayList<>();
    private DonationAdapter donationAdapter;
    private Integer maxPage = 0;
    private OnEndLess onEndLess;
    private SpinnerAdapter spinnerBloodTypeAdapter;
    private SpinnerAdapter spinnerGovermentAdapter;
   // private boolean Filter = false ;

    public DonationsListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_recycle_donation_items_layout, container, false);
        //  setUpActivity();
        unbinder = ButterKnife.bind(this, view);
        spinnerBloodTypeAdapter = new SpinnerAdapter(getActivity());
        getSpinnerData(getActivity(),donationListFragmentSpBloodType,spinnerBloodTypeAdapter,getString(R.string.tx_blood_type),getClient().getBloodTypes());
        spinnerGovermentAdapter = new SpinnerAdapter(getActivity());

        getSpinnerData(getActivity(),donationListFragmentSpCity,spinnerGovermentAdapter,getString(R.string.tx_goverment),
                getClient().getGoverment());

        init();
        return view;
    }

    private void init() {
        linearLayout = new LinearLayoutManager(getActivity());
        donationListFragmentRvDonation.setLayoutManager(linearLayout);
        onEndLess = new OnEndLess(linearLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                       // if (Filter) {
                         //   onFilter(current_page);
                        //}else {
                         //   getDonations(current_page);
                       // }
                        getDonations(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;

                }
            }
        };
        donationListFragmentRvDonation.addOnScrollListener(onEndLess);
        donationAdapter = new DonationAdapter(getActivity(), donationDataList);
        donationListFragmentRvDonation.setAdapter(donationAdapter);
        if (donationDataList.size()==0) {

            getDonations(1);
        }


    }

    private void getDonations(int page) {
        Call<Donations> call = getClient().donationRequsests("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", page);
        startCall(call, page);
    }

    private void startCall(Call<Donations> call, int page) {
        call.enqueue(new Callback<Donations>() {
            @Override
            public void onResponse(Call<Donations> call, Response<Donations> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        donationDataList.addAll(response.body().getData().getData());
                        donationAdapter.notifyDataSetChanged();


                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Donations> call, Throwable t) {

            }
        });
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

    @OnClick(R.id.donation_list_fragment_img_btn_filter)
    public void onViewClicked() {
        //onFilter(1);
    }
/*
    private void onFilter(int page) {
        Filter = true;
      onEndLess.previousTotal = 0 ;
       onEndLess.current_page= 1 ;
       onEndLess.previous_page= 1 ;
       donationDataList = new ArrayList<>();

        donationAdapter = new DonationAdapter(getActivity(), donationDataList);
        donationListFragmentRvDonation.setAdapter(donationAdapter);

        Call<Donations> call = getClient().donationRequsests("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", page, BloodType.selectedId,govermentAdapter.selctedId);
        startCall(call, 1);
    }*/
}
