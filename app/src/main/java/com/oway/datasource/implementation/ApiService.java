package com.oway.datasource.implementation;

import com.oway.di.BaseUrl;
import com.oway.model.request.LoginRequest;
import com.oway.model.response.LoginResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS).addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        Request build = builder.addHeader("token", "")
                                .build();
                        return chain.proceed(build);
                    }
                }).build();
        Retrofit retrofit;
        if (apiService == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(GsonConverterFactory.create()).build();
            apiService = retrofit.create(ApiInterface.class);
        }
    }

    public Call<LoginResponse> login(LoginRequest loginRequest) {
        return apiService.login(loginRequest);
    }

    public interface ApiInterface {
        @POST("/quikcard/webapi/login")
        Call<LoginResponse> login(@Body LoginRequest loginRequest);
    }
}

