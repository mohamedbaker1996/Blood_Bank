package com.mohamed.bloodbank.data.api;

import com.mohamed.bloodbank.data.model.articles.Articles;
import com.mohamed.bloodbank.data.model.donations.Donations;
import com.mohamed.bloodbank.data.model.forgetPassword.ForgetPassword;
import com.mohamed.bloodbank.data.model.generalResponse.GeneralResponse;
import com.mohamed.bloodbank.data.model.login.Login;
import com.mohamed.bloodbank.data.model.login.LoginData;
import com.mohamed.bloodbank.data.model.notification.Notification;
import com.mohamed.bloodbank.data.model.profile.Profile;
import com.mohamed.bloodbank.data.model.register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    @GET("governorates")
    Call<GeneralResponse> getGoverment();

    @GET("cities")
    Call<GeneralResponse> getCity(@Query("governorate_id") int governorateId);

    @GET("blood-types")
    Call<GeneralResponse> getBloodTypes();

    @POST("login")
    @FormUrlEncoded

    Call<Login> onLogin (@Field("phone") String phone,
                            @Field("password") String password);

    @POST("reset-password")
    @FormUrlEncoded
     Call<ForgetPassword> onForgetPassword (@Field("phone") String phone);


    @POST("signup")
    @FormUrlEncoded
    Call<Register> onRegister (@Field("name") String name,
                               @Field("email") String email,
                               @Field("birth_date") String birthDate,
                               @Field("city_id") String cityId,
                               @Field("phone") String phone,
                               @Field("donation_last_date") String donationLastDate,
                               @Field("blood_type_id") String bloodTypeId,
                               @Field("city") String city,
                               @Field("password") String password,
                               @Field("password_confirmation") String passwordConfirmation

                               );

    @GET("donation-requests")
    Call<Donations> donationRequsests (@Query("api_token")String apiToken,
                             @Query("page") int page );

    @GET("donation-requests")
    Call<Donations> donationRequsests (@Query("api_token")String apiToken,
                                       @Query("page") int page ,
                                       @Query("blood_type_id") int bloodTypeId,
                                       @Query("governorate_id") int governorateId
    );
    @GET("posts")
    Call<Articles> posts (@Query("api_token")String apiToken,
                                      @Query("page") int page );

    @POST("profile")
    @FormUrlEncoded
    Call<Profile> getProfile (@Field("api_token")String apiToken);

    @POST("profile")
    @FormUrlEncoded
    Call<Profile> updateUserData (@Field("name")String name,
                                  @Field("email") String email,
                                  @Field("birth_date") String birthDate,
                                  @Field("city_id")int cityId,
                                  @Field("phone") String phone,
                                  @Field("donation_last_date") String donationLastDate,
                                  @Field("password") String password,
                                  @Field("password_confirmation")String passwordConfirmation,
                                  @Field("blood_type_id") int bloodTypeId,
                                  @Field("api_token")String apiToken);


    @GET("notifications")
    Call<Notification> getNotification (@Query("api_token") String apiToken,
                                         @Query("page") int page);
}
