package com.mohamed.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.data.model.donations.DonationData;
import com.mohamed.bloodbank.data.model.generalResponse.GeneralResponse;
import com.mohamed.bloodbank.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {

    private Context context;
    private BaseActivity activity;
    private List<DonationData> donationDataList = new ArrayList<>();
    private GeneralResponse generalResponse ;


    public DonationAdapter(Activity activity, List<DonationData> donationDataList) {
        this.context = activity;
        this.activity = (BaseActivity) activity;
        this.donationDataList = donationDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donation,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        holder.donationAdapterTvName.setText(donationDataList.get(position).getPatientName());
        holder.donationAdapterTvBloodType.setText(donationDataList.get(position).getBloodType().getName());
        holder.donationAdapterTvHospitalAddress.setText(donationDataList.get(position).getHospitalName());
        holder.donationAdapterTvCity.setText(donationDataList.get(position).getCity().getName());


    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return donationDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.donation_adapter_tv_name)
        TextView donationAdapterTvName;
        @BindView(R.id.donation_adapter_tv_hospital_address)
        TextView donationAdapterTvHospitalAddress;
        @BindView(R.id.donation_adapter_tv_city)
        TextView donationAdapterTvCity;
        @BindView(R.id.donation_adapter_tv_blood_type)
        TextView donationAdapterTvBloodType;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
