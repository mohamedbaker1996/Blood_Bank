package com.mohamed.bloodbank.view.fragment.userCycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.data.model.login.Login;
import com.mohamed.bloodbank.helper.HelperMethods;
import com.mohamed.bloodbank.view.activity.HomeCycleActivity;
import com.mohamed.bloodbank.view.activity.UserCycleActivity;
import com.mohamed.bloodbank.view.fragment.BaseFragment;
import com.mohamed.bloodbank.view.fragment.homeCycle.DonationsListFragment;
import com.mohamed.bloodbank.view.fragment.homeCycle.HomeFragment;
import com.mohamed.bloodbank.view.fragment.homeCycle.PostsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.mohamed.bloodbank.data.api.RetrofitClient.getClient;
import static com.mohamed.bloodbank.helper.network.InternetState.isConnected;

public class LoginFragment extends BaseFragment {

    @BindView(R.id.et_login_mobile_phone)
    EditText etLoginMobilePhone;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.img_btn_forget_password_login)
    Button imgBtnForgetPasswordLogin;
    @BindView(R.id.chk_remember_me_login)
    AppCompatCheckBox chkRememberMeLogin;
    @BindView(R.id.tv_remember_me_login)
    TextView tvRememberMeLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_have_acc_)
    Button btnHaveAcc;
    Unbinder unbinder;

    public LoginFragment() {
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
       View view =inflater.inflate(R.layout.fragment_login, container, false);
      //  setUpActivity();
        unbinder =  ButterKnife.bind(this,view);
        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onBack() {
        getActivity().finish();


    }

    @OnClick({R.id.img_btn_forget_password_login, R.id.btn_login, R.id.btn_have_acc_})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_btn_forget_password_login:
                HelperMethods.replaceFragment(getActivity().getSupportFragmentManager(),R.id.user_cycle_frame,new ForgetPasswordStep1());
                break;

            case R.id.btn_login:
              String phone = etLoginMobilePhone.getText().toString();
              String password = etLoginPassword.getText().toString();
                if(validateLogin(phone, password)) {
                    onCall(phone, password);
                    break;
                }else {
                    Toast.makeText(baseActivity, "something wrong", Toast.LENGTH_SHORT).show();
                }
            case R.id.btn_have_acc_:
                HelperMethods.replaceFragment(getActivity().getSupportFragmentManager(),R.id.user_cycle_frame,new RegisterAsNewUser());

        }
    }
    private Boolean validateLogin (String phone,String password){
        if (phone == null || phone.trim().length()== 0) {
            Toast.makeText(getActivity(), "phone is required", Toast.LENGTH_SHORT).show();
            return false;

        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(getActivity(), "password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
    }

    private void onCall(String phone, String password) {
        if (isConnected(getActivity())) {


            getClient().onLogin(phone, password).enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    try {
                        if (response.body().getStatus() == 1) {
                            Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
                            startActivity(intent);
                            Toast.makeText(getActivity(), "login success", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "phone or password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Log.d(TAG, "onFailure:  " + t.toString());
                    Toast.makeText(getActivity(), "fail login ", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
