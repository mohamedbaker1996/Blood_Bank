package com.mohamed.bloodbank.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.adapter.SpinnerAdapter;
import com.mohamed.bloodbank.data.model.DateTxt;
import com.mohamed.bloodbank.data.model.login.Client;
import com.mohamed.bloodbank.data.model.profile.Profile;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

import java.text.DecimalFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamed.bloodbank.data.api.RetrofitClient.getClient;
import static com.mohamed.bloodbank.helper.GeneralRequest.getSpinnerData;
import static com.mohamed.bloodbank.helper.HelperMethods.showCalender;

public class EditProfileFragment extends BaseFragment {


    @BindView(R.id.tool_bar_edit)
    Toolbar toolBarEdit;
    @BindView(R.id.et_edit_name)
    TextInputEditText etEditName;
    @BindView(R.id.et_edit_email)
    TextInputEditText etEditEmail;

    @BindView(R.id.spinner_edit_blood_type)
    Spinner spinnerEditBloodType;

    @BindView(R.id.spinner_govern_edit_fragment)
    Spinner spinnerGovernEditFragment;
    @BindView(R.id.spinner_city_edit_fragment)
    Spinner spinnerCityEditFragment;
    @BindView(R.id.et_phone_num_edit)
    TextInputEditText etPhoneNumEdit;
    @BindView(R.id.et_password_edit)
    TextInputEditText etPasswordEdit;
    @BindView(R.id.et_confirm_password_edit)
    TextInputEditText etConfirmPasswordEdit;
    @BindView(R.id.btn_edit_first)
    Button btnEditFirst;
    @BindView(R.id.edit_profile_fragment_tv_birth_date)
    TextView editProfileFragmentTvBirthDate;
    @BindView(R.id.edit_profile_fragment_tv_last_donation_date)
    TextView editProfileFragmentTvLastDonationDate;
    private Unbinder unbinder;
    private SpinnerAdapter spinnerBloodTypeAdapter;
    private SpinnerAdapter spinnerGovermentAdapter;
    private SpinnerAdapter spinnerCityAdapter;
    private DateTxt birthdayDate,lastDonationDate;

    public EditProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        //  setUpActivity();
        unbinder = ButterKnife.bind(this, view);
      spinnerBloodTypeAdapter = new SpinnerAdapter(getActivity());
      getSpinnerData(getActivity(),spinnerEditBloodType,spinnerBloodTypeAdapter,getString(R.string.tx_blood_type),getClient().getBloodTypes());
      spinnerGovermentAdapter = new SpinnerAdapter(getActivity());
      spinnerCityAdapter = new SpinnerAdapter(getActivity());
      getSpinnerData(getActivity(),spinnerGovernEditFragment,spinnerGovermentAdapter,getString(R.string.tx_goverment),
              getClient().getGoverment(),spinnerCityEditFragment,spinnerCityAdapter,getString(R.string.tx_city),
              getClient().getCity(spinnerGovermentAdapter.selectedId));

        getProfile();
        setDates();

        return view;
    }

    private void setDates() {
        DecimalFormat mFormat = new DecimalFormat("00");
        Calendar calendar = Calendar.getInstance();
        String cDay = mFormat.format(Double.valueOf(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))));
        String cMonth = mFormat.format(Double.valueOf(String.valueOf(calendar.get(Calendar.MONTH +1))));
        String cYear = String.valueOf(calendar.get(Calendar.YEAR));

        lastDonationDate = new DateTxt(cDay,cMonth,cYear,cDay+"-"+cMonth+"-"+cYear);
        birthdayDate = new DateTxt("01","01","1990","01-01-1990");
    }

    private void getProfile() {
        getClient().getProfile("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl").enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        setData(response.body().getData().getClient());

//                        spinnerGovermentAdapter = new SpinnerAdapter(getActivity());
//                        spinnerBloodTypeAdapter = new SpinnerAdapter(getActivity());
//                        spinnerCityAdapter = new SpinnerAdapter(getActivity());
//
//                        getSpinnerData(getClient().getBloodTypes(), spinnerEditBloodType, spinnerBloodTypeAdapter, response.body().getData().getClient().getBloodType().getId(), getString(R.string.tx_blood_type));
//                        AdapterView.OnItemSelectedListener onGovermentItemSelected = new AdapterView.OnItemSelectedListener() {
//
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                                if (i == 0) {
//                                    getSpinnerData(getClient().getCity(spinnerGovermentAdapter.selectedId), spinnerCityEditFragment, spinnerCityAdapter, response.body().getData().getClient().getCity().getId(), getString(R.string.tx_city));
//
//
//                                }
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        };
//                        getSpinnerData(getClient().getGoverment(), spinnerGovernEditFragment, spinnerGovermentAdapter, response.body().getData().getClient().getBloodType().getId(), getString(R.string.tx_goverment), onGovermentItemSelected);
//

                    }
                } catch (Exception e) {

                }
            }


            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
            }
        });
    }

    private void setData(Client client) {
        etEditName.getEditableText().append(client.getName());
        etEditEmail.getEditableText().append(client.getEmail());
        editProfileFragmentTvBirthDate.getEditableText().append(client.getBirthDate());
        editProfileFragmentTvLastDonationDate.getEditableText().append(client.getDonationLastDate());


        etPhoneNumEdit.getEditableText().append(client.getPhone());
        etPasswordEdit.getEditableText().append("");
        etConfirmPasswordEdit.getEditableText().append("");
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick({R.id.edit_profile_fragment_tv_birth_date, R.id.edit_profile_fragment_tv_last_donation_date, R.id.btn_edit_first})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_profile_fragment_tv_birth_date:
                showCalender(getActivity(),getString(R.string.select_date),editProfileFragmentTvBirthDate,birthdayDate);
                break;
            case R.id.edit_profile_fragment_tv_last_donation_date:
                showCalender(getActivity(),getString(R.string.select_date),editProfileFragmentTvLastDonationDate,lastDonationDate);

                break;
            case R.id.btn_edit_first:
                editData();
                break;
        }
    }

    private void editData() {
     String name = etEditName.getText().toString();
     String email= etEditEmail.getText().toString();
     String birthDate = editProfileFragmentTvBirthDate.getText().toString();
     int city = spinnerCityEditFragment.getId();
     int bloodType = spinnerEditBloodType.getId();
     int goverment = spinnerGovernEditFragment.getId();
     String lastDonation = editProfileFragmentTvLastDonationDate.getText().toString();
     String phone = etPhoneNumEdit.getText().toString();
     String password = etPasswordEdit.getText().toString();
     String confirmPassword = etConfirmPasswordEdit.getText().toString();
    getClient().updateUserData(name,email,birthDate,city,phone,lastDonation,password,confirmPassword,bloodType,"W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl").enqueue(new Callback<Profile>() {
        @Override
        public void onResponse(Call<Profile> call, Response<Profile> response) {
            try {
                if (response.body().getStatus()==1) {
                    Toast.makeText(baseActivity, "Your user data has changed successfully", Toast.LENGTH_SHORT).show();


                }
            }catch (Exception e){

            }
        }

        @Override
        public void onFailure(Call<Profile> call, Throwable t) {

        }
    });

    }
}
