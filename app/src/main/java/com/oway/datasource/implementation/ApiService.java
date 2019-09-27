package com.oway.datasource.implementation;

import com.oway.di.BaseUrl;
import com.oway.model.request.CustomerTransactionRequest;
import com.oway.model.request.GetEstimateBikeRequest;
import com.oway.model.request.GetNearestDriverRequest;
import com.oway.model.request.GetRecommendedPlacesRequest;
import com.oway.model.request.GetSaldoRequest;
import com.oway.model.request.GetSearchPlacesRequest;
import com.oway.model.request.LoginRequest;
import com.oway.model.request.RegisterRequest;
import com.oway.model.request.SendDriverRequest;
import com.oway.model.response.CustomerTransactionResponse;
import com.oway.model.response.GetEstimateBikeResponse;
import com.oway.model.response.GetNearestDriverResponse;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.model.response.LocationDetailsResponse;
import com.oway.model.response.GetSaldoResponse;
import com.oway.model.response.LoginResponse;
import com.oway.model.response.RegisterResponse;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

@Singleton
public class ApiService {
    private final String baseUrl;
    private ApiInterface apiService;

    @Inject
    public ApiService(@BaseUrl String baseUrl) {
        this.baseUrl = baseUrl;
        getClient();
    }
    private void getClient() {

        Request.Builder builder = new Request.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS).build();
        Retrofit retrofit;
        if (apiService == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(GsonConverterFactory.create()).build();
            apiService = retrofit.create(ApiInterface.class);
        }
    }

  /*  private void getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS).addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        Request build = builder.addHeader("", "")
                                .build();
                        return chain.proceed(build);
                    }
                }).build();
        Retrofit retrofit;
        if (apiService == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(GsonConverterFactory.create()).build();
            apiService = retrofit.create(ApiInterface.class);
        }*/


    public Call<LoginResponse> login(LoginRequest loginRequest) {
        return apiService.login(loginRequest);
    }

    public Call<GetSaldoResponse> getSaldo(GetSaldoRequest saldoRequest) {
        return apiService.getSaldoRequest(saldoRequest);
    }

    public Call<GetNearestDriverResponse> getNearestDriver(GetNearestDriverRequest nearestDriverRequest) {
        return apiService.getNearestDriver(nearestDriverRequest);
    }

    public Call<RegisterResponse> register(RegisterRequest registerRequest) {
        return apiService.register(registerRequest);
    }

    public Call<GetRecommendedPlacesResponse> getRecommendedPlaces(GetRecommendedPlacesRequest placeRequest) {
        return apiService.getResommendedRequest(placeRequest);
    }
    public Call<GetRecommendedPlacesResponse> getSearchPlaces(GetSearchPlacesRequest placeRequest) {
        return apiService.getSearchPlacesRequest(placeRequest);
    }

    public Call<GetEstimateBikeResponse> getEstimateBikePrice(GetEstimateBikeRequest priceRequest) {
        return apiService.getEstimateBikeRequest(priceRequest);
    }
    public Call<CustomerTransactionResponse> getCustomerTransRequest(CustomerTransactionRequest mRequest) {
        return apiService.getCustomerTransactionRequest(mRequest);
    }

    public Call<GetNearestDriverResponse> getNearestDriverRequest(SendDriverRequest mRequest) {
        return apiService.getDriverRequest(mRequest);
    }

    public Call<LocationDetailsResponse> getCurrentAddress(String latlng,String key) {
        return apiService.getLocationDetails(latlng,key);
    }
    public interface ApiInterface {

        @POST("/api/customer/login")
        Call<LoginResponse> login(@Body LoginRequest loginRequest);

        @POST("/api/customer/register")
        Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

        @POST("/api/trx/getnearestdriver")
        Call<GetNearestDriverResponse> getNearestDriver(@Body GetNearestDriverRequest nearestDriverRequest);

        @POST("api/customer/get_recommended_place")
        Call<GetRecommendedPlacesResponse> getResommendedRequest(@Body GetRecommendedPlacesRequest placeRequest);

        @POST("api/customer/getestimateprice")
        Call<GetEstimateBikeResponse> getEstimateBikeRequest(@Body GetEstimateBikeRequest bikeRequest);

        @POST("api/ride/customer_request_transaction")
        Call<CustomerTransactionResponse> getCustomerTransactionRequest(@Body CustomerTransactionRequest bikeRequest);

        @POST("api/trx/send_request_to_driver")
        Call<GetNearestDriverResponse> getDriverRequest(@Body SendDriverRequest request);

        @GET("https://maps.googleapis.com/maps/api/geocode/json")
        Call<LocationDetailsResponse> getLocationDetails(@Query("latlng") String latlng, @Query("key") String key);
        @POST("/api/customer/getsaldo")
        Call<GetSaldoResponse> getSaldoRequest(@Body GetSaldoRequest saldoRequest);

        @POST("/api/customer/nearbysearch")
        Call<GetRecommendedPlacesResponse> getSearchPlacesRequest(@Body GetSearchPlacesRequest placeRequest);
    }
}

