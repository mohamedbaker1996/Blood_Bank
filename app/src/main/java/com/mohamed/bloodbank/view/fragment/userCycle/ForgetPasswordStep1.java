package com.mohamed.bloodbank.view.fragment.userCycle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.data.model.forgetPassword.ForgetPassword;
import com.mohamed.bloodbank.helper.HelperMethods;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.mohamed.bloodbank.data.api.RetrofitClient.getClient;
import static com.mohamed.bloodbank.helper.HelperMethods.replaceFragment;
import static com.mohamed.bloodbank.helper.network.InternetState.isConnected;

public class ForgetPasswordStep1 extends BaseFragment {

    @BindView(R.id.forget_password_phone)
    EditText forgetPasswordPhone;
    @BindView(R.id.forget_password_btn_send)
    Button forgetPasswordBtnSend;

    public ForgetPasswordStep1() {
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
        View view = inflater.inflate(R.layout.fragment_forget_password_step_1, container, false);
        setUpActivity();
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.forget_password_btn_send)
    public void onViewClicked() {
        String phone = forgetPasswordPhone.getText().toString();
        if (phone.length()!= 0) {
            onCall(phone);
        }
    }

    private void onCall(final String phone) {
        if (isConnected(getActivity())) {
            getClient().onForgetPassword(phone).enqueue(new Callback<ForgetPassword>() {
                @Override
                public void onResponse(Call<ForgetPassword> call, Response<ForgetPassword> response) {
                    try {
                        if (response.body().getStatus() ==1) {
                           ForgetPasswordStep2 forgetPasswordStep2 = new ForgetPasswordStep2();
                           ForgetPasswordStep2.Phone = phone;
                           replaceFragment(getActivity().getSupportFragmentManager(),R.id.user_cycle_frame, forgetPasswordStep2);
                        }
                        Toast.makeText(baseActivity,response.body().getMsg() , Toast.LENGTH_SHORT).show();

                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(Call<ForgetPassword> call, Throwable t) {
                    Log.d(TAG, "onFailure:  "+t.toString());
                }
            });
        }
    }
}
