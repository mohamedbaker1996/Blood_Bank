package com.mohamed.bloodbank.helper;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.adapter.SpinnerAdapter;
import com.mohamed.bloodbank.data.model.generalResponse.GeneralResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralRequest {

    public static void getSpinnerData(Activity activity,final Spinner spinner,final SpinnerAdapter adapter,final String hint,
                                      Call<GeneralResponse> method ) {

        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {

                        spinner.setVisibility(View.VISIBLE);
                        adapter.setData(response.body().getData(),hint);
                        spinner.setAdapter(adapter);

                    }
                }catch (Exception e) {}
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
            HelperMethods.dismissProgressDialog();
            }
        });
    }

    public static void getSpinnerData(final Activity activity, final Spinner spinner,final SpinnerAdapter adapter,final String hint
    ,final Call<GeneralResponse> method, final Spinner spinner2,final SpinnerAdapter adapter2,final String hint2
    ,final  Call<GeneralResponse> method2) {


        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {

                    if (response.body().getStatus()==1) {

                        spinner.setVisibility(View.VISIBLE);
                        adapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(adapter);

//                    for (int i = 0; i < adapter.generalResponseDataList.size() ; i++) {
//                        if (adapter.generalResponseDataList.get(i).getId() == selectedId) {
//                            spinner.setSelection(i);
//                            break;
//                        }

//                }
                    }
                 spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                     @Override
                     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                         if (position != 0) {
                             getSpinnerData(activity, spinner2, adapter2, hint2, method2);
                         } else {
                             spinner2.setVisibility(View.GONE);
                         }
                     }

                     @Override
                     public void onNothingSelected(AdapterView<?> parent) {

                     }
                 });


            }catch (Exception e) {}
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
            HelperMethods.dismissProgressDialog();
            }
        });
    }
}
