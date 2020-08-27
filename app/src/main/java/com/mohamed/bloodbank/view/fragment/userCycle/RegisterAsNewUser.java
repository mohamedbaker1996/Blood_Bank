package com.mohamed.bloodbank.view.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.adapter.SpinnerAdapter;
import com.mohamed.bloodbank.data.model.generalResponse.GeneralResponse;
import com.mohamed.bloodbank.data.model.register.Register;
import com.mohamed.bloodbank.helper.HelperMethods;
import com.mohamed.bloodbank.view.fragment.BaseFragment;
import com.mohamed.bloodbank.view.fragment.homeCycle.PostsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamed.bloodbank.data.api.RetrofitClient.getClient;
import static com.mohamed.bloodbank.helper.GeneralRequest.getSpinnerData;

public class RegisterAsNewUser extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.et_register_name)
    EditText etRegisterName;
    @BindView(R.id.et_register_email)
    EditText etRegisterEmail;
    @BindView(R.id.et_register_birth_date)
    EditText etRegisterBirthDate;
    @BindView(R.id.et_register_blood_type)
    Spinner etRegisterBloodType;
    @BindView(R.id.et_register_last_donation_date)
    EditText etRegisterLastDonationDate;
    @BindView(R.id.et_govern_register)
    Spinner etGovernRegister;
    @BindView(R.id.et_city_register)
    Spinner etCityRegister;
    @BindView(R.id.et_phone_num_register)
    EditText etPhoneNumRegister;
    @BindView(R.id.et_password_register)
    EditText etPasswordRegister;
    @BindView(R.id.et_confirm_password_register)
    EditText etConfirmPasswordRegister;
    @BindView(R.id.btn_register_first)
    Button btnRegisterFirst;
    private SpinnerAdapter spinnerBloodTypeAdapter;
    private SpinnerAdapter spinnerGovermentAdapter;
    private SpinnerAdapter spinnerCityAdapter;

    public RegisterAsNewUser() {
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
        View view = inflater.inflate(R.layout.fragment_register_as_new_user, container, false);
        //  setUpActivity();
        accessSpinner();
        unbinder = ButterKnife.bind(this, view);


        return view;

    }

    private void accessSpinner() {


        getClient().getGoverment().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.body().getStatus() == 1) {


                    spinnerGovermentAdapter = new SpinnerAdapter(getActivity());
                    spinnerBloodTypeAdapter = new SpinnerAdapter(getActivity());
                    spinnerCityAdapter = new SpinnerAdapter(getActivity());

//                    getSpinnerData(getClient().getBloodTypes(), etRegisterBloodType, spinnerBloodTypeAdapter, 0, getString(R.string.tx_blood_type));
//                    AdapterView.OnItemSelectedListener onGovermentItemSelected = new AdapterView.OnItemSelectedListener() {
//
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                            if (i == 0) {
//                                getSpinnerData(getClient().getCity(0), etCityRegister, spinnerCityAdapter, 0, getString(R.string.tx_city));
//
//
//                            } else {
//                                getSpinnerData(getClient().getCity(i), etCityRegister, spinnerCityAdapter, 0, getString(R.string.tx_city));
//                            }
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    };
//                    getSpinnerData(getClient().getGoverment(), etGovernRegister, spinnerGovermentAdapter, 0, getString(R.string.tx_goverment), onGovermentItemSelected);
//

                    spinnerBloodTypeAdapter = new SpinnerAdapter(getActivity());
                    getSpinnerData(getActivity(),etRegisterBloodType,spinnerBloodTypeAdapter,getString(R.string.tx_blood_type),getClient().getBloodTypes());
                    spinnerGovermentAdapter = new SpinnerAdapter(getActivity());
                    spinnerCityAdapter = new SpinnerAdapter(getActivity());
                    getSpinnerData(getActivity(),etGovernRegister,spinnerGovermentAdapter,getString(R.string.tx_goverment),
                            getClient().getGoverment(),etCityRegister,spinnerCityAdapter,getString(R.string.tx_city),
                            getClient().getCity(spinnerGovermentAdapter.selectedId));
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.btn_register_first)
    public void onViewClicked() {
    onRegister();
    }

    private void onRegister(){
        String name = etRegisterName.getText().toString();
        String email = etRegisterEmail.getText().toString();
        String birthDate = etRegisterEmail.getText().toString();
        String phone = etRegisterEmail.getText().toString();
        String donationLastDate = etRegisterEmail.getText().toString();
        String password = etPasswordRegister.getText().toString();
        String passwordConfirmation = etConfirmPasswordRegister.getText().toString();
        String city = String.valueOf(etCityRegister.getId());
        String bloodType = String.valueOf(etRegisterBloodType.getId());
        String goverment = String.valueOf(etGovernRegister.getId());


        if (email.isEmpty()) {
            etRegisterEmail.setError("Enter Valid email");
            etRegisterEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPasswordRegister.setError("password required");
            etPasswordRegister.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPasswordRegister.setError("password should be at least 6 characters long");
            etPasswordRegister.requestFocus();
            return;
        }
        if (phone.length() < 11) {
            etPhoneNumRegister.setError("phone is not correct");
            etPhoneNumRegister.requestFocus();
            return;
        }

        getClient().onRegister(name,email,birthDate,goverment,phone,donationLastDate,bloodType,city,password,passwordConfirmation).enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(baseActivity, "Welcome", Toast.LENGTH_SHORT).show();
                        HelperMethods.replaceFragment(getActivity().getSupportFragmentManager(),R.id.user_cycle_frame,new PostsFragment());

                    }
                }catch (Exception e){}

        }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });
    }


}
